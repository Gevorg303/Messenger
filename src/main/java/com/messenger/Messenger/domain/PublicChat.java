package com.messenger.Messenger.domain;

import java.util.List;

public class PublicChat extends Chat {
    public PublicChat(User creator, String chatName, int maxUser, String password) {
        super(creator, chatName, maxUser, password);
    }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public String toString() {
        return "\nНазвание ПУБЛИЧНОГО чата: "+getChatName()+" Максимальное количество пользователей: "+ getMaxUser()+" Список пользователей в чате: "+ getUserList()+" История сообщений в чате: "+getMessagesHistory();
    }
}
