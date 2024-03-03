package com.example.messenger.mapper;

import com.example.messenger.domain.ImageMessage;
import com.example.messenger.dto.ImageMessageDto;
import com.example.messenger.dto.UserDto;
import com.example.messenger.mapper.api.ImageMessageInterface;
import org.springframework.stereotype.Component;

@Component
public class ImageMessageMapperImpl implements ImageMessageInterface {
    @Override
    public ImageMessageDto mapToImageMessageDto(ImageMessage imageMessage) {
        ImageMessageDto imageMessageDto = new ImageMessageDto();
        imageMessageDto.setId(imageMessage.getId());
        imageMessageDto.setMessage(imageMessage.getMessage());
        return imageMessageDto;
    }

    @Override
    public ImageMessage mapToImageMessage(ImageMessageDto dto) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setId(dto.getId());
        imageMessage.setMessage(dto.getMessage());
        return imageMessage;
    }
}
