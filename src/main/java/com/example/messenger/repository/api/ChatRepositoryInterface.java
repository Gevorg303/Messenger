package com.example.messenger.repository.api;

import com.example.messenger.domain.Chat;
import com.example.messenger.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatRepositoryInterface {
    Page<Chat> findAll(Pageable pageable);
    Chat save(Chat chat);
    Chat findChat(String chatName);
    List<Chat> findAll();
    void delete(Chat chat);
    Chat createNewChat(User creator, String name, boolean isPrivate, String password, int maxUsers);
}
