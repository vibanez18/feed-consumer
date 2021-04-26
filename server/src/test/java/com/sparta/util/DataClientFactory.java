package com.sparta.util;

import com.github.javafaker.Faker;
import com.github.javafaker.GameOfThrones;

import java.io.*;
import java.util.*;
import java.util.zip.CRC32;

public class DataClientFactory {

    private static final DataClientFactory.Time time = new DataClientFactory.Time();
    private final Faker faker = new Faker();
    private final GameOfThrones got;
    private final DataClientFactory.SensorFactory sensorFactory;

    public DataClientFactory() {
        this.got = this.faker.gameOfThrones();
        this.sensorFactory = new DataClientFactory.SensorFactory(1000);
    }

    public void write(OutputStream fos, int records) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeLong((long) records);
        long count = 0L;

        for (int i = 0; i < records; ++i) {
            dos.writeLong(count++);
            dos.writeLong(time.tick());
            String city = this.got.city();
            writeString(dos, city);
            byte[] batchBytes = this.createBatchSensors();
            dos.writeInt(batchBytes.length);
            dos.write(batchBytes);
            dos.writeLong(this.calculateCrc(batchBytes));
        }

        dos.flush();
    }

    private byte[] createBatchSensors() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream batchStream = new DataOutputStream(baos);
        List<DataClientFactory.Sensor> batch = this.sensorFactory.getBatch();
        batchStream.writeInt(batch.size());
        Iterator var4 = batch.iterator();

        while (var4.hasNext()) {
            DataClientFactory.Sensor sensor = (DataClientFactory.Sensor) var4.next();
            writeString(batchStream, sensor.id);
            batchStream.writeInt(sensor.measure);
        }

        batchStream.flush();
        return baos.toByteArray();
    }

    private long calculateCrc(byte[] batchBytes) {
        CRC32 crc = new CRC32();
        crc.update(batchBytes);
        return crc.getValue();
    }

    private static void writeString(DataOutputStream dos, String value) throws IOException {
        byte[] cityb = value.getBytes();
        dos.writeInt(cityb.length);
        dos.write(cityb);
    }

    public static class SensorFactory {

        public Random rnd = new Random(0L);
        private List<String> ids = new ArrayList();

        public SensorFactory(int max) {
            for (int i = 0; i < max; ++i) {
                this.ids.add(UUID.randomUUID().toString());
            }

        }

        public List<DataClientFactory.Sensor> getBatch() {
            List<DataClientFactory.Sensor> values = new ArrayList();
            int batchSize = this.rnd.nextInt(10);

            for (int i = 0; i < batchSize; ++i) {
                String id = (String) this.ids.get(this.rnd.nextInt(this.ids.size()));
                int value = this.rnd.nextInt(10000);
                values.add(new DataClientFactory.Sensor(id, value));
            }

            return values;
        }
    }

    public static class Time {

        private long now = System.nanoTime();
        private Random rnd = new Random(0L);

        public Time() {
        }

        public long tick() {
            this.now += (long) this.rnd.nextInt(1000);
            return this.now;
        }
    }

    public static class Sensor {

        private String id;
        private int measure;

        public Sensor(String id, int measure) {
            this.id = id;
            this.measure = measure;
        }
    }
}
