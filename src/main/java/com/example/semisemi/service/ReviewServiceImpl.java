package com.example.semisemi.service;

import com.example.semisemi.dto.ReviewDTO;
import com.example.semisemi.repository.ReviewRepository;
import com.example.semisemi.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository repository;

  ReviewDTO toDTO(ReviewVO vo){
    ReviewDTO reviewDTO = new ReviewDTO();
    reviewDTO.setId(vo.getId());
    reviewDTO.setUser_name(vo.getUser_name());
    reviewDTO.setTitle(vo.getTitle());
    reviewDTO.setContent(vo.getContent());
    return  reviewDTO;
  }

  ReviewVO toVO(ReviewDTO dto){
    ReviewVO reviewVO = new ReviewVO();
    reviewVO.setId(dto.getId());
    reviewVO.setUser_name(dto.getUser_name());
    reviewVO.setTitle(dto.getTitle());
    reviewVO.setContent(dto.getContent());
    return reviewVO;
  }

  @Override
  public List<ReviewDTO> list() {
    return repository.findAll().stream().map(i->toDTO(i)).toList();
  }

  @Override
  public void add(ReviewDTO dto) {
    repository.save(toVO(dto));
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Override
  public void update(ReviewDTO dto) {
    log.info("확인 {}",dto);
    repository.save(toVO(dto));
  }
}
