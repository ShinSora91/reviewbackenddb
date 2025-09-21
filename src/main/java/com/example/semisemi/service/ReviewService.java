package com.example.semisemi.service;

import com.example.semisemi.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
  public List<ReviewDTO> list();
  public void add(ReviewDTO dto);
  public void delete(Long id);
  public void update(ReviewDTO dto);
}
