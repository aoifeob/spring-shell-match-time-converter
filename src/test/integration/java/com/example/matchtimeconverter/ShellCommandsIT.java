package com.example.matchtimeconverter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.Shell;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ShellCommandsIT {

  @Autowired
  private Shell shell;

  @Test
  public void testConvertShellCommandFileNotFound() {
    Map<String, MethodTarget> commands = shell.listCommands();
    MethodTarget convertCommand = commands.get("convert");
    assertNotNull("Convert command should exist", convertCommand);
    assertThat(convertCommand.getMethod(), is(ReflectionUtils.findMethod(ShellCommands.class, "convertMatchTimesAndPrint", String.class)));
    assertTrue("Command should be available", convertCommand.getAvailability().isAvailable());
    assertEquals("Result of shell command should be an empty array", Collections.emptyList(),
        shell.evaluate(
            () -> "convert nonExistentFile.data"));
  }

  @Test
  public void testConvertShellCommandFileExists() {
    String file = new File("src/test/resources/test.data").toString();
    Map<String, MethodTarget> commands = shell.listCommands();
    MethodTarget convertCommand = commands.get("convert");
    assertNotNull("Convert command should exist", convertCommand);
    assertThat(convertCommand.getMethod(), is(ReflectionUtils
        .findMethod(ShellCommands.class, "convertMatchTimesAndPrint", String.class)));
    assertTrue("Command should be available", convertCommand.getAvailability().isAvailable());
    List<String> expectedOutput = Arrays
        .asList("00:00 - PRE_MATCH", "10:30 - FIRST_HALF", "INVALID");
    assertEquals("Result of shell command should be an empty array", expectedOutput, shell.evaluate(
        () -> "convert src/test/resources/test.data"));
  }

}
