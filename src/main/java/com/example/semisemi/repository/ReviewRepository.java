package com.example.semisemi.repository;

import com.example.semisemi.vo.ReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewVO, Long> {
}
