package com.messenger.Messenger.repository;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.PrivateChat;
import com.messenger.Messenger.domain.PublicChat;
import com.messenger.Messenger.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class ChatList {
    private List<Chat> chats;
    @Autowired
    public ChatList(List<Chat> chats) {
        this.chats = chats;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public String toString() {
        return "ChatList{" +
                "chats=" + chats +
                '}';
    }
}
