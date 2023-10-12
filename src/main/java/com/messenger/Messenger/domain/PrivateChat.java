package com.messenger.Messenger.domain;

import java.util.List;

public class PrivateChat extends Chat {

    public PrivateChat(User creator, String chatName, int maxUser, String password) {
        super(creator, chatName, maxUser, password);
    }

    @Override
    public boolean isPrivate() {
        return true;
    }
    @Override
    public String toString() {
        return "\nНазвание ПРИВАТНОГО чата: "+getChatName()+" Максимальное количество пользователей: "+ getMaxUser()+" Список пользователей в чате: "+ getUserList()+" История сообщений в чате: "+getMessagesHistory();
    }
}
