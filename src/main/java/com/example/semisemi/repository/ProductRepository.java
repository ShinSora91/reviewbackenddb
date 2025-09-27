// ProductRepository.java
package com.example.semisemi.repository;

import com.example.semisemi.vo.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {}
