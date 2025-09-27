// CartController.java
package com.example.semisemi.controller;

import com.example.semisemi.dto.CartItemDTO;
import com.example.semisemi.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Slf4j
public class CartController {

    private final CartService service;

    private String cartKey(HttpSession session) {
        String sid = (session != null) ? session.getId() : null;
        log.info("[cartKey] session={}", sid);
        return sid;
    }

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> get(HttpSession session) {
        String sid = cartKey(session);
        log.info("GET /api/cart sid={}", sid);
        return ResponseEntity.ok(service.getCart(sid));
    }

    @PostMapping("/items")
    public ResponseEntity<List<CartItemDTO>> add(HttpSession session,
                                                 @RequestParam Long productId,
                                                 @RequestParam(defaultValue = "1") int deltaQty) {
        String sid = cartKey(session);
        log.info("POST /api/cart/items sid={} productId={} deltaQty={}", sid, productId, deltaQty);
        return ResponseEntity.ok(service.add(sid, productId, deltaQty));
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> remove(HttpSession session, @PathVariable Long productId) {
        String sid = cartKey(session);
        log.info("DELETE /api/cart/items/{} sid={}", productId, sid);
        service.remove(sid, productId);
        return ResponseEntity.noContent().build(); // 204
    }

    @DeleteMapping
    public ResponseEntity<Void> clear(HttpSession session) {
        String sid = cartKey(session);
        log.info("DELETE /api/cart sid={}", sid);
        service.clear(sid);
        return ResponseEntity.noContent().build(); // 204
    }
}
