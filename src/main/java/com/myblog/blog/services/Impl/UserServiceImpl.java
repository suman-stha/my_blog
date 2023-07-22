package com.myblog.blog.services.Impl;

import com.myblog.blog.entities.User;
import com.myblog.blog.exceptions.ResourceNotFoundException;
import com.myblog.blog.payloads.UserDto;
import com.myblog.blog.repositories.UserRepo;
import com.myblog.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelmapper;
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public User dtoToUser(UserDto userDto) {
        User user = this.modelmapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelmapper.map(user, UserDto.class);
        return userDto;
    }

    // @Override
    // public UserDto registerNewUser(UserDto userDto) {
    //     User user = dtoToUser(userDto);
    //     // encode the password
    //     user.setPassword(passwordEncoder.encode(user.getPassword()));
    //     ;
    // }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = userRepo.save(user);
        UserDto userDto1 = userToDto(updateUser);
        return userDto1;

    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        UserDto userDto = userToDto(user);

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepo.delete(user);
    }
}
