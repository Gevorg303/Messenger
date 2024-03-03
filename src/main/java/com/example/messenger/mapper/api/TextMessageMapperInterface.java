package com.example.messenger.mapper.api;

import com.example.messenger.domain.TextMessage;
import com.example.messenger.dto.TextMessageDto;

public interface TextMessageMapperInterface {
    TextMessageDto mapToTextMessageDto(TextMessage textMessage);
    TextMessage mapToTextMessage(TextMessageDto dto);
}
