package com.example.Messenger.service;

import com.example.Messenger.repository.impl.UserRepositoryInterface;
import com.example.Messenger.service.impl.UserServiceInterface;
import com.example.Messenger.domain.Admin;
import com.example.Messenger.domain.Chat;
import com.example.Messenger.domain.User;
import com.example.Messenger.repository.ChatRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String createUser(String username) {
        try {
            User user = new User(username);
            userRepository.save(user);
            return "Пользователь " + user.getNameUser() + " создан.";
        }catch (Exception e){
            return "Ошибка при создании пользователя: " + e.getMessage();
        }
    }

    /*Удалить пользователя из мессенджера*/
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteUser(User user) {
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    /*Получить чаты пользователя*/
    @Override
    public List<Chat> getUserChats(User user) {
        return user.getChat();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Chat createChat(ChatServiceImpl chatService, User user, String name, boolean isPrivate, String password, int maxUsers) {
        try {
            Chat chat = chatService.createChat(user, name, isPrivate, password, maxUsers);
            chatRepository.save(chat);
            return chat;
        } catch (Exception e) {
            System.out.println("Ошибка при создании чата: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteChat(ChatServiceImpl chatService, User user, Chat chat) {
        try {
            chatService.deleteChat(user, chat);
        } catch (Exception e) {
            System.out.println("Ошибка при удалении чата: " + e.getMessage());
        }
    }

    @Override
    public void changeChatMaxUsers(AdminServiceImpl adminService, Admin admin, Chat chat, int maxUsers) {
        try {
            adminService.changeChatMaxUsers(admin, chat, maxUsers);
        } catch (Exception e) {
            System.out.println("Ошибка при изменении максимального количества пользователей в чате: " + e.getMessage());
        }
    }

    /*Удаляем чаты пользователя*/
    @Override
    public void deleteUserAndChats(ChatServiceImpl chatService, User user) {
        try {
            deleteUser(user);
            List<Chat> userChatsCopy = new ArrayList<>(user.getChat());
            for (Chat chat : userChatsCopy) {
                chatService.deleteChat(user, chat);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя и его чатов: " + e.getMessage());
        }
    }

    @Override
    public User findUser(String userName) {
        try {
            for (User user : userRepository.getAllUsers()) {
                if (Objects.equals(userName, user.getNameUser())) {
                    return user;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске пользователя: " + e.getMessage());
            return null;
        }
    }
    @Override
    public List<User> getUserList(){
        return userRepository.getAllUsers();
    }
}
