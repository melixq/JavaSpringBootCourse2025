package ru.kuznetsov.lab8.service;

import ru.kuznetsov.lab8.dto.UserDto;
import ru.kuznetsov.lab8.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
