package com.messenger.Messenger.service;

import com.messenger.Messenger.service.impl.ChatServiceInterface;
import com.messenger.Messenger.domain.*;
import com.messenger.Messenger.repository.ChatList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChatServiceImpl implements ChatServiceInterface {
    @Autowired
    private ChatList chatList;
    /*Создать новый чат*/
    @Override
    public Chat createChat(User creator, String name, boolean isPrivate,
                           String password, int maxUsers) {
        Chat chat;
        if (isPrivate) {
            chat = new PrivateChat(creator, name, maxUsers, password);
        } else {
            chat = new PublicChat(creator, name, maxUsers, null);
        }
        System.out.println("Создан чат " + name + " пользователем "+creator.getNameUser());
        chat.addUser(creator);
        chatList.getChats().add(chat);
        creator.addChat(chat);
        return chat;
    }
    /*Удалить чат*/
    @Override
    public List<Chat> deleteChat(User user, Chat chat) {
        if (user.getNameUser().equals(chat.getCreator().getNameUser())) {
            if (chatList.getChats().contains(chat)) {
                chatList.getChats().remove(chat);
                for (User u : chat.getUserList()) {
                    removeUserFromChat(u, chat);
                }
                return chatList.getChats();
            } else {
                return chatList.getChats();//если всё грустно вернем как было
            }
        }
      return null;
    }

    /*Добавить пользователя в чат*/
    @Override
    public void addUserToChat(User user, Chat chat, String password) {
        if(!(chat.isPrivate())) {
            chat.addUser(user);
        }else {
            if(Objects.equals(chat.getPassword(), password)){
                chat.addUser(user);
            }else {
                System.out.println(user.getNameUser()+" ввел неверный пароль для входа в чат "+chat.getChatName());
            }
        }
    }

    /*Удалить пользователя из чата*/
    @Override
    public void removeUserFromChat(User user, Chat chat) {
        chat.removeUser(user);
        for (Chat c : chatList.getChats()) {
            if (c != chat && c.getUserList().contains(user)) {
                c.removeUser(user);
            }
        }
    }

    /*Написать сообщение*/
    @Override
    public void writeMessage(User user, Chat chat, Message message) {
        if (chat.getUserList().contains(user)) {
            chat.writeMessage(user, message);
            System.out.println("Сообщение от пользователя: " + user.getNameUser() + " в чате " + chat.getChatName() + " '" + message + "'");
        } else {
            System.out.println(user.getNameUser() + " не может отправить сообщение в чат " + chat.getChatName() + ", так как он не является его участником");
        }
    }

    public List<Chat> getChatList() {
        return chatList.getChats();
    }
    @Override
    public Chat findChat(String chatName){
        for(Chat chat: chatList.getChats()){
            if(Objects.equals(chat.getChatName(), chatName))
                return chat;
        }
        return null;
    }
    @Override
    public Message newMessage(String message, boolean isText){
        Message message1;
        if(isText){
            return message1=new TextMessage(message);
        }else {
            return message1=new ImageMessage(message);
        }
    }
}
