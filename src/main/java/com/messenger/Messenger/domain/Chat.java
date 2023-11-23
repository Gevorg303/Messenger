package com.messenger.Messenger.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Chat")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "chat_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_chat")
    private String chatName;/*Название чата*/
    @OneToOne(mappedBy = "creator")
    private User creator;/*Создатель чата*/
    @Column(name = "max_user")
    private int maxUser;/*Максимальное количество пользователей*/
    @OneToMany(mappedBy = "chat_user")
    private List<User> userList;/*Список пользователей в чате*/
    @OneToMany(mappedBy = "messagesHistory")
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
     /*Добавить пользователя в чат*/
//    public void addUser(User user) {
//        if (!getUserList().contains(user)) {
//            if (getUserList().size() < getMaxUser()) {
//                getUserList().add(user);
//                user.addChat(this);
//                System.out.println(user.getNameUser() + " присоединился к чату " + getChatName());
//            } else {
//                System.out.println("Чат " + getChatName() + " полон, нельзя добавить больше пользователей.");
//            }
//        }
//    }

    /*Удалить пользователя из чата*/
//    public void removeUser(User user) {
//        if (getUserList().contains(user)) {
//            getUserList().remove(user);
//            user.removeChat(this);
//            System.out.println(user.getNameUser() + " покинул чат '" + getChatName() + "'.");
//        }
//    }

    /*Проверить чат на приватность*/
    public abstract boolean isPrivate();

    /*Написать сообщение в чат*/
    public void writeMessage(User user, Message message){
        if(getUserList().contains(user)){
            getMessagesHistory().add(message);
        }
        else {System.out.println(user.getNameUser()+ " не может написать сообщение, так как он не является участником этого чата");
        }
    }


    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Message> getMessagesHistory() {
        return messagesHistory;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String getChatName() {
        return chatName;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public void setMessagesHistory(List<Message> messagesHistory) {
        this.messagesHistory = messagesHistory;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setMaxUser(int maxUser) {
        this.maxUser = maxUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
