package com.sparta.infrastructure.rest.actuator;

import com.sparta.application.repository.RecordRepository;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

@Component
@WebEndpoint(id = "messages")
public class MessagesEndpoint {

    private final RecordRepository recordRepository;

    public MessagesEndpoint(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @ReadOperation
    public MessagesDto readMessages(@Selector String provider) {
        final Integer messagesByProvider = this.recordRepository.findMessagesByProvider(provider);

        return new MessagesDto(provider, messagesByProvider);
    }

}
