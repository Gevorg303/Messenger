package com.messenger.Messenger.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TextMessage")
public class TextMessage extends Message{
    public TextMessage(String message) {
        super(message);
    }

    public TextMessage() {

    }

    @Override
    public String getMessageType() {
        return getMessage();
    }
}
