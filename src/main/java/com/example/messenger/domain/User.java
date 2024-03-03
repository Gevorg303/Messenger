package com.example.messenger.domain;

import com.example.messenger.domain.api.UserInterface;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@Table(name = "users")
public class User implements UserInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String nameUser;

    @JsonIgnore
    @OneToMany(mappedBy = "creator")
    private List<Chat> createdChats;

    @JsonIgnore
    @ManyToMany(mappedBy = "userList")
    private List<Chat> chat;/*Список чатов пользователя*/

    public User(String nameUser) {
        this.nameUser = nameUser;
        this.chat = new ArrayList<>();
    }

    public User() {

    }
    @Override
    public void addChatToUser(Chat chat) {
        if (!this.chat.contains(chat)) {
            this.chat.add(chat);
            chat.getUserList().add(this);
        }
    }
    @Override
    public String toString() {
        return "Имя пользователя: "+getNameUser();
    }
}
