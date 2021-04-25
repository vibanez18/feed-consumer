package com.sparta.infrastructure.db.repository;

import com.sparta.domain.record.Record;
import com.sparta.infrastructure.db.memory.repository.RecordMemoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class RecordMemoryRepositoryTest {

    public static final String PROVIDER = "provider";

    @InjectMocks
    RecordMemoryRepository testee;

    @Test
    void saveAllByProvider_WhenParametersOk_EntityIsReturned() {
        final List<Record> records = Collections.singletonList(Mockito.mock(Record.class));
        final List<Record> result = testee.saveAllByProvider(PROVIDER, records);

        assertThat(result).isEqualTo(records);

    }

    @Test
    void saveAllByProvider_WhenProviderIsNull_EntityIsReturned() {
        final List<Record> records = Collections.singletonList(Mockito.mock(Record.class));
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

    @Test
    void findMessagesByProvider_WhenProvider_TotalMessagesReturned() {
        final Integer messages = testee.findMessagesByProvider(PROVIDER);

        assertThat(messages).isNotNull()
                .isEqualTo(0);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void findMessagesByProvider_WhenProvider_TotalMessagesReturned(String provider) {
        final Integer messages = testee.findMessagesByProvider(provider);

        assertThat(messages).isNotNull()
                .isEqualTo(0);
    }
}
