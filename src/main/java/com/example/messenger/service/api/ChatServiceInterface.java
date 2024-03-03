package com.example.messenger.service.api;

import com.example.messenger.domain.Chat;
import com.example.messenger.domain.Message;
import com.example.messenger.domain.User;
import com.example.messenger.dto.ChatDto;
import com.example.messenger.dto.MessageDto;
import com.example.messenger.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatServiceInterface {
    Chat createChat(User creator, String name, boolean isPrivate, String password, int maxUsers);
    void deleteChat(User user, Chat chat);
    void addUserToChat(User user, Chat chat);
    void removeUserFromChat(User user, Chat chat);
    void writeMessage(User user, Chat chat, Message message);
    Page<Chat> getChatList(Pageable pageable);
    Chat findChat(String chatName);
    Message newMessage(String message, boolean isText);
    void removeChat(ChatDto chatDto, UserDto userDto);
    Chat addChat(UserDto userDto, ChatDto chatDto);
    void addUserChat(ChatDto chatDto, UserDto userDto);
    void deleteUserToChat(UserDto userDto, ChatDto chatDto);
    void writeMessageUser(UserDto userDto, ChatDto chatDto, MessageDto messageDto);
}
