package com.example.matchtimeconverter.service;

import static org.junit.Assert.assertEquals;

import com.example.matchtimeconverter.model.MatchTimeInput;
import javax.validation.constraints.Null;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MatchTimeInputParsingServiceTest {

  @Autowired
  private MatchTimeInputParsingService matchTimeInputParsingService;

  @Test
  public void parseStringToMatchTimeInput() {
    String input = "[H1] 30:45.162";
    MatchTimeInput matchTimeInput = matchTimeInputParsingService.parseStringToMatchTimeInput(input);
    assertEquals("H1", matchTimeInput.getPeriod());
    assertEquals(30, matchTimeInput.getMinutes());
    assertEquals(45.162d, matchTimeInput.getSeconds(), 0);
  }

  @Test (expected = NullPointerException.class)
  public void parseNullStringToMatchTimeInput() {
    matchTimeInputParsingService.parseStringToMatchTimeInput(null);
  }

}
