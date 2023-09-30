package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JokeDto {
  private String id;
  private String type;
  private String setup;
  private String punchline;
}
