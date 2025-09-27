package com.example.semisemi.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CartItemDTO {
    private Long productId;
    private String name;
    private String imageUrl;
    private int qty;
    private int price;
}
