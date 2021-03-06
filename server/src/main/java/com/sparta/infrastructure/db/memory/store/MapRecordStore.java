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

    // TODO: I assume that the data is like a cache and it has to be updated.
    public void addElement(String key, List<T> value) {
        this.store.put(key, value);
    }

    public List<T> getElement(String key) {
        final List<T> result = this.store.get(key);
        return result != null ? Collections.unmodifiableList(result) : Collections.emptyList();
    }
}
