package com.sparta.application.service;

import java.util.List;

public class RecordDto {

    private Long recordIndex;
    private Long timestamp;
    private String city;
    private Integer numberBytesSensorData;
    private List<SensorDto> sensorsDataList;
    private Long crc32SensorsData;

    private RecordDto(Builder builder) {
        this.recordIndex = builder.recordIndex;
        this.timestamp = builder.timestamp;
        this.city = builder.city;
        this.numberBytesSensorData = builder.numberBytesSensorData;
        this.sensorsDataList = builder.sensorsDataList;
        this.crc32SensorsData = builder.crc32SensorsData;
    }

    public static RecordDto.Builder builder() {
        return new RecordDto.Builder();
    }

    public Long getRecordIndex() {
        return recordIndex;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getCity() {
        return city;
    }

    public Integer getNumberBytesSensorData() {
        return numberBytesSensorData;
    }

    public List<SensorDto> getSensorsDataList() {
        return sensorsDataList;
    }

    public Long getCrc32SensorsData() {
        return crc32SensorsData;
    }

    public String toString() {
        return "RecordDto(recordIndex=" + this.getRecordIndex()
                + ", timestamp=" + this.getTimestamp()
                + ", city=" + this.getCity()
                + ", numberBytesSensorData=" + this.getNumberBytesSensorData()
                + ", sensorsDataList="
                + this.getSensorsDataList()
                + ", crc32SensorsData="
                + this.getCrc32SensorsData() + ")";
    }

    public static final class Builder {

        private Long recordIndex;
        private Long timestamp;
        private String city;
        private Integer numberBytesSensorData;
        private List<SensorDto> sensorsDataList;
        private Long crc32SensorsData;

        public Builder withRecordIndex(Long recordIndex) {
            this.recordIndex = recordIndex;
            return this;
        }

        public Builder withTimestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withNumberBytesSensorData(Integer numberBytesSensorData) {
            this.numberBytesSensorData = numberBytesSensorData;
            return this;
        }

        public Builder withSensorsDataList(List<SensorDto> sensorsDataList) {
            this.sensorsDataList = sensorsDataList;
            return this;
        }

        public Builder withCrc32SensorsData(Long crc32SensorsData) {
            this.crc32SensorsData = crc32SensorsData;
            return this;
        }

        public RecordDto build() {
            return new RecordDto(this);
        }
    }
}
