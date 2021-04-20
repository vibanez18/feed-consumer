package com.sparta.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController {

  @PostMapping("/load/{provider}")
  public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) throws IOException {
    return 0;
  }

  @GetMapping("/data/{provider}/total")
  public int total(@PathVariable("provider") String provider) {
    return 0;
  }

}
