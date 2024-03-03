package com.example.messenger.mapper;

import com.example.messenger.domain.User;
import com.example.messenger.dto.UserDto;
import com.example.messenger.mapper.api.UserMapperInterface;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapperInterface {
    @Override
    public UserDto mapToUserDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setNameUser(user.getNameUser());
        return dto;
    }

    @Override
    public User mapToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setNameUser(userDto.getNameUser());
        return user;
    }
}
