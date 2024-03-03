package com.example.messenger.service;

import com.example.messenger.domain.*;
import com.example.messenger.dto.ChatDto;
import com.example.messenger.dto.MessageDto;
import com.example.messenger.dto.UserDto;
import com.example.messenger.mapper.api.ChatMapperInterface;
import com.example.messenger.mapper.api.MessageMapperInterface;
import com.example.messenger.mapper.api.UserMapperInterface;
import com.example.messenger.repository.api.ChatRepositoryInterface;
import com.example.messenger.service.api.ChatServiceInterface;
import com.example.messenger.service.api.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ChatServiceImpl implements ChatServiceInterface {
    @Autowired
    private ChatRepositoryInterface chatRepository;
    @Autowired
    private ChatServiceInterface chatServiceInterface;
    @Autowired
    private UserMapperInterface userMapperInterface;
    @Autowired
    private ChatMapperInterface chatMapperInterface;
    @Autowired
    private MessageMapperInterface messageMapperInterface;

    @Override
    public Chat createChat(User creator,
                           String name,
                           boolean isPrivate,
                           String password,
                           int maxUsers) {
        try {
            Chat chat = chatRepository.createNewChat(creator, name, isPrivate, password, maxUsers);/*Создание чата*/
            log.info("Создан чат " + name + " пользователем "+creator.getNameUser());
            addUserToChat(creator, chat); /*Добавить пользователя в чат*/
            creator.addChatToUser(chat); /*Добавить чат в список чатов пользователя*/
            return chat;
        } catch (Exception e) {
            log.error("Ошибка при создании чата: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteChat(User user, Chat chat) {
        try {
            if (user.getNameUser().equals(chat.getCreator().getNameUser())) {
                chatRepository.delete(chat); /* Удалить чат */
                log.info("Чат успешно удален");
                List<User> userListCopy = new ArrayList<>(chat.getUserList());

                for (User u : userListCopy) {
                    removeUserFromChat(u, chat); /* Удаление пользователя из чата */
                    log.info("Пользователь из чата удален");
                }
            } else {
                log.info("Данный пользователь не может удалить этот чат");
            }
        } catch (Exception e) {
            log.error("Ошибка при удалении чата: " + e.getMessage());
        }
    }

    @Override
    public void addUserToChat(User user, Chat chat) {
        try {
            if (!chat.getUserList().contains(user)) {
                if (chat.getUserList().size() < chat.getMaxUser()) {
                    chat.getUserList().add(user);/*Добавить пользователя в чат*/
                    user.getChat().add(chat);/*Добавить чат в список чатов пользователя*/
                    log.info(user.getNameUser() + " присоединился к чату " + chat.getChatName());
                } else {
                    log.info("Чат " + chat.getChatName() + " полон, нельзя добавить больше пользователей.");
                }
            }
        }catch (Exception e){
            log.error("Ошибка при добавлении пользователя в чат: " + e.getMessage());
        }
    }
    /* Удалить пользователя из чата */
    @Override
    public void removeUserFromChat(User user, Chat chat) {
        try {
            if (chat.getUserList().contains(user)) {
                chat.getUserList().remove(user);/*Удалить из чата пользователя*/
                user.getChat().remove(chat);/*Удалить чат из списка чатов пользователя*/
                log.info(user.getNameUser() + " покинул чат '" + chat.getChatName() + "'.");
            }
        }catch (Exception e){
            log.error("Ошибка при удалении пользователя из чата: " + e.getMessage());
        }
    }

    /*Написать сообщение*/
    @Override
    public void writeMessage(User user, Chat chat, Message message) {
        try{
            if (chat.getUserList().contains(user)) {
                chat.writeMessage(user, message);
                log.info("Сообщение от пользователя: " + user.getNameUser() + " в чате " + chat.getChatName() + " '" + message + "'");
            } else {
                log.info(user.getNameUser() + " не может отправить сообщение в чат " + chat.getChatName() + ", так как он не является его участником");
            }
        }catch (Exception e){
            log.error("Ошибка при отправке сообщения: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Chat> getChatList(Pageable pageable) {
        return chatRepository.findAll(pageable);
    }

    @Override
    public Chat findChat(String chatName){
        try {
            for (Chat chat : chatRepository.findAll()) {
                if (Objects.equals(chat.getChatName(), chatName))
                    return chat;
            }
            return null;
        } catch (Exception e) {
            log.error("Ошибка при поиске чата: " + e.getMessage());
            return null;
        }
    }
    @Override
    public Message newMessage(String message, boolean isText){
        try {
            if (isText) {
                return new TextMessage(message);
            } else {
                return new ImageMessage(message);
            }
        } catch (Exception e) {
            log.error("Ошибка при создании сообщения: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void removeChat(ChatDto chatDto, UserDto userDto) {
        Chat chat1 = chatMapperInterface.mapToChat(chatDto);
        User user1 = userMapperInterface.mapToUser(userDto);
        chatServiceInterface.deleteChat(user1, chat1);
    }

    @Override
    @Transactional
    public Chat addChat(UserDto userDto, ChatDto chatDto) {
        User user1 = userMapperInterface.mapToUser(userDto);
        Chat chat1 = chatMapperInterface.mapToChat(chatDto);
        return chat1;
    }

    @Override
    @Transactional
    public void addUserChat(ChatDto chatDto, UserDto userDto) {
        Chat chat1= chatMapperInterface.mapToChat(chatDto);
        User user1=userMapperInterface.mapToUser(userDto);
        chatServiceInterface.addUserToChat(user1,chat1);
    }

    @Override
    @Transactional
    public void deleteUserToChat(UserDto userDto, ChatDto chatDto) {
        User user=userMapperInterface.mapToUser(userDto);
        Chat chat=chatMapperInterface.mapToChat(chatDto);
        chatServiceInterface.removeUserFromChat(user, chat);
    }

    @Override
    @Transactional
    public void writeMessageUser(UserDto userDto, ChatDto chatDto, MessageDto messageDto) {
        User user=userMapperInterface.mapToUser(userDto);
        Chat chat=chatMapperInterface.mapToChat(chatDto);
        Message message1=messageMapperInterface.mapToMessage(messageDto);
        chatServiceInterface.writeMessage(user, chat, message1);
    }
}
