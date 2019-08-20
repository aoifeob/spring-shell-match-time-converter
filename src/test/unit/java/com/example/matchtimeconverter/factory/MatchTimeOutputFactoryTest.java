package com.example.matchtimeconverter.factory;

import static org.junit.Assert.assertEquals;

import com.example.matchtimeconverter.model.MatchTimeOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MatchTimeOutputFactoryTest {

  @Autowired
  private MatchTimeOutputFactory matchTimeOutputFactory;

  @Test
  public void getMatchTimeOutPutAsStringIsCorrect(){
    MatchTimeOutput actualOutput = MatchTimeOutput.builder()
        .minutes(47)
        .seconds(12)
        .period("SECOND_HALF")
        .build();
    String expectedOutput = "47:12 - SECOND_HALF";
    assertEquals("String output should match expected value", expectedOutput, matchTimeOutputFactory.getMatchTimeOutputAsString(actualOutput));
  }

  @Test
  public void getMatchTimeOutputAsStringWithAdditionalMinutesIsCorrect(){
    MatchTimeOutput actualOutput = MatchTimeOutput.builder()
        .minutes(45)
        .seconds(00)
        .additionalMinutes(1)
        .hasAdditionalTime(true)
        .period("FIRST_HALF")
        .build();
    String expectedOutput = "45:00 + 01:00 - FIRST_HALF";
    assertEquals("String output should match expected value", expectedOutput, matchTimeOutputFactory.getMatchTimeOutputAsString(actualOutput));
  }

  @Test
  public void getMatchTimeOutputAsStringWithAdditionalSecondsIsCorrect(){
    MatchTimeOutput actualOutput = MatchTimeOutput.builder()
        .minutes(45)
        .seconds(00)
        .additionalSeconds(5)
        .hasAdditionalTime(true)
        .period("FIRST_HALF")
        .build();
    String expectedOutput = "45:00 + 00:05 - FIRST_HALF";
    assertEquals("String output should match expected value", expectedOutput, matchTimeOutputFactory.getMatchTimeOutputAsString(actualOutput));
  }

  @Test
  public void getMatchTimeOutputAsStringWithAdditionalMinutesAndSecondsIsCorrect(){
    MatchTimeOutput actualOutput = MatchTimeOutput.builder()
        .minutes(45)
        .seconds(00)
        .additionalMinutes(1)
        .additionalSeconds(30)
        .hasAdditionalTime(true)
        .period("FIRST_HALF")
        .build();
    String expectedOutput = "45:00 + 01:30 - FIRST_HALF";
    assertEquals("String output should match expected value", expectedOutput, matchTimeOutputFactory.getMatchTimeOutputAsString(actualOutput));
  }

}
