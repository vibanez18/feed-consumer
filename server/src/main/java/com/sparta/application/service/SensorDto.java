package com.sparta.application.service;

public class SensorDto {

    private String id;
    private Integer measure;

    private SensorDto(Builder builder) {
        this.id = builder.id;
        this.measure = builder.measure;
    }

    public static SensorDto.Builder builder() {
        return new SensorDto.Builder();
    }

    public String getId() {
        return id;
    }

    public Integer getMeasure() {
        return measure;
    }

    public String toString() {
        return "SensorDto(id=" + this.getId()
                + ", measure="
                + this.getMeasure() + ")";
    }

    public static final class Builder {

        private String id;
        private Integer measure;


        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withMeasure(Integer measure) {
            this.measure = measure;
            return this;
        }

        public SensorDto build() {
            return new SensorDto(this);
        }
    }
}
