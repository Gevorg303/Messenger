package com.messenger.Messenger.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("private")
public class PrivateChat extends Chat {

    public PrivateChat(User creator, String chatName, int maxUser, String password) {
        super(creator, chatName, maxUser, password);
    }

    public PrivateChat() {


    }

    @Override
    public boolean isPrivate() {
        return true;
    }
//    @Override
//    public String toString() {
//        return "\nНазвание ПРИВАТНОГО чата: "+getChatName()+" Максимальное количество пользователей: "+ getMaxUser()+" Список пользователей в чате: "+ getUserList()+" История сообщений в чате: "+getMessagesHistory();
//    }
}
