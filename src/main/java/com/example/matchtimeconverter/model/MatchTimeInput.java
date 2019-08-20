package com.example.matchtimeconverter.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MatchTimeInput {

  private final String period;
  private final int minutes;
  private final double seconds;

}
