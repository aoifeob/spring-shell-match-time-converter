package com.example.matchtimeconverter.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class FileReaderUtil {

  private static final Logger log = LoggerFactory.getLogger(FileReaderUtil.class);

  public List<String> readFile(String filePath) {

    try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
      return fileStream.collect(Collectors.toList());
    }
    catch (IOException e) {
      log.error("Error while reading file. Exception: ", e);
    }

    return Collections.emptyList();
  }

}
