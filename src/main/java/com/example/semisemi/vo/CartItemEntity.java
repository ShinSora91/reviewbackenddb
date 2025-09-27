// CartItemEntity.java
package com.example.semisemi.vo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name="cart_item")
public class CartItemEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String sessionId;   // ✅ 세션 기반 장바구니 키

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @Column(nullable=false)
    private int qty;

    @Column(nullable=false)
    private int priceSnap;
}
