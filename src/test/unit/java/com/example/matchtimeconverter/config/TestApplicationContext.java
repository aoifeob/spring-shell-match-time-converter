package com.example.matchtimeconverter.config;

import com.example.matchtimeconverter.MatchTimeConverterApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Import(MatchTimeConverterApplication.class)
@Profile("test")
public class TestApplicationContext {

}
