package com.example.matchtimeconverter.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
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
public class FileReaderUtilTest {

  @Autowired
  private FileReaderUtil fileReaderUtil;

  @Test
  public void testFileReaderReadsFileLineByLine(){
    List<String> expectedOutput = Arrays.asList("[PM] 0:00.000", "[H1] 10:30.200");
    assertEquals("", expectedOutput, fileReaderUtil.readFile("src/test/resources/test.data"));
  }

  @Test
  public void testFileReaderReturnsEmptyListWhenFileNotFound(){
    List<String> expectedOutput = Collections.emptyList();
    assertEquals("", expectedOutput, fileReaderUtil.readFile("nonexistentfile.data"));
  }
}
