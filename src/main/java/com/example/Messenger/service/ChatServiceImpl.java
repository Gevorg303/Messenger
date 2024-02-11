package com.example.Messenger.service;

import com.example.Messenger.domain.*;
import com.example.Messenger.repository.impl.ChatRepositoryInterface;
import com.example.Messenger.service.impl.ChatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ChatServiceImpl implements ChatServiceInterface {
    @Autowired
    private ChatRepositoryInterface chatRepository;

    /*Создать новый чат*/
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Chat createChat(User creator, String name, boolean isPrivate,
                           String password, int maxUsers) {
        Chat chat = chatRepository.createNewChat(creator, name, isPrivate, password, maxUsers);/*Создание чата*/
        System.out.println("Создан чат " + name + " пользователем "+creator.getNameUser());
        addUserToChat(creator, chat); /*Добавить пользователя в чат*/
        creator.addChatToUser(chat); /*Добавить чат в список чатов пользователя*/
        return chat;

    }
    /*Удалить чат*/
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteChat(User user, Chat chat) {
        if (user.getNameUser().equals(chat.getCreator().getNameUser())) {
            chatRepository.delete(chat); /* Удалить чат */
            System.out.println("Чат успешно удален");
            List<User> userListCopy = new ArrayList<>(chat.getUserList());

            for (User u : userListCopy) {
                removeUserFromChat(u, chat); /* Удаление пользователя из чата */
                System.out.println("Пользователь из чата удален");
            }
        } else {
            System.out.println("Данный пользователь не может удалить этот чат");
        }
    }

    @Override
    public void addUserToChat(User user, Chat chat) {
        if (!chat.getUserList().contains(user)) {
            if (chat.getUserList().size() < chat.getMaxUser()) {
                chat.getUserList().add(user);/*Добавить пользователя в чат*/
                user.getChat().add(chat);/*Добавить чат в список чатов пользователя*/
                System.out.println(user.getNameUser() + " присоединился к чату " + chat.getChatName());
            } else {
                System.out.println("Чат " + chat.getChatName() + " полон, нельзя добавить больше пользователей.");
            }
        }
    }
    /* Удалить пользователя из чата */
    public void removeUserFromChat(User user, Chat chat) {
        if (chat.getUserList().contains(user)) {
            chat.getUserList().remove(user);/*Удалить из чата пользователя*/
            user.getChat().remove(chat);/*Удалить чат из списка чатов пользователя*/
            System.out.println(user.getNameUser() + " покинул чат '" + chat.getChatName() + "'.");
        }
    }

    /*Написать сообщение*/
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void writeMessage(User user, Chat chat, Message message) {
        if (chat.getUserList().contains(user)) {
            chat.writeMessage(user, message);
            System.out.println("Сообщение от пользователя: " + user.getNameUser() + " в чате " + chat.getChatName() + " '" + message + "'");
        } else {
            System.out.println(user.getNameUser() + " не может отправить сообщение в чат " + chat.getChatName() + ", так как он не является его участником");
        }
    }
    @Override
    public List<Chat> getChatList() {
        return chatRepository.findAll();
    }
    @Override
    public Chat findChat(String chatName){
        for(Chat chat: chatRepository.findAll()){
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
