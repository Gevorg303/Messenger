package com.example.messenger.mapper;

import com.example.messenger.domain.TextMessage;
import com.example.messenger.dto.TextMessageDto;
import com.example.messenger.mapper.api.TextMessageMapperInterface;
import org.springframework.stereotype.Component;

@Component
public class TextMessageMapperImpl implements TextMessageMapperInterface {
    @Override
    public TextMessageDto mapToTextMessageDto(TextMessage textMessage) {
        TextMessageDto textMessageDto = new TextMessageDto();
        textMessageDto.setId(textMessage.getId());
        textMessageDto.setMessage(textMessage.getMessage());
        return textMessageDto;
    }

    @Override
    public TextMessage mapToTextMessage(TextMessageDto dto) {
        TextMessage textMessage = new TextMessage();
        textMessage.setId(dto.getId());
        textMessage.setMessage(dto.getMessage());
        return textMessage;
    }
}
