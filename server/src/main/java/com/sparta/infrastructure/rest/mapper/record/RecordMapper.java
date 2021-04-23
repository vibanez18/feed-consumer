package com.sparta.infrastructure.rest.mapper.record;

import com.sparta.infrastructure.rest.dto.record.RecordDto;
import com.sparta.infrastructure.rest.dto.record.SensorDto;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RecordMapper {

    public List<RecordDto> byteArrayToRecord(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dataInputStream = new DataInputStream(bis);

        final long lengthRecord = dataInputStream.readLong();
        RecordDto[] recordDtos = new RecordDto[(int) lengthRecord];

        for (int i = 0; i < lengthRecord; i++) {
            //index record
            final long indexRecord = dataInputStream.readLong();
            //timestamp
            final long timestamp = dataInputStream.readLong();
            //city
            final int lengthCityBytes = dataInputStream.readInt();
            final String city = new String(dataInputStream.readNBytes(lengthCityBytes));
            //sensor
            final List<SensorDto> sensorDtoList = readSensors(dataInputStream);
            //checksum
            final long checksum = dataInputStream.readLong();
            //TODO: numberBytesSensorData
            recordDtos[i] = RecordDto.builder()
                    .withRecordIndex(indexRecord)
                    .withTimestamp(timestamp)
                    .withCity(city)
                    .withSensorsDataList(sensorDtoList)
                    .withCrc32SensorsData(checksum)
                    .build();
        }
        return Arrays.asList(recordDtos);
    }

    private List<SensorDto> readSensors(DataInputStream di) throws IOException {
        //TODO: unknown field
        final int unknown = di.readInt();
        final int length = di.readInt();
        SensorDto[] sensorDtos = new SensorDto[length];

        for (int i = 0; i < length; i++) {
            int lengthId = di.readInt();
            String id = new String(di.readNBytes(lengthId));
            int measure = di.readInt();
            sensorDtos[i] = SensorDto.builder()
                    .withId(id)
                    .withMeasure(measure)
                    .build();
        }
        return Arrays.asList(sensorDtos);
    }
}