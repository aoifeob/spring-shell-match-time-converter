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
public class MatchTimeTransformerServiceTest {

  @Autowired
  private MatchTimeTransformerService matchTimeTransformerService;


  @Test
  public void transformMatchTimeReturnsInvalid(){
    assertEquals("Result should be invalid", "INVALID", matchTimeTransformerService.transformMatchTime("ABC"));
  }

  @Test
  public void transformMatchTime(){
    assertEquals("Result should be invalid", "12:03 - FIRST_HALF", matchTimeTransformerService.transformMatchTime("[H1] 12:03.276"));
  }

}
