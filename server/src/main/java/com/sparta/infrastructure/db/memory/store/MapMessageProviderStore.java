package com.sparta.infrastructure.db.memory.store;

import java.util.HashMap;
import java.util.Map;

public class MapMessageProviderStore {

    private Map<String, Integer> store;

    public MapMessageProviderStore() {
        this.store = new HashMap<>();
    }

    public void addElement(String key, Integer value) {
        final Integer messages = this.store.get(key);

        if (messages != null) {
            this.store.computeIfPresent(key, (k, v) -> messages + value);
        } else {
            this.store.putIfAbsent(key, value);
        }

    }

    public Integer getElement(String key) {
        final Integer messages = this.store.get(key);

        return messages != null ? messages : 0;
    }

    // Only for test
    private void deleteAll() {
        this.store.clear();
    }
}
