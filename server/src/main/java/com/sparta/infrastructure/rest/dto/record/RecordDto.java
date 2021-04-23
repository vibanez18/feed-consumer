package com.sparta.infrastructure.rest.dto.record;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class RecordDto {

    private Long recordIndex;
    private Long timestamp;
    private String city;
    private Integer numberBytesSensorData;
    private List<SensorDto> sensorsDataList;
    private Long crc32SensorsData;
}
