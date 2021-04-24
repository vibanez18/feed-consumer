package com.sparta.domain.record;

public class Sensor {

    private String id;
    private Integer measure;

    private Sensor(Builder builder) {
        //TODO: add validations
        this.id = builder.id;
        this.measure = builder.measure;
    }

    public static Sensor.Builder builder() {
        return new Sensor.Builder();
    }

    public String getId() {
        return id;
    }

    public Integer getMeasure() {
        return measure;
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

        public Sensor build() {
            return new Sensor(this);
        }
    }
}
