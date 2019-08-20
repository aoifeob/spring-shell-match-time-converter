package com.example.matchtimeconverter;

import com.example.matchtimeconverter.service.MatchTimeTransformerService;
import com.example.matchtimeconverter.util.FileReaderUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public final class ShellCommands {

  private final FileReaderUtil fileReaderUtil;
  private final MatchTimeTransformerService matchTimeTransformerService;

  @Autowired
  public ShellCommands(FileReaderUtil fileReaderUtil, MatchTimeTransformerService matchTimeTransformerService){
    this.fileReaderUtil = fileReaderUtil;
    this.matchTimeTransformerService = matchTimeTransformerService;
  }

  @ShellMethod(value = "Convert the match times in the file provided.", key = "convert")
  public List<String> convertMatchTimesAndPrint(String path) {
    return fileReaderUtil.readFile(path)
        .stream()
        .map(matchTimeTransformerService::transformMatchTime)
        .collect(Collectors.toList());
  }

}
