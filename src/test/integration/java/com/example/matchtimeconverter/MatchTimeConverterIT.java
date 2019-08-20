package com.example.matchtimeconverter;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import com.example.matchtimeconverter.service.MatchTimeTransformerService;
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
public class MatchTimeConverterIT {

  @Autowired
  private MatchTimeTransformerService matchTimeTransformerService;

  @Test
  public void convertValidMatchTimes() {
    assertEquals("Output should match expected value", "00:00 - PRE_MATCH",
        matchTimeTransformerService.transformMatchTime("[PM] 0:00.000"));
    assertEquals("Output should match expected value", "30:00 - FIRST_HALF",
        matchTimeTransformerService.transformMatchTime("[H1] 30:00.000"));
    assertEquals("Output should match expected value", "45:00 - HALF_TIME",
        matchTimeTransformerService.transformMatchTime("[HT] 45:00.000"));
    assertEquals("Output should match expected value", "60:00 - SECOND_HALF",
        matchTimeTransformerService.transformMatchTime("[H2] 60:00.000"));
    assertEquals("Output should match expected value", "90:00 - FULL_TIME",
        matchTimeTransformerService.transformMatchTime("[FT] 90:00.000"));
    assertEquals("Output should match expected value", "30:02 - FIRST_HALF",
        matchTimeTransformerService.transformMatchTime("[H1] 30:02.000"));
    assertEquals("Output should match expected value", "65:00 - SECOND_HALF",
        matchTimeTransformerService.transformMatchTime("[H2] 65:00.153"));
    assertEquals("Output should match expected value", "65:01 - SECOND_HALF",
        matchTimeTransformerService.transformMatchTime("[H2] 65:00.823"));
    assertEquals("Output should match expected value", "45:00 + 05:06 - FIRST_HALF",
        matchTimeTransformerService.transformMatchTime("[H1] 50:06.000"));
    assertEquals("Output should match expected value", "90:00 + 01:43 - SECOND_HALF",
        matchTimeTransformerService.transformMatchTime("[H2] 91:43.000"));
    assertEquals("Output should match expected value", "45:00 + 00:00 - FIRST_HALF",
        matchTimeTransformerService.transformMatchTime("[H1] 45:00.001"));
  }

  @Test
  public void convertInvalidMatchTimes() {
    List<String> invalidInputs = asList("[PM] -1:00.000",
        "[H1] 45:60.000",
        "[HM] 45:00.000",
        "[H1] 300:00.000",
        "[P1] 10.50.100",
        "90:00.000",
        "[H1]",
        "ABCD",
        "23",
        "-100",
        "",
        null);
    for (String invalidInput : invalidInputs) {
      assertEquals("Output should match expected value", "INVALID",
          matchTimeTransformerService.transformMatchTime(invalidInput));
    }
  }
}
