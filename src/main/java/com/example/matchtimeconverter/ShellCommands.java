package com.example.matchtimeconverter;

import com.example.matchtimeconverter.service.MatchTimeTransformerService;
import com.example.matchtimeconverter.util.FileReaderUtil;
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
  public void convertMatchTimesAndPrint(String path) {
    fileReaderUtil.readFile(path)
        .stream()
        .map(matchTimeTransformerService::transformMatchTime)
        .forEach(System.out::println);
  }

}
