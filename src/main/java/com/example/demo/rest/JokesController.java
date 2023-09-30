package com.example.demo.rest;


import com.example.demo.dto.JokeDto;
import com.example.demo.service.JokeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
public class JokesController {

  private final JokeService forecastService;

  @GetMapping("/jokes")
  public ResponseEntity<List<JokeDto>> getCities(@RequestParam(required = false, defaultValue = "5") Integer size) {
    log.info("got request with param size: " + size);
    List<JokeDto> response = forecastService.getJokes(size);
    return ResponseEntity.of(Optional.of(response));
  }
}
