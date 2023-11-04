package com.messenger.Messenger.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nameUser;

    @OneToMany
    @JoinColumn(name = "chat_user")
    private List<Chat> chat;/*Список чатов пользователя*/

    public User(String nameUser) {
        this.nameUser = nameUser;
        //this.chat = new ArrayList<>();
    }
    public User() {

    }

    /*Чат добавить в список чатов пользователя*/
//    @Override
//    public void addChat(Chat chat) {
//        getChat().add(chat);
//    }
//
//    /*Чат удалить из списка чатов пользователя*/
//    @Override
//    public void removeChat(Chat chat) {
//        getChat().remove(chat);
//    }

    @Override
    public String toString() {
        return "Имя пользователя: "+getNameUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public List<Chat> getChat() {
        return chat;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }
}
