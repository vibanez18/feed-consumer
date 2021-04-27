package com.sparta.infrastructure.rest.actuator;

public class MessagesDto {

    private String provider;
    private Integer messages;

    public MessagesDto(String provider, Integer messages) {
        this.provider = provider;
        this.messages = messages;
    }

    public String getProvider() {
        return provider;
    }

    public Integer getMessages() {
        return messages;
    }
}
