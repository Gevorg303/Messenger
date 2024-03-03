package com.example.messenger.mapper.api;

import com.example.messenger.domain.User;
import com.example.messenger.dto.UserDto;

public interface UserMapperInterface {
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
