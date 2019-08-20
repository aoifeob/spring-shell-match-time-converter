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
public class FormattingServiceTest {

  @Autowired
  private FormattingService formattingService;

  @Test
  public void padValueToTwoDigitsPadsSingleDigit(){
    //if value is one digit, method should pad it to two digits by adding a leading 0
    assertEquals("Value should have been padded with a leading 0", "02",formattingService.padSingleDigitValueToTwoDigits(2));
  }

  @Test
  public void padValueToTwoDigitsDoesNotPadDoubleDigits(){
    //if value is two digits, method should not change it
    assertEquals("Value should be unchanged", "10",formattingService.padSingleDigitValueToTwoDigits(10));
  }

  @Test
  public void padValueToThreeDigitsDoesNotPadDoubleDigits(){
    //if value is two digits, method should not change it
    assertEquals("Value should be unchanged", "234",formattingService.padSingleDigitValueToTwoDigits(234));
  }

}
