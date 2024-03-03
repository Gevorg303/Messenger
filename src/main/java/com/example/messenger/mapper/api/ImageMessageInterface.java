package com.example.messenger.mapper.api;

import com.example.messenger.domain.ImageMessage;
import com.example.messenger.dto.ImageMessageDto;

public interface ImageMessageInterface {
    ImageMessageDto mapToImageMessageDto(ImageMessage imageMessage);
    ImageMessage mapToImageMessage(ImageMessageDto dto);
}
