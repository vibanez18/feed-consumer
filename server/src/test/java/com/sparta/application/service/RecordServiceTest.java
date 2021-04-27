package com.sparta.application.service;

import com.sparta.application.mapper.record.RecordMapper;
import com.sparta.application.repository.RecordRepository;
import com.sparta.domain.record.Record;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecordServiceTest {

    public static final String PROVIDER = "provider";

    @InjectMocks
    RecordService testee;
    @Mock
    RecordMapper mapper;
    @Mock
    RecordRepository recordRepository;

    @Test
    void loadRecords_WhenParametersOk_ThenReturnTotal() throws ExecutionException, InterruptedException {
        final List<RecordDto> recordDtos = Arrays.asList(mock(RecordDto.class), mock(RecordDto.class), mock(RecordDto.class));
        final List<Record> recordSaved = Arrays.asList(mock(Record.class), mock(Record.class), mock(Record.class));

        when(recordRepository.saveAllByProvider(PROVIDER, mapper.fromDtos(recordDtos))).thenReturn(recordSaved);
        when(mapper.fromEntities(anyList())).thenReturn(recordDtos);

        final CompletableFuture<List<RecordDto>> result = testee.loadRecords(recordDtos, PROVIDER);

        assertThat(result.get()).isNotNull()
                .isEqualTo(recordDtos);
        verify(recordRepository).saveAllByProvider(PROVIDER, mapper.fromDtos(recordDtos));
    }

    @Test
    void loadRecords_WhenProviderIsNullOrEmpty_ThenExceptionThrown() {
        final List<RecordDto> recordDtos = Arrays.asList(mock(RecordDto.class), mock(RecordDto.class), mock(RecordDto.class));

        assertThatThrownBy(() -> testee.loadRecords(recordDtos, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provider must not be null nor empty");

        assertThatThrownBy(() -> testee.loadRecords(recordDtos, " "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provider must not be null nor empty");
    }

    @Test
    void loadRecords_WhenRecordDtoIsNull_ThenExceptionThrown() {
        assertThatThrownBy(() -> testee.loadRecords(null, PROVIDER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("RecordDtos must not be null");
    }

    @Test
    void totalMessagesByProvider_WhenParametersOk_ThenReturnTotal() {
        when(recordRepository.findMessagesByProvider(PROVIDER)).thenReturn(3);
        final Integer messages = testee.totalMessagesByProvider(PROVIDER);

        assertThat(messages).isNotNull()
                .isEqualTo(3);
        verify(recordRepository).findMessagesByProvider(PROVIDER);
    }

    @Test
    void totalMessagesByProvider_WhenProviderIsNullOrEmpty_ThenExceptionThrown() {
        assertThatThrownBy(() -> testee.totalMessagesByProvider(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provider must not be null nor empty");

        assertThatThrownBy(() -> testee.totalMessagesByProvider(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provider must not be null nor empty");
    }
}
