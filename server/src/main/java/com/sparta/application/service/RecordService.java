package com.sparta.application.service;

import com.google.common.base.Preconditions;
import com.sparta.application.mapper.record.RecordMapper;
import com.sparta.application.repository.RecordRepository;
import com.sparta.domain.record.Record;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class RecordService {

    private static final Logger logger = LoggerFactory.getLogger(RecordService.class);
    private final RecordMapper mapper;
    private final RecordRepository repository;

    public RecordService(RecordMapper mapper, RecordRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public int loadRecords(List<RecordDto> recordDtos, String provider) {
        Preconditions.checkArgument(nonNull(recordDtos), "RecordDtos must not be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(provider), "Provider must not be null nor empty");

        final List<Record> records = this.repository.saveAllByProvider(provider, mapper.fromDtos(recordDtos));

        logger.info("Load {} records from Provider: {}", records.size(), provider);

        return records != null ? records.size() : 0;
    }

}
