package com.example.messenger.domain;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "message")
@Inheritance(strategy = jakarta.persistence.InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "message_type", discriminatorType = jakarta.persistence.DiscriminatorType.STRING)
public abstract class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "message")
    private String message;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public Message(String message) {
        this.message = message;
    }

    public Message() {

    }

    public abstract String getMessageType();

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
