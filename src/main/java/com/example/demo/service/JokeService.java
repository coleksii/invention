package com.example.demo.service;

import com.example.demo.dto.JokeDto;

import java.util.List;

public interface JokeService {

  List<JokeDto> getJokes(int i);
}
