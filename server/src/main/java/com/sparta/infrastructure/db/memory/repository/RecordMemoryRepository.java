package com.sparta.infrastructure.db.memory.repository;

import com.sparta.application.repository.RecordRepository;
import com.sparta.domain.record.Record;
import com.sparta.infrastructure.db.memory.store.MapStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordMemoryRepository implements RecordRepository {

    private MapStore mapStore = new MapStore<List<Record>>();

    @Override
    public List<Record> saveAllByProvider(String provider, List<Record> records) {
        this.mapStore.addElement(provider, records);
        return records;
    }
}
