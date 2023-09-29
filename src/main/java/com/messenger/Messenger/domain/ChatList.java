package com.messenger.Messenger.domain;

import com.messenger.Messenger.domain.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ChatList {
    private List<Chat> chats;/*Список всех чатов*/

    public ChatList() {
        this.chats = new ArrayList<>();
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }
}
