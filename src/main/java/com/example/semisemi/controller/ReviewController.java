package com.example.semisemi.controller;

import com.example.semisemi.dto.ReviewDTO;
import com.example.semisemi.service.ReviewService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(
    origins = {"http://localhost:3000"},
    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS},
    allowedHeaders = "*"
)
public class ReviewController {
  @Autowired
  private ReviewService service;

  @PostMapping("/add")
  public ResponseEntity<String> add(@RequestBody ReviewDTO dto){
    log.info("리뷰가 등록되었습니다 {}", dto);
    service.add(dto);
    return ResponseEntity.ok("성공");
  }

  @GetMapping("/list")
  public ResponseEntity<List<ReviewDTO>> list() {
    return ResponseEntity.ok(service.list());
  }

  @GetMapping("/delete")
  public ResponseEntity<List<ReviewDTO>> delete(@RequestParam() Long id){
    log.info("리뷰가 삭제되었습니다 {}", id);
    service.delete(id);
    List<ReviewDTO> list = new ArrayList<>();
    service.list().forEach(i->list.add(i));

    return ResponseEntity.ok(list);
  }
  @PutMapping("/update")
  public ResponseEntity<String> update(@RequestBody ReviewDTO dto){
    log.info("리뷰가 수정되었습니다 {}", dto);
    service.update(dto);

    return ResponseEntity.ok("성공");
  }
}
