package com.sparta.application.mapper;

import com.sparta.application.mapper.record.SensorMapper;
import com.sparta.application.service.SensorDto;
import com.sparta.domain.record.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SensorMapperTest {

    @InjectMocks
    SensorMapper testee;

    @Test
    void fromEntity_WhenCalled_ThenDto() {
        Sensor sensor = Sensor.builder()
                .withId("id")
                .withMeasure(123456789)
                .build();

        final SensorDto sensorDto = testee.fromEntity(sensor);

        assertThat(sensorDto.getId()).isEqualTo("id");
        assertThat(sensorDto.getMeasure()).isEqualTo(123456789);
    }

    @Test
    void fromDto_WhenCalled_ThenEntity() {
        SensorDto sensorDto = SensorDto.builder()
                .withId("id")
                .withMeasure(123456789)
                .build();

        final Sensor sensor = testee.fromDto(sensorDto);

        assertThat(sensor.getId()).isEqualTo("id");
        assertThat(sensor.getMeasure()).isEqualTo(123456789);

    }

}
