package com.sparta.infrastructure.db.store;

import com.sparta.domain.record.Record;
import com.sparta.infrastructure.db.memory.store.MapRecordStore;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MapRecordStoreTest {

    public static final String PROVIDER = "provider";

    private final MapRecordStore<Record> testee = new MapRecordStore<>();

    @Test
    void addElement_WhenParametersOk_ThenStoreIsCalled() {
        final List<Record> records = Collections.singletonList(mock(Record.class));

        testee.addElement(PROVIDER, records);

        final Map<String, List<Record>> store = (Map<String, List<Record>>) ReflectionTestUtils.getField(testee, "store");

        assertThat(store)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void getElement_WhenCalled_CollectionIsReturned() {
        final List<Record> records = testee.getElement(PROVIDER);

        assertThat(records)
                .isNotNull()
                .isEmpty();
    }

}
