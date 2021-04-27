package com.sparta.infrastructure.db.memory.repository;

import com.google.common.base.Preconditions;
import com.sparta.application.repository.RecordRepository;
import com.sparta.domain.record.Record;
import com.sparta.infrastructure.db.memory.store.MapMessageProviderStore;
import com.sparta.infrastructure.db.memory.store.MapRecordStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
public class RecordMemoryRepository implements RecordRepository {

    private static final Logger logger = LoggerFactory.getLogger(RecordMemoryRepository.class);
    private final MapRecordStore<Record> mapRecordStore;
    private final MapMessageProviderStore mapMessageProviderStore;

    public RecordMemoryRepository() {
        this.mapRecordStore = new MapRecordStore<>();
        this.mapMessageProviderStore = new MapMessageProviderStore();
    }

    @Override
    public List<Record> saveAllByProvider(String provider, List<Record> records) {
        Preconditions.checkArgument(nonNull(records), "Records must not be null");

        final int recordSize = records.size();
        this.mapRecordStore.addElement(provider, records);

        logger.debug("Add {} records from Provider: {}", recordSize, provider);

        this.mapMessageProviderStore.addElement(provider, recordSize);

        return records;
    }

    @Override
    public List<Record> findRecordByProvider(String provider) {
        return mapRecordStore.getElement(provider);
    }

    @Override
    public Integer findMessagesByProvider(String provider) {
        final Integer messages = this.mapMessageProviderStore.getElement(provider);

        logger.debug("Get {} messages from Provider: {}", messages, provider);

        return messages != null ? messages : 0;
    }

    // Only for test
    @SuppressWarnings("squid:S1144")
    private void purgeMessageStore() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var method = mapMessageProviderStore.getClass().getDeclaredMethod("deleteAll");
        method.setAccessible(true);
        method.invoke(mapMessageProviderStore);
    }
}
