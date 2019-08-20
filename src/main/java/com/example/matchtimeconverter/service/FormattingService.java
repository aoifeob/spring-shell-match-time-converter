package com.example.matchtimeconverter.service;

import org.springframework.stereotype.Service;

@Service
public final class FormattingService {

  public String padSingleDigitValueToTwoDigits(int valueForPadding){
    return valueForPadding < 10
        ? String.format("%02d", valueForPadding)
        : String.valueOf(valueForPadding);
  }

}
