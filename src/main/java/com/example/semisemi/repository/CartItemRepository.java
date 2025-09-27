// CartItemRepository.java
package com.example.semisemi.repository;

import com.example.semisemi.vo.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findBySessionId(String sessionId);
    Optional<CartItemEntity> findBySessionIdAndProduct_Id(String sessionId, Long productId);
    // ✅ 여러 행 지울 수 있으니 long 또는 void 로 선언
    long deleteBySessionIdAndProduct_Id(String sessionId, Long productId);
    long deleteBySessionId(String sessionId);
}
