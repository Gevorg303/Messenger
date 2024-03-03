package com.example.messenger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Data
@Entity
@Table(name = "chat")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "chat_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_chat")
    private String chatName;/*Название чата*/
    @ManyToOne
    private User creator;/*Создатель чата*/
    @Column(name = "max_user")
    private int maxUser;/*Максимальное количество пользователей*/
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList;/*Список пользователей в чате*/
    @JsonIgnore
    @OneToMany(mappedBy = "chat")
    private List<Message> messagesHistory;/*История сообщений в чате*/
    @Column(name = "password")
    private String password;/*Пароль чата*/


    public Chat(User creator, String chatName, int maxUser, String password) {
        this.chatName = chatName;
        this.maxUser = maxUser;
        this.userList = new ArrayList<>();
        this.messagesHistory=new ArrayList<>();
        this.password=password;
        this.creator=creator;
    }

    public Chat() {
    }
    /*Проверить чат на приватность*/
    public abstract boolean isPrivate();

    /*Написать сообщение в чат*/
    public void writeMessage(User user, Message message){
        if(getUserList().contains(user)){
            getMessagesHistory().add(message);
        }
        else {log.info(user.getNameUser()+ " не может написать сообщение, так как он не является участником этого чата");
        }
    }
}
