package com.sparta.infrastructure.rest.mapper;

import com.sparta.application.service.RecordDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.sparta.util.TestUtils.createRecords;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ByteArrayMapperTest {

    ByteArrayMapper mapper = new ByteArrayMapper();

    @Test
    void byteArrayToRecord_WhenByteArray_ThenDtoReturned() throws IOException {
        final List<RecordDto> recordDtos = mapper.byteArrayToRecord(createRecords(10));

        assertThat(recordDtos)
                .isNotNull()
                .hasSize(10);
    }

    @Test
    void byteArrayToRecord_WhenByteArrayIsNull_ThenExceptionThrown() {
        assertThatThrownBy(() -> mapper.byteArrayToRecord(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bytes[] must not be null");
    }

}
