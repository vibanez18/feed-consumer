package com.sparta.application.repository;

import com.sparta.domain.record.Record;

import java.util.List;

public interface RecordRepository {

    List<Record> saveAllByProvider(String provider, List<Record> records);

    List<Record> findRecordByProvider(String provider);

    Integer findMessagesByProvider(String provider);
}
