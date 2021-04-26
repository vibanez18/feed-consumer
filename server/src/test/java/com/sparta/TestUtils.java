package com.sparta;

import com.sparta.infrastructure.client.DataClientFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestUtils {

    public static byte[] createRecords(int numberRecords) throws IOException {
        DataClientFactory dataFactory = new DataClientFactory();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        dataFactory.write(baos, numberRecords);
        return baos.toByteArray();
    }
}
