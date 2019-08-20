package com.example.matchtimeconverter.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TimeTransformerServiceTest {

  @Autowired
  private TimeTransformerService timeTransformerService;

  @Test
  public void roundSecondsRoundsUp(){
    assertEquals("Seconds should have been rounded up", 13, timeTransformerService.roundSeconds(12.500d));
  }

  @Test
  public void roundSecondsRoundsDown(){
    assertEquals("Seconds should have been rounded down", 12, timeTransformerService.roundSeconds(12.499d));
  }

}
