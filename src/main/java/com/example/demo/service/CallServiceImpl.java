package com.example.demo.service;

import com.example.demo.dto.JokeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService{

  private final RestTemplate restTemplate;

  private String urlForTenJokes = "https://official-joke-api.appspot.com/random_ten";
  private String urlForOneJoke = "https://official-joke-api.appspot.com/random_joke";

  @Async
  public CompletableFuture<JokeDto> callForOneJoke() {

    JokeDto jokeDto = restTemplate.getForObject(urlForOneJoke, JokeDto.class);

    return CompletableFuture.completedFuture(jokeDto);
  }

  @Override
  @Async
  public CompletableFuture<List<JokeDto>> callForTenJoke() {

    ResponseEntity<JokeDto[]> responseEntity = restTemplate.getForEntity(urlForTenJokes, JokeDto[].class);
    JokeDto[] objects = responseEntity.getBody();
    if (objects == null) {
      throw new IllegalArgumentException("jokes are not available");
    }
    List<JokeDto> arrayList = Arrays.asList(objects);

    return CompletableFuture.completedFuture(arrayList);
  }
}
