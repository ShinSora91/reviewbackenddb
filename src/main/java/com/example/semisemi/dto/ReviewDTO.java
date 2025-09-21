package com.example.semisemi.dto;

import jakarta.persistence.*;
import lombok.*;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
  private Long id;
  private String user_name;
  private String title;
  private String content;
}
