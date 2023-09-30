package com.example.demo.service;

import com.example.demo.dto.JokeDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CallService {
  CompletableFuture<JokeDto> callForOneJoke() throws InterruptedException;
  CompletableFuture<List<JokeDto>> callForTenJoke() throws InterruptedException;

  }
