package com.example.messenger.dto;

import lombok.Data;

@Data
public abstract class MessageDto {
    private Long id;
    private String message;
}
