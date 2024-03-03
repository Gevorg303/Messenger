package com.example.messenger.service.api;

import com.example.messenger.domain.Admin;
import com.example.messenger.domain.Chat;
import com.example.messenger.domain.User;
import com.example.messenger.dto.UserDto;
import com.example.messenger.service.AdminServiceImpl;
import com.example.messenger.service.ChatServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserServiceInterface {
    String createUser(UserDto dto);
    void deleteUser(User user);
    List<Chat> getUserChats(User user);
    Chat createChat(ChatServiceImpl chatService, User user, String name, boolean isPrivate, String password, int maxUsers);
    void deleteChat(ChatServiceImpl chatService, User user, Chat chat);
    void changeChatMaxUsers(AdminServiceImpl adminService, Admin admin, Chat chat, int maxUsers);
    void deleteUserAndChats(ChatServiceImpl chatService, User user);
    boolean findUser(UserDto dto);
    Page<User> getUserList(Pageable pageable);
    void removeUser(UserDto userDto);
}
