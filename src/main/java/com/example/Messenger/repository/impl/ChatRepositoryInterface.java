package com.example.Messenger.repository.impl;

import com.example.Messenger.domain.Chat;
import com.example.Messenger.domain.User;

import java.util.List;

public interface ChatRepositoryInterface {
    Chat save(Chat chat);
    Chat findChat(String chatName);
    List<Chat> findAll();
    void delete(Chat chat);
    Chat createNewChat(User creator, String name, boolean isPrivate, String password, int maxUsers);
}
