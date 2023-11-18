package com.messenger.Messenger.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ImageMessage")
public class ImageMessage extends Message{

    public ImageMessage(String message) {
        super(message);
    }

    public ImageMessage() {
    }

    @Override
    public String getMessageType() {
        return "Картинка";
    }
}
