package com.sparta.infrastructure.rest.controller;

import com.sparta.application.service.RecordDto;
import com.sparta.infrastructure.rest.mapper.ByteArrayMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

  private final ByteArrayMapper byteArrayMapper;

  public MainController(ByteArrayMapper byteArrayMapper) {
    this.byteArrayMapper = byteArrayMapper;
  }

  @PostMapping("/load/{provider}")
  public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) throws IOException {
    final List<RecordDto> recordDtos = byteArrayMapper.byteArrayToRecord(content);
    return 0;
  }

  @GetMapping("/data/{provider}/total")
  public int total(@PathVariable("provider") String provider) {
    return 0;
  }
}
