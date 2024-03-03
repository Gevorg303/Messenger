package com.example.messenger.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ChatDto {
    private Long id;
    private String chatName;
    private int maxUser;

    public ChatDto(Long id, String chatName, int maxUser) {
        this.id = id;
        this.chatName = chatName;
        this.maxUser = maxUser;
    }

    public abstract boolean isPrivate();
}
