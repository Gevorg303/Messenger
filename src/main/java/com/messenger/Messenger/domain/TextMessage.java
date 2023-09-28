package com.messenger.Messenger.domain;

public class TextMessage extends Message{
    public TextMessage(String message) {
        super(message);
    }

    @Override
    public String getMessageType() {
        return getMessage();
    }
}
