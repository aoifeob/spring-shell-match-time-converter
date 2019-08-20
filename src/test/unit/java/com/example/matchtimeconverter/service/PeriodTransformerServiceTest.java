package com.example.matchtimeconverter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PeriodTransformerServiceTest {

  @Autowired
  private PeriodTransformerService periodTransformerService;

  @Test
  public void getLongFormPeriodForPM() {
    assertEquals("Long form period of PM should be PRE_MATCH", "PRE_MATCH", periodTransformerService.getLongFormPeriod("PM"));
  }

  @Test
  public void getLongFormPeriodForH1() {
    assertEquals("Long form period of H1 should be FIRST_HALF", "FIRST_HALF", periodTransformerService.getLongFormPeriod("H1"));
  }

  @Test
  public void getLongFormPeriodForHT() {
    assertEquals("Long form period of HT should be HALF_TIME", "HALF_TIME", periodTransformerService.getLongFormPeriod("HT"));
  }

  @Test
  public void getLongFormPeriodForH2() {
    assertEquals("Long form period of H2 should be SECOND_HALF", "SECOND_HALF", periodTransformerService.getLongFormPeriod("H2"));
  }

  @Test
  public void getLongFormPeriodForFT() {
    assertEquals("Long form period of FT should be FULL_TIME", "FULL_TIME", periodTransformerService.getLongFormPeriod("FT"));
  }

  @Test
  public void getLongFormPeriodForKeyThatDoesNotExistReturnsNull() {
    assertNull("Long form period of FM should not exist", periodTransformerService.getLongFormPeriod("FM"));
  }
}
