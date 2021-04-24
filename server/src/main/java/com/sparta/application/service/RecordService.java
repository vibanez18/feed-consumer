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
    private final RecordRepository recordRepository;

    public RecordService(RecordMapper mapper, RecordRepository recordRepository) {
        this.mapper = mapper;
        this.recordRepository = recordRepository;
    }

    public List<RecordDto> loadRecords(List<RecordDto> recordDtos, String provider) {
        Preconditions.checkArgument(nonNull(recordDtos), "RecordDtos must not be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(provider), "Provider must not be null nor empty");

        final List<Record> records = this.recordRepository.saveAllByProvider(provider, mapper.fromDtos(recordDtos));

        logger.info("Load {} records from Provider: {}", records.size(), provider);

        return mapper.fromEntities(records);

    }

    public Integer totalMessagesByProvider(String provider) {
        Preconditions.checkArgument(StringUtils.isNotBlank(provider), "Provider must not be null nor empty");
        return this.recordRepository.findMessagesByProvider(provider);
    }

}
