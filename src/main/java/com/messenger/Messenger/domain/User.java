package com.messenger.Messenger.domain;

import com.messenger.Messenger.domain.impl.UserInterface;
import lombok.Getter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "User")
public class User implements UserInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String nameUser;

    @OneToMany
    @JoinColumn(name = "id_chat_list")
    private List<Chat> chat;/*Список чатов пользователя*/

    public User(String nameUser) {
        this.nameUser = nameUser;
        this.chat = new ArrayList<>();
    }

    public User() {

    }

    /*Чат добавить в список чатов пользователя*/
    @Override
    public void addChat(Chat chat) {
        getChat().add(chat);
    }

    /*Чат удалить из списка чатов пользователя*/
    @Override
    public void removeChat(Chat chat) {
        getChat().remove(chat);
    }

    @Override
    public String toString() {
        return "Имя пользователя: "+getNameUser();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }
}
