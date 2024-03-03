package com.example.messenger.dto;

import com.example.messenger.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class PrivateChatDto extends ChatDto{
    private String password;
    public PrivateChatDto(Long id, String chatName, int maxUser, String password) {
        super(id, chatName, maxUser);
        this.password = password;
    }

    public boolean isPrivate() {
        return true;
    }
}
