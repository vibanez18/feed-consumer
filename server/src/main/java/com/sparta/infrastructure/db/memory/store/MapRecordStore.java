package com.sparta.infrastructure.db.memory.store;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapRecordStore<T> {

    private final Map<String, List<T>> store;

    public MapRecordStore() {
        this.store = new HashMap<>();
    }

    public void addElement(String key, List<T> value) {
        this.store.put(key, value);
    }

    public List<T> getElement(String key) {
        final List<T> result = this.store.get(key);
        return result != null ? Collections.unmodifiableList(result) : Collections.emptyList();
    }
}
