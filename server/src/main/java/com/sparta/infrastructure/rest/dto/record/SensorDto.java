package com.sparta.infrastructure.rest.dto.record;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class SensorDto {

    private String id;
    private Integer measure;
}
