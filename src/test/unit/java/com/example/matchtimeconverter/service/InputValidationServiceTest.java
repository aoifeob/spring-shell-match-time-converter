package com.example.matchtimeconverter.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class InputValidationServiceTest {

  @Autowired
  private InputValidationService inputValidationService;

  @Test
  public void validateMatchTimeForValidInputs() {
    List<String> validTestInputs = Arrays.asList("[PM] 0:00.000",
        "[H1] 30:00.000",
        "[HT] 45:00.000",
        "[H2] 60:00.000",
        "[FT] 90:00.000",
        "[H1] 30:02.000",
        "[H2] 65:00.153",
        "[H2] 65:00.823",
        "[H1] 50:06.000",
        "[H2] 91:43.000",
        "[H2] 101:43.000");
    for (String validTestInput : validTestInputs) {
      assertTrue("Result should match the expected boolean value",
          inputValidationService.isValidMatchTimeInputString(validTestInput));
    }
  }

  @Test
  public void validateMatchTimeForInvalidInputs() {
    List<String> invalidTestInputs = Arrays.asList("[H2] 201:43.000",
        "[PM] -1:00.000",
        "[H1] 300:00.000",
        "[HF] 45:00.000",
        "[H1] 45:60.000",
        "[H2] 60:72:000",
        "90:00.000",
        "[H1]",
        "ABCD",
        "23",
        "[P1] 10.50.100",
        "-100",
        "",
        null);
    for (String invalidTestInput : invalidTestInputs) {
      assertFalse("Result should match the expected boolean value",
          inputValidationService.isValidMatchTimeInputString(invalidTestInput));
    }
  }

}
