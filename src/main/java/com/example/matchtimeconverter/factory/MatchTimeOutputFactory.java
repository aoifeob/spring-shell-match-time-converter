package com.example.matchtimeconverter.factory;

import com.example.matchtimeconverter.model.MatchTimeInput;
import com.example.matchtimeconverter.model.MatchTimeOutput;
import com.example.matchtimeconverter.service.FormattingService;
import com.example.matchtimeconverter.service.PeriodTransformerService;
import com.example.matchtimeconverter.service.TimeTransformerService;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class MatchTimeOutputFactory {

  private static final Map<String, Integer> MAX_TIME_PER_HALF = ImmutableMap.of
      ("H1", 45,
          "H2", 90);

  private final PeriodTransformerService periodTransformerService;
  private final TimeTransformerService timeTransformerService;
  private final FormattingService formattingService;

  @Autowired
  public MatchTimeOutputFactory(PeriodTransformerService periodTransformerService,
      TimeTransformerService timeTransformerService,
      FormattingService formattingService){
    this.periodTransformerService = periodTransformerService;
    this.timeTransformerService = timeTransformerService;
    this.formattingService = formattingService;
  }

  private boolean hasAdditionalTime(MatchTimeInput matchTimeInput){
    if (MAX_TIME_PER_HALF.containsKey(matchTimeInput.getPeriod())) {
      int maxTime = MAX_TIME_PER_HALF.get(matchTimeInput.getPeriod());
      return matchTimeInput.getMinutes() > maxTime ||
          (matchTimeInput.getMinutes() == maxTime && matchTimeInput.getSeconds() > 0.000d);
    }
    return false;
  }

  public MatchTimeOutput transformInputToOutput(MatchTimeInput matchTimeInput){
    MatchTimeOutput.MatchTimeOutputBuilder matchTimeOutput = MatchTimeOutput.builder();
    matchTimeOutput.period(periodTransformerService.getLongFormPeriod(matchTimeInput.getPeriod()));

    if (hasAdditionalTime(matchTimeInput)){
      matchTimeOutput.hasAdditionalTime(true);
      matchTimeOutput.minutes(MAX_TIME_PER_HALF.get(matchTimeInput.getPeriod()));
      matchTimeOutput.additionalMinutes(
          matchTimeInput.getMinutes() - MAX_TIME_PER_HALF.get(matchTimeInput.getPeriod()));
      matchTimeOutput
          .additionalSeconds(timeTransformerService.roundSeconds(matchTimeInput.getSeconds()));
    }
    else {
      matchTimeOutput.minutes(matchTimeInput.getMinutes());
      matchTimeOutput.seconds(timeTransformerService.roundSeconds(matchTimeInput.getSeconds()));
    }

    return matchTimeOutput.build();
  }

  public String getMatchTimeOutputAsString(MatchTimeOutput matchTimeOutput){
    StringBuilder output = new StringBuilder();
    output.append(formattingService.padSingleDigitValueToTwoDigits(matchTimeOutput.getMinutes()))
        .append(":")
        .append(formattingService.padSingleDigitValueToTwoDigits(matchTimeOutput.getSeconds()));
    if (matchTimeOutput.hasAdditionalTime()) {
      output.append(" + ")
          .append(formattingService
              .padSingleDigitValueToTwoDigits(matchTimeOutput.getAdditionalMinutes()))
          .append(":")
          .append(formattingService
              .padSingleDigitValueToTwoDigits(matchTimeOutput.getAdditionalSeconds()));
    }
    output.append(" - ")
        .append(matchTimeOutput.getPeriod());
    return output.toString();
  }

}
