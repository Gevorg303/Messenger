package com.messenger.Messenger.domain;

public class ImageMessage extends Message{

    public ImageMessage(String message) {
        super(message);
    }

    @Override
    public String getMessageType() {
        return "Картинка";
    }
}
