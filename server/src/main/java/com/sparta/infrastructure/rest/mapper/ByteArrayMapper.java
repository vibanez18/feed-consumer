package com.sparta.infrastructure.rest.mapper;

import com.google.common.base.Preconditions;
import com.sparta.application.service.RecordDto;
import com.sparta.application.service.SensorDto;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class ByteArrayMapper {

    public List<RecordDto> byteArrayToRecord(byte[] bytes) throws IOException {
        Preconditions.checkArgument(nonNull(bytes), "Bytes[] must not be null");
        var bis = new ByteArrayInputStream(bytes);
        var dataInputStream = new DataInputStream(bis);

        final var lengthRecord = dataInputStream.readLong();
        var recordDtos = new RecordDto[(int) lengthRecord];

        for (var i = 0; i < lengthRecord; i++) {
            //index record
            final var indexRecord = dataInputStream.readLong();
            //timestamp
            final var timestamp = dataInputStream.readLong();
            //city
            final var lengthCityBytes = dataInputStream.readInt();
            final var city = new String(dataInputStream.readNBytes(lengthCityBytes));
            //sensor
            //TODO: unknown field
            dataInputStream.readInt();
            final var sensorLength = dataInputStream.readInt();
            final List<SensorDto> sensorDtoList = readSensors(dataInputStream, sensorLength);
            //checksum
            final var checksum = dataInputStream.readLong();
            recordDtos[i] = RecordDto.builder()
                    .withRecordIndex(indexRecord)
                    .withTimestamp(timestamp)
                    .withCity(city)
                    .withSensorsDataList(sensorDtoList)
                    .withNumberBytesSensorData(sensorLength)
                    .withCrc32SensorsData(checksum)
                    .build();
        }
        return Arrays.asList(recordDtos);
    }

    private List<SensorDto> readSensors(DataInputStream di, int length) throws IOException {
        var sensorDtos = new SensorDto[length];

        for (var i = 0; i < length; i++) {
            var lengthId = di.readInt();
            var id = new String(di.readNBytes(lengthId));
            var measure = di.readInt();
            sensorDtos[i] = SensorDto.builder()
                    .withId(id)
                    .withMeasure(measure)
                    .build();
        }
        return Arrays.asList(sensorDtos);
    }
}