package com.sparta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpartaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpartaApplication.class, args);
  }


}
