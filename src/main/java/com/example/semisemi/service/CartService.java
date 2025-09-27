// CartService.java
package com.example.semisemi.service;

import com.example.semisemi.dto.CartItemDTO;
import com.example.semisemi.vo.CartItemEntity;
import com.example.semisemi.vo.ProductEntity;
import com.example.semisemi.repository.CartItemRepository;
import com.example.semisemi.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartItemRepository cartRepo;
    private final ProductRepository productRepo;

    private CartItemDTO toDTO(CartItemEntity e) {
        return new CartItemDTO(
                e.getProduct().getId(),
                e.getProduct().getName(),
                e.getProduct().getImageUrl(),
                e.getQty(),
                e.getPriceSnap()
        );
    }

    public List<CartItemDTO> getCart(String sessionId) {
        if (sessionId == null || sessionId.isBlank()) {
            log.warn("[getCart] sessionId is null/blank");
            return List.of();
        }
        var list = cartRepo.findBySessionId(sessionId).stream().map(this::toDTO).toList();
        log.info("[getCart] sid={} items={}", sessionId, list.size());
        return list;
    }

    @Transactional
    public List<CartItemDTO> add(String sessionId, Long productId, int deltaQty) {
        if (sessionId == null || sessionId.isBlank())
            throw new IllegalArgumentException("세션이 유효하지 않습니다.");
        if (productId == null)
            throw new IllegalArgumentException("productId가 필요합니다.");
        if (deltaQty == 0) return getCart(sessionId);

        ProductEntity p = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음: " + productId));

        var itemOpt = cartRepo.findBySessionIdAndProduct_Id(sessionId, productId);
        if (itemOpt.isPresent()) {
            var item = itemOpt.get();
            int newQty = item.getQty() + deltaQty;
            log.info("[add] sid={} pid={} oldQty={} delta={} newQty={}",
                    sessionId, productId, item.getQty(), deltaQty, newQty);
            if (newQty <= 0) {
                cartRepo.delete(item);
                log.info("[add] qty<=0 → delete");
            } else {
                item.setQty(newQty);
                cartRepo.save(item);
            }
        } else {
            int q = Math.max(1, deltaQty);
            var ni = new CartItemEntity();
            ni.setSessionId(sessionId);
            ni.setProduct(p);
            ni.setQty(q);
            ni.setPriceSnap(p.getPrice());
            cartRepo.save(ni);
            log.info("[add] new item sid={} pid={} qty={}", sessionId, productId, q);
        }
        return getCart(sessionId);
    }

    @Transactional
    public void remove(String sessionId, Long productId) {
        if (sessionId == null || sessionId.isBlank()) {
            log.warn("[remove] sessionId null/blank");
            return;
        }
        long deleted = cartRepo.deleteBySessionIdAndProduct_Id(sessionId, productId);
        log.info("[remove] sid={} pid={} deleted={}", sessionId, productId, deleted);
    }

    @Transactional
    public void clear(String sessionId) {
        if (sessionId == null || sessionId.isBlank()) {
            log.warn("[clear] sessionId null/blank");
            return;
        }
        long deleted = cartRepo.deleteBySessionId(sessionId);
        log.info("[clear] sid={} deletedRows={}", sessionId, deleted);
    }
}
