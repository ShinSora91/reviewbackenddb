package com.example.semisemi;

import com.example.semisemi.dto.ReviewDTO;
import com.example.semisemi.service.ReviewService;
import com.example.semisemi.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReviewTests {
  @Autowired
  private ReviewService service;

  @Test
  public void addReview() {
    for(int i=0; i<5; i++){
      ReviewDTO review = new ReviewDTO();
//      review.setId(i+1L);
      review.setUser_name("신소라"+i);
      review.setTitle("리뷰"+i);
      review.setContent("리뷰내용"+i);
      service.add(review);
      log.info("추가된 데이터 {}", review);
    }
  }

  @Test
  public void getReview() {
    service.list();
    log.info("전체 조회 {}", service.list());
  }

  @Test
  public void updateReview() {
    ReviewDTO review = new ReviewDTO();
    review.setId(5L);
    review.setUser_name("피카츄");
    review.setTitle("포켓몬스터");
    review.setContent("피카츄가 진화하면 라이츄");
    service.update(review);
  }

  @Test
  public void deleteReview() {
    service.delete(12L);
  }
}
