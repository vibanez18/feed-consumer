package com.sparta.infrastructure.db.memory.repository;

import com.sparta.application.repository.RecordRepository;
import com.sparta.domain.record.Record;
import com.sparta.infrastructure.db.memory.store.MapMessageProviderStore;
import com.sparta.infrastructure.db.memory.store.MapRecordStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordMemoryRepository implements RecordRepository {

    private static final Logger logger = LoggerFactory.getLogger(RecordMemoryRepository.class);
    private MapRecordStore mapRecordStore = new MapRecordStore<List<Record>>();
    private MapMessageProviderStore mapMessageProviderStore = new MapMessageProviderStore();

    @Override
    public List<Record> saveAllByProvider(String provider, List<Record> records) {
        this.mapRecordStore.addElement(provider, records);

        logger.debug("Add {} records from Provider: {}", records.size(), provider);

        this.mapMessageProviderStore.addElement(provider, records.size());

        return records;
    }

    @Override
    public Integer findMessagesByProvider(String provider) {
        return this.mapMessageProviderStore.getElement(provider);
    }
}
