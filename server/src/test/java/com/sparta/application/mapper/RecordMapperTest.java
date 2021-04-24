package com.sparta.application.mapper;

import com.sparta.application.mapper.record.RecordMapper;
import com.sparta.application.mapper.record.SensorMapper;
import com.sparta.application.service.RecordDto;
import com.sparta.application.service.SensorDto;
import com.sparta.domain.record.Record;
import com.sparta.domain.record.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecordMapperTest {

    @InjectMocks
    RecordMapper testee;

    @Mock
    SensorMapper sensorMapper;

    @Test
    void fromEntity_WhenCalled_ThenDto() {
        final long time = new Date().getTime();
        final List<Sensor> sensorList = Collections.singletonList(mock(Sensor.class));
        Record record = Record.builder()
                .withRecordIndex(1L)
                .withCity("city")
                .withSensorsDataList(sensorList)
                .withCrc32SensorsData(123456789L)
                .withNumberBytesSensorData(1234567890)
                .withTimestamp(time)
                .build();

        when(sensorMapper.fromEntities(anyList())).thenReturn(anyList());

        final RecordDto recordDto = testee.fromEntity(record);

        assertThat(recordDto.getRecordIndex()).isEqualTo(Long.valueOf(1));
        assertThat(recordDto.getCity()).isEqualTo("city");
        assertThat(recordDto.getCrc32SensorsData()).isEqualTo(Long.valueOf(123456789));
        assertThat(recordDto.getNumberBytesSensorData()).isEqualTo(1234567890);
        assertThat(recordDto.getTimestamp()).isEqualTo(time);
    }

    @Test
    void fromDto_WhenCalled_ThenEntity() {
        final long time = new Date().getTime();
        final List<SensorDto> sensorList = Collections.singletonList(mock(SensorDto.class));
        RecordDto recordDto = RecordDto.builder()
                .withRecordIndex(1L)
                .withCity("city")
                .withSensorsDataList(sensorList)
                .withCrc32SensorsData(123456789L)
                .withNumberBytesSensorData(1234567890)
                .withTimestamp(time)
                .build();

        when(sensorMapper.fromDtos(anyList())).thenReturn(anyList());

        final Record record = testee.fromDto(recordDto);

        assertThat(record.getRecordIndex()).isEqualTo(Long.valueOf(1));
        assertThat(record.getCity()).isEqualTo("city");
        assertThat(record.getCrc32SensorsData()).isEqualTo(Long.valueOf(123456789));
        assertThat(record.getNumberBytesSensorData()).isEqualTo(1234567890);
        assertThat(record.getTimestamp()).isEqualTo(time);
    }

}
