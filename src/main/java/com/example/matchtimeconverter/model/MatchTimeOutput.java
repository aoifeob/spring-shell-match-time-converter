package com.example.matchtimeconverter.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MatchTimeOutput {

  private final int minutes;
  private final int seconds;
  private final int additionalMinutes;
  private final int additionalSeconds;
  private final String period;

}
