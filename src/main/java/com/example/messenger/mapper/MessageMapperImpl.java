package com.example.messenger.mapper;

import com.example.messenger.domain.Message;
import com.example.messenger.domain.TextMessage;
import com.example.messenger.dto.MessageDto;
import com.example.messenger.dto.TextMessageDto;
import com.example.messenger.mapper.api.MessageMapperInterface;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperImpl implements MessageMapperInterface {
    @Override
    public MessageDto mapToMessageDto(Message message) {
        MessageDto messageDto = new TextMessageDto();
        messageDto.setId(message.getId());
        messageDto.setMessage(message.getMessage());
        return messageDto;
    }

    @Override
    public Message mapToMessage(MessageDto dto) {
        Message message = new TextMessage();
        message.setId(dto.getId());
        message.setMessage(dto.getMessage());
        return message;
    }
}
