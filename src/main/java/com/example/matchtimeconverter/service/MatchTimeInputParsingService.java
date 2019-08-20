package com.example.matchtimeconverter.service;

import com.example.matchtimeconverter.model.MatchTimeInput;
import org.springframework.stereotype.Service;

@Service
public final class MatchTimeInputParsingService {

  MatchTimeInput parseStringToMatchTimeInput(String matchTime) {
    if (matchTime == null){
      throw new NullPointerException("Received null value while attempting to build MatchTimeInput model.");
    }
    return MatchTimeInput.builder()
        .period(getPeriodFromMatchTime(matchTime))
        .minutes(getMinutesFromMatchTime(matchTime))
        .seconds(getSecondsFromMatchTime(matchTime))
        .build();
  }

  private String getPeriodFromMatchTime(String matchTime) {
    //get substring of characters between [ and ] to retrieve the short form period
    return matchTime.substring(matchTime.indexOf('[')+1, matchTime.indexOf(']'));
  }

  private int getMinutesFromMatchTime(String matchTime) {
    //get substring of characters after ] and before :, then trim to retrieve minutes
    return Integer.parseInt(matchTime.substring(matchTime.indexOf(']')+1, matchTime.indexOf(':')).trim());
  }

  private double getSecondsFromMatchTime(String matchTime) {
    //get substring of characters after : to retrieve seconds with milliseconds after decimal point
    return Double.parseDouble(matchTime.substring(matchTime.indexOf(':')+1));
  }

}
