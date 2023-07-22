package com.myblog.blog.services;

import com.myblog.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    // UserDto registerNewUser(UserDto user);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
