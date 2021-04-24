package com.sparta.infrastructure.db.memory.repository;

import com.sparta.application.repository.RecordRepository;
import com.sparta.domain.record.Record;
import com.sparta.infrastructure.db.memory.store.MapMessageProviderStore;
import com.sparta.infrastructure.db.memory.store.MapRecordStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordMemoryRepository implements RecordRepository {

    private MapRecordStore mapRecordStore = new MapRecordStore<List<Record>>();
    private MapMessageProviderStore mapMessageProviderStore = new MapMessageProviderStore();

    @Override
    public List<Record> saveAllByProvider(String provider, List<Record> records) {
        this.mapRecordStore.addElement(provider, records);
        this.mapMessageProviderStore.addElement(provider, records.size());
        return records;
    }
}
