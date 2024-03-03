package com.example.messenger.dto;

import com.example.messenger.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class PublicChatDto extends ChatDto{
    public PublicChatDto(Long id, String chatName, int maxUser) {
        super(id, chatName, maxUser);
    }

    public boolean isPrivate() {
        return false;
    }

}
