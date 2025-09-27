// ProductEntity.java
package com.example.semisemi.vo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name="product")
public class ProductEntity {
    @Id
    private Long id;
    private String name;
    private int price;
    private String imageUrl;
}
