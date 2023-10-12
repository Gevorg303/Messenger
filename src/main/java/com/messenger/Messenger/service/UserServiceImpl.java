package com.messenger.Messenger.service;

import com.messenger.Messenger.service.impl.UserServiceInterface;
import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.repository.UserList;
import com.messenger.Messenger.repository.ChatList;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private UserList userList;
    @Autowired
    private ChatList chatList;
    @Override
    public List<User> getUserList(){
        return userList.getUsers();
    }

    /*Создать нового пользователя*/
    @Override
    public String createUser(String username) {
        User user = new User(username);
        userList.getUsers().add(user);
        return "Пользователь " + user.getNameUser() + " создан.";
    }

    /*Удалить пользователя из мессенджера*/
    @Override
    public String deleteUser(User user) {
        List<User> usersToRemove = new ArrayList<>();

        // Поиск пользователей для удаления
        for (User user1 : userList.getUsers()) {
            if (user1.getNameUser().equals(user.getNameUser())) {
                usersToRemove.add(user);
            }
        }

        // Удаление пользователей из списка
        userList.getUsers().removeAll(usersToRemove);

        // Удаление пользователя из чатов
        for (Chat chat : chatList.getChats()) {

            if (chat.getUserList().contains(user)) {
                chat.removeUser(user);
            }
        }

        if (!usersToRemove.isEmpty()) {
            return "Пользователь "+user.getNameUser()+" удален.";
        } else {
            return "Пользователь "+user.getNameUser()+" не найден.";
        }
    }

    /*Получить чаты пользователя*/
    @Override
    public List<Chat> getUserChats(User user) {
        return user.getChatMessagesUser();
    }

    @Override
    public Chat createChat(ChatServiceImpl chatService, ChatList chatList, User user, String name, boolean isPrivate, String password, int maxUsers) {
        Chat chat = chatService.createChat(user, name, isPrivate, password, maxUsers);
        chatList.getChats().add(chat);
        return chat;
    }

    @Override
    public void deleteChat(ChatServiceImpl chatService, ChatList chatList, User user, Chat chat) {
        chatService.deleteChat(user, chat);
    }

    @Override
    public void changeChatMaxUsers(AdminServiceImpl adminService, Admin admin, Chat chat, int maxUsers) {
        adminService.changeChatMaxUsers(admin, chat, maxUsers);
    }

    /*Удаляем чаты пользователя*/
    @Override
    public void deleteUserAndChats(ChatServiceImpl chatService, ChatList chatList, User user) {
        deleteUser(user);
        List<Chat> userChatsCopy = new ArrayList<>(user.getChatMessagesUser());
        for (Chat chat : userChatsCopy) {
            chatService.deleteChat(user, chat);
        }
    }

    @Override
    public User findUser(String userName) {
        for(User user:userList.getUsers()){
            if(Objects.equals(userName, user.getNameUser())){
                return user;
            }
        }
        return null;
    }
}
