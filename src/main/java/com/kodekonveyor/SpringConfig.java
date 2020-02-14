package com.kodekonveyor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

@SpringBootApplication
@ExcludeFromCodeCoverage("empty")
public class SpringConfig extends SpringBootServletInitializer {

  public static void main(final String[] args) {
    SpringApplication.run(SpringConfig.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(
      final SpringApplicationBuilder builder
  ) {
    return builder.sources(SpringConfig.class);
  }

}
