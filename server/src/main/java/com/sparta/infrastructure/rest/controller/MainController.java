package com.sparta.infrastructure.rest.controller;

import com.sparta.infrastructure.rest.dto.record.RecordDto;
import com.sparta.infrastructure.rest.mapper.record.RecordMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

  private final RecordMapper recordMapper;

  public MainController(final RecordMapper recordMapper) {
    this.recordMapper = recordMapper;
  }

  @PostMapping("/load/{provider}")
  public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) throws IOException {
    final List<RecordDto> recordDtos = recordMapper.byteArrayToRecord(content);
    return 0;
  }

  @GetMapping("/data/{provider}/total")
  public int total(@PathVariable("provider") String provider) {
    return 0;
  }
}
