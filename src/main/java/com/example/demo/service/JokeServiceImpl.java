package com.example.demo.service;

import com.example.demo.dto.JokeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class JokeServiceImpl implements JokeService {

  private final CallService callService;

  @Override
  public List<JokeDto> getJokes(int jokeSize) {
    if (jokeSize > 100) {
      throw new IllegalArgumentException("cannot be more that 100 jokes");
    }

    try {
      if (jokeSize == 1) {
        Future<JokeDto> jokeDtoFuture = callService.callForOneJoke();
        return List.of(jokeDtoFuture.get());
      } else {
        return getMultipleJokes(jokeSize);
      }
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException("something went wrong");
    }
  }

  private List<JokeDto> getMultipleJokes(int jokeSize) throws InterruptedException, ExecutionException {
    List<CompletableFuture<List<JokeDto>>> futureList = new ArrayList<>(jokeSize);
    for (int i = 0; i < jokeSize; i += 10) {
      CompletableFuture<List<JokeDto>> listCompletableFuture = callService.callForTenJoke();
      futureList.add(listCompletableFuture);
    }

    List<JokeDto> jokeDtosResponse = new ArrayList<>();
    for (CompletableFuture<List<JokeDto>> future: futureList) {
      jokeDtosResponse.addAll(future.get());
    }
    return jokeDtosResponse.stream().limit(jokeSize).collect(Collectors.toList());
  }
}
