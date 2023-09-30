package com.example.demo.service;

import com.example.demo.dto.JokeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class JokeServiceImplTest {

  @Mock
  CallService callService;
  @InjectMocks
  JokeServiceImpl forecastService;

  @Test
  public void test_getOneJoke() throws InterruptedException {
    Mockito.when(callService.callForOneJoke()).thenReturn(CompletableFuture.completedFuture(new JokeDto()));

    forecastService.getJokes(1);

    Mockito.verify(callService, times(1)).callForOneJoke();
  }


  @Test
  public void test_getMoreJoke() throws InterruptedException {
    Mockito.when(callService.callForTenJoke()).thenReturn(CompletableFuture.completedFuture(new ArrayList<>()));

    forecastService.getJokes(78);

    Mockito.verify(callService, times(0)).callForOneJoke();
    Mockito.verify(callService, times(8)).callForTenJoke();
  }
}
