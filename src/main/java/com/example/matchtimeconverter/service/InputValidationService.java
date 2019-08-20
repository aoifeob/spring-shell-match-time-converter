package com.example.matchtimeconverter.service;

import java.util.regex.Pattern;
import lombok.experimental.PackagePrivate;
import org.springframework.stereotype.Service;

@Service
public final class InputValidationService {

  @SuppressWarnings("RegExpRedundantEscape") //suppressed false positive warning
  private final Pattern validMatchTimeInputPattern = Pattern.compile("^\\[(PM|H1|HT|H2|FT)\\]\\s[0-1]?[0-9]{1,2}:[0-5][0-9]\\.[0-9]{1,3}$");

  @PackagePrivate
  boolean isValidMatchTimeInputString(String input) {
    return input != null && validMatchTimeInputPattern.matcher(input).matches();
  }

}
