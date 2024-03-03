package com.example.messenger.mapper;

import com.example.messenger.domain.Chat;
import com.example.messenger.domain.PrivateChat;
import com.example.messenger.domain.PublicChat;
import com.example.messenger.dto.ChatDto;
import com.example.messenger.dto.PrivateChatDto;
import com.example.messenger.dto.PublicChatDto;
import com.example.messenger.mapper.api.ChatMapperInterface;
import org.springframework.stereotype.Component;

@Component
public class ChatMapperImpl implements ChatMapperInterface {
    @Override
    public ChatDto mapToChatDto(Chat chat) {
        ChatDto dto;
        if(chat.isPrivate()) {
             dto = new PrivateChatDto();
        }else {
            dto = new PublicChatDto();
        }
        dto.setId(chat.getId());
        dto.setChatName(chat.getChatName());
        dto.setMaxUser(chat.getMaxUser());
        return dto;
    }

    @Override
    public Chat mapToChat(ChatDto dto) {
        Chat chat;
        if(dto.isPrivate()) {
            chat = new PrivateChat();
        }else {
            chat = new PublicChat();
        }
        chat.setId(dto.getId());
        chat.setChatName(dto.getChatName());
        chat.setMaxUser(dto.getMaxUser());
        return chat;
    }
}
