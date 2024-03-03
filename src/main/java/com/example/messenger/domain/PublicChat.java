package com.example.messenger.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("public")
public class PublicChat extends Chat {
    public PublicChat(User creator, String chatName, int maxUser, String password) {
        super(creator, chatName, maxUser, password);
    }

    public PublicChat() {

    }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public String toString() {
        return "\nНазвание ПУБЛИЧНОГО чата: "+getChatName()+
                " Максимальное количество пользователей: "+ getMaxUser()+
                " Список пользователей в чате: "+ getUserList()+
                " История сообщений в чате: "+getMessagesHistory();
    }
}
