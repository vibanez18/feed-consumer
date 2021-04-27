package com.sparta.infrastructure.rest.controller;

import com.sparta.application.service.RecordDto;
import com.sparta.application.service.RecordService;
import com.sparta.infrastructure.rest.mapper.ByteArrayMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

  private final ByteArrayMapper byteArrayMapper;
  private final RecordService recordService;

  public MainController(ByteArrayMapper byteArrayMapper, RecordService recordService) {
    this.byteArrayMapper = byteArrayMapper;
    this.recordService = recordService;
  }

  @PostMapping("/load/{provider}")
  public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) throws IOException {
    final List<RecordDto> dtos = byteArrayMapper.byteArrayToRecord(content);
    this.recordService.loadRecords(dtos, provider);

    return dtos != null ? dtos.size() : 0;
  }

  @GetMapping("/data/{provider}/total")
  public int total(@PathVariable("provider") String provider) {
    return this.recordService.totalMessagesByProvider(provider);
  }
}