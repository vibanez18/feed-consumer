package com.sparta.application.mapper.record;

import com.sparta.application.mapper.AbstractMapper;
import com.sparta.application.service.SensorDto;
import com.sparta.domain.record.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper extends AbstractMapper<Sensor, SensorDto> {

    @Override
    public SensorDto fromEntity(Sensor entity) {
        return SensorDto.builder()
                .withId(entity.getId())
                .withMeasure(entity.getMeasure())
                .build();
    }

    @Override
    public Sensor fromDto(SensorDto dto) {
        return Sensor.builder()
                .withId(dto.getId())
                .withMeasure(dto.getMeasure())
                .build();
    }
}
