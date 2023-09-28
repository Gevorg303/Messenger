package com.messenger.Messenger.domain;

public abstract class Message {
    private String message;

    public Message(String message) {
        this.message = message;
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
}
