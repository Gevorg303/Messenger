package com.messenger.Messenger.domain;
import javax.persistence.*;

@Entity
@Table(name = "Message")
@Inheritance(strategy = javax.persistence.InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "message_type", discriminatorType = javax.persistence.DiscriminatorType.STRING)
public abstract class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "message")
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message() {

    }

    public abstract String getMessageType();
    public String getMessage() {
        return message;
    }

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

    public Long getId() {
        return id;
    }
}
