package com.example.messenger.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

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
