package com.example.messenger.mapper.api;

import com.example.messenger.domain.ImageMessage;
import com.example.messenger.domain.Message;
import com.example.messenger.dto.ImageMessageDto;
import com.example.messenger.dto.MessageDto;

public interface MessageMapperInterface {
    MessageDto mapToMessageDto(Message message);
    Message mapToMessage(MessageDto dto);
}
