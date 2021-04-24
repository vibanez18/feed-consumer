package com.sparta.application.mapper.record;

import com.sparta.application.mapper.AbstractMapper;
import com.sparta.application.service.RecordDto;
import com.sparta.application.service.SensorDto;
import com.sparta.domain.record.Record;
import com.sparta.domain.record.Sensor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordMapper extends AbstractMapper<Record, RecordDto> {

    private final SensorMapper sensorMapper;

    public RecordMapper(SensorMapper sensorMapper) {
        this.sensorMapper = sensorMapper;
    }

    @Override
    public RecordDto fromEntity(Record entity) {
        final List<SensorDto> sensorDtos = sensorMapper.fromEntities(entity.getSensorsDataList());

        return RecordDto.builder()
                .withCity(entity.getCity())
                .withRecordIndex(entity.getRecordIndex())
                .withCrc32SensorsData(entity.getCrc32SensorsData())
                .withTimestamp(entity.getTimestamp())
                .withNumberBytesSensorData(entity.getNumberBytesSensorData())
                .withSensorsDataList(sensorDtos)
                .build();
    }

    @Override
    public Record fromDto(RecordDto dto) {
        final List<Sensor> sensors = sensorMapper.fromDtos(dto.getSensorsDataList());

        return Record.builder()
                .withCity(dto.getCity())
                .withCrc32SensorsData(dto.getCrc32SensorsData())
                .withRecordIndex(dto.getRecordIndex())
                .withNumberBytesSensorData(dto.getNumberBytesSensorData())
                .withTimestamp(dto.getTimestamp())
                .withSensorsDataList(sensors)
                .build();
    }
}
