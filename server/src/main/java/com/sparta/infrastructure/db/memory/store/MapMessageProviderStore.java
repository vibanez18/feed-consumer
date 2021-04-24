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
        //TODO: try to remove the if
        if (messages != null) {
            this.store.computeIfPresent(key, (k, v) -> messages + value);
        } else {
            this.store.putIfAbsent(key, value);
        }

    }

    public Integer getElement(String key) {
        return this.store.get(key);
    }
}
