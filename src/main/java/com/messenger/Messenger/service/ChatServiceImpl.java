package com.messenger.Messenger.service;

import com.messenger.Messenger.domain.*;
import com.messenger.Messenger.repository.impl.ChatRepositoryInterface;
import com.messenger.Messenger.service.impl.ChatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChatServiceImpl implements ChatServiceInterface {
    @Autowired
    private ChatRepositoryInterface chatRepository;
    /*Создать новый чат*/
    @Override
    public Chat createChat(User creator, String name, boolean isPrivate,
                           String password, int maxUsers) {
        Chat chat = chatRepository.createNewChat(creator, name, isPrivate, password, maxUsers);
        System.out.println("Создан чат " + name + " пользователем "+creator.getNameUser());
        //chat.addUser(creator);
        creator.addChat(chat);
        return chat;
    }
    /*Удалить чат*/
    @Override
    public void deleteChat(User user, Chat chat) {
        if (user.getNameUser().equals(chat.getCreator().getNameUser())) {
            chatRepository.delete(chat);
            System.out.println("Чат успещно удален");
                for (User u : chat.getUserList()) {
                    //removeUserFromChat(u, chat);
                    System.out.println("Пользователи из чата удалены");
                }
        }
        else {
            System.out.println("Данный пользователь не может удалить этот чат");
        }
    }

    /*Добавить пользователя в чат*/
//    @Override
//    public void addUserToChat(User user, Chat chat, String password) {
//        if(!(chat.isPrivate())) {
//            chat.addUser(user);
//        }else {
//            if(Objects.equals(chat.getPassword(), password)){
//                chat.addUser(user);
//            }else {
//                System.out.println(user.getNameUser()+" ввел неверный пароль для входа в чат "+chat.getChatName());
//            }
//        }
//    }

    /*Удалить пользователя из чата*/
//    @Override
//    public void removeUserFromChat(User user, Chat chat) {
//        chatRepositoryInterface.removeUserFromChat(chat, user);
//    }

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
