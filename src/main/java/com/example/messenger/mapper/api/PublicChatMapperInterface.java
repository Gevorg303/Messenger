package com.example.messenger.mapper.api;

import com.example.messenger.domain.PublicChat;
import com.example.messenger.dto.PublicChatDto;

public interface PublicChatMapperInterface {
    PublicChatDto mapToPublicChatDto(PublicChat PublicChat);
    PublicChat mapToPublicChat(PublicChatDto dto);
}
