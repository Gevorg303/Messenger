package com.example.messenger.mapper.api;

import com.example.messenger.domain.Chat;
import com.example.messenger.dto.ChatDto;

public interface ChatMapperInterface {
    ChatDto mapToChatDto(Chat chat);

    Chat mapToChat(ChatDto dto);
}
