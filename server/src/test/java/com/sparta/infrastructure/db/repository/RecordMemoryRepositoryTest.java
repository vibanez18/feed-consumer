package com.sparta.infrastructure.db.repository;

import com.sparta.domain.record.Record;
import com.sparta.infrastructure.db.memory.repository.RecordMemoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RecordMemoryRepositoryTest {

    public static final String PROVIDER = "provider";

    @InjectMocks
    RecordMemoryRepository testee;

    @Test
    void saveAllByProvider_WhenParametersOk_EntityIsReturned() {
        final List<Record> records = Collections.singletonList(mock(Record.class));
        final List<Record> result = testee.saveAllByProvider(PROVIDER, records);

        assertThat(result).isEqualTo(records);

    }

    @Test
    void saveAllByProvider_WhenExistProviderExist_UpdateProviderData() {
        final List<Record> records_first = Collections.singletonList(mock(Record.class));
        final List<Record> records_second = Arrays.asList(mock(Record.class), mock(Record.class));

        testee.saveAllByProvider(PROVIDER, records_first);

        final List<Record> recordsExpected = testee.saveAllByProvider(PROVIDER, records_second);
        final List<Record> recordByProvider = testee.findRecordByProvider(PROVIDER);

        assertThat(recordByProvider)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(recordsExpected);
    }

    @Test
    void saveAllByProvider_WhenProviderIsNull_EntityIsReturned() {
        final List<Record> records = Collections.singletonList(mock(Record.class));
        final List<Record> result = testee.saveAllByProvider(null, records);

        assertThat(result).isEqualTo(records);

    }

    @Test
    void saveAllByProvider_WhenRecordListIsEmpty_EntityIsReturned() {
        final List<Record> result = testee.saveAllByProvider(PROVIDER, new ArrayList<>());

        assertThat(result).isEmpty();

    }

    @Test
    void saveAllByProvider_WhenRecordListIsNull_ThenExceptionThrown() {
        assertThatThrownBy(() -> testee.saveAllByProvider(PROVIDER, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Records must not be null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"provider"})
    @NullAndEmptySource
    void findMessagesByProvider_WhenProvider_TotalMessagesReturned(String provider) {
        final Integer messages = testee.findMessagesByProvider(provider);

        assertThat(messages).isNotNull()
                .isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"provider"})
    @NullAndEmptySource
    void findRecordByProvider_WhenProvider_TotalRecordsReturned(String provider) {
        final List<Record> recordList = testee.findRecordByProvider(provider);
        assertThat(recordList)
                .isNotNull()
                .isEmpty();
    }

}
