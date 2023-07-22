package com.myblog.blog;

import com.myblog.blog.entities.User;
import com.myblog.blog.exceptions.ResourceNotFoundException;
import com.myblog.blog.payloads.UserDto;
import com.myblog.blog.repositories.UserRepo;
import com.myblog.blog.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = { UserServiceImplTest.class })
public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("johndoe@example.com");

        User user = new User();
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");

        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto savedUserDto = userService.createUser(userDto);

        assertEquals(userDto.getName(), savedUserDto.getName());
        assertEquals(userDto.getEmail(), savedUserDto.getEmail());

        verify(modelMapper, times(1)).map(userDto, User.class);
        verify(userRepo, times(1)).save(any(User.class));
        verify(modelMapper, times(1)).map(user, UserDto.class);
    }

    @Test
    public void testUpdateUser() {
        Integer userId = 1;
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("johndoe@example.com");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("Jane Smith");
        existingUser.setEmail("janesmith@example.com");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("John Doe");
        updatedUser.setEmail("johndoe@example.com");

        when(userRepo.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepo.save(any(User.class))).thenReturn(updatedUser);
        when(modelMapper.map(updatedUser, UserDto.class)).thenReturn(userDto);

        UserDto updatedUserDto = userService.updateUser(userDto, userId);

        assertEquals(userId, updatedUserDto.getId());
        assertEquals(userDto.getName(), updatedUserDto.getName());
        assertEquals(userDto.getEmail(), updatedUserDto.getEmail());

        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, times(1)).save(any(User.class));
        verify(modelMapper, times(1)).map(updatedUser, UserDto.class);
    }

    @Test
    public void testGetUserById_UserExists() {
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(new UserDto());
          UserDto userDto = userService.getUserById(userId);

        assertEquals(userId, user.getId());

        verify(userRepo, times(1)).findById(userId);
        verify(modelMapper, times(1)).map(user, UserDto.class);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        Integer userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));

        verify(userRepo, times(1)).findById(userId);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("johndoe@example.com");

        User user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("janesmith@example.com");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userRepo.findAll()).thenReturn(userList);
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());

        List<UserDto> userDtoList = userService.getAllUsers();

        assertEquals(userList.size(), userDtoList.size());

        verify(userRepo, times(1)).findAll();
        verify(modelMapper, times(userList.size())).map(any(User.class), eq(UserDto.class));
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 1;
        User user = new User();

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, times(1)).delete(user);
    }
}
