package com.messenger.Messenger.domain;

import com.messenger.Messenger.domain.impl.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class User implements UserInterface {
    private String nameUser;
    private List<Chat> chatMessagesUser;/*Список чатов пользователя*/

    public User(String nameUser) {
        this.nameUser = nameUser;
        this.chatMessagesUser = new ArrayList<>();
    }

    /*Чат добавить в список чатов пользователя*/
    @Override
    public void addChat(Chat chat) {
        getChatMessagesUser().add(chat);
    }

    /*Чат удалить из списка чатов пользователя*/
    @Override
    public void removeChat(Chat chat) {
        getChatMessagesUser().remove(chat);
    }

    @Override
    public String toString() {
        return "Имя пользователя: "+getNameUser();
    }


    public List<Chat> getChatMessagesUser() {
        return chatMessagesUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setChatMessagesUser(List<Chat> chatMessagesUser) {
        this.chatMessagesUser = chatMessagesUser;
    }
}
