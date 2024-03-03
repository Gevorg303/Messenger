package com.example.messenger.mapper.api;

import com.example.messenger.domain.PrivateChat;
import com.example.messenger.dto.PrivateChatDto;

public interface PrivateChatMapperInterface {
    PrivateChatDto mapToPrivateChatDto(PrivateChat privateChat);
    PrivateChat mapToPrivateChat(PrivateChatDto dto);
}
