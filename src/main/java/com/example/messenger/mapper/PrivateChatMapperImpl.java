package com.example.messenger.mapper;

import com.example.messenger.domain.PrivateChat;
import com.example.messenger.dto.PrivateChatDto;
import com.example.messenger.mapper.api.PrivateChatMapperInterface;
import org.springframework.stereotype.Component;

@Component
public class PrivateChatMapperImpl implements PrivateChatMapperInterface {
    @Override
    public PrivateChatDto mapToPrivateChatDto(PrivateChat privateChat) {
        PrivateChatDto dto = new PrivateChatDto();
        dto.setId(privateChat.getId());
        dto.setChatName(privateChat.getChatName());
        dto.setMaxUser(privateChat.getMaxUser());
        dto.setPassword(privateChat.getPassword());
        return dto;
    }

    @Override
    public PrivateChat mapToPrivateChat(PrivateChatDto dto) {
        PrivateChat privateChat = new PrivateChat();
        privateChat.setId(dto.getId());
        privateChat.setChatName(dto.getChatName());
        privateChat.setPassword(dto.getPassword());
        privateChat.setMaxUser(dto.getMaxUser());
        return privateChat;
    }
}
