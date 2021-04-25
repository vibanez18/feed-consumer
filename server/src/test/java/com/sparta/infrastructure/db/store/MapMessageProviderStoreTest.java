package com.sparta.infrastructure.db.store;

import com.sparta.infrastructure.db.memory.store.MapMessageProviderStore;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapMessageProviderStoreTest {

    public static final String PROVIDER = "provider";
    private final MapMessageProviderStore testee = new MapMessageProviderStore();

    @Test
    void addElement_WhenParametersOk_ThenStoreIsCalled() {
        testee.addElement(PROVIDER, 100);

        final Map<String, Integer> store = (Map<String, Integer>) ReflectionTestUtils.getField(testee, "store");

        assertThat(store)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void getElement_WhenCalled_CollectionIsReturned() {
        final Integer messages = testee.getElement(PROVIDER);

        assertThat(messages)
                .isNotNull()
                .isEqualTo(0);
    }

}
