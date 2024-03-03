package com.example.messenger.mapper;

import com.example.messenger.domain.PublicChat;
import com.example.messenger.dto.PublicChatDto;
import com.example.messenger.mapper.api.PublicChatMapperInterface;
import org.springframework.stereotype.Component;

@Component
public class PublicChatMapperImpl implements PublicChatMapperInterface {
    @Override
    public PublicChatDto mapToPublicChatDto(PublicChat publicChat) {
        PublicChatDto dto = new PublicChatDto();
        dto.setId(publicChat.getId());
        dto.setChatName(publicChat.getChatName());
        dto.setMaxUser(publicChat.getMaxUser());
        return dto;
    }

    @Override
    public PublicChat mapToPublicChat(PublicChatDto dto) {
        PublicChat publicChat = new PublicChat();
        publicChat.setId(dto.getId());
        publicChat.setChatName(dto.getChatName());
        publicChat.setMaxUser(dto.getMaxUser());
        return publicChat;
    }
}
