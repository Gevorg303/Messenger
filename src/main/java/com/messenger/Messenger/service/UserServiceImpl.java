package com.messenger.Messenger.service;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.repository.ChatRepositoryImpl;
import com.messenger.Messenger.repository.impl.UserRepositoryInterface;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private UserRepositoryInterface userRepository;
    @Autowired
    private ChatRepositoryImpl chatRepository;
    /*Создать нового пользователя*/
    @Override
    public String createUser(String username) {
        User user = new User(username);
        userRepository.save(user);
        return "Пользователь " + user.getNameUser() + " создан.";
    }

    /*Удалить пользователя из мессенджера*/
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    /*Получить чаты пользователя*/
    @Override
    public List<Chat> getUserChats(User user) {
        return user.getChat();
    }

    @Override
    public Chat createChat(ChatServiceImpl chatService, User user, String name, boolean isPrivate, String password, int maxUsers) {
        Chat chat = chatService.createChat(user, name, isPrivate, password, maxUsers);
        chatRepository.save(chat);
        return chat;
    }

    @Override
    public void deleteChat(ChatServiceImpl chatService, User user, Chat chat) {
        chatService.deleteChat(user, chat);
    }

    @Override
    public void changeChatMaxUsers(AdminServiceImpl adminService, Admin admin, Chat chat, int maxUsers) {
        adminService.changeChatMaxUsers(admin, chat, maxUsers);
    }

    /*Удаляем чаты пользователя*/
    @Override
    public void deleteUserAndChats(ChatServiceImpl chatService, User user) {
        deleteUser(user);
        List<Chat> userChatsCopy = new ArrayList<>(user.getChat());
        for (Chat chat : userChatsCopy) {
            chatService.deleteChat(user, chat);
        }
    }

    @Override
    public User findUser(String userName) {
        for(User user: userRepository.getAllUsers()){
            if(Objects.equals(userName, user.getNameUser())){
                return user;
            }
        }
        return null;
    }
    @Override
    public List<User> getUserList(){
        return userRepository.getAllUsers();
    }
}
