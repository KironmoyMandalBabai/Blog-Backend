package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.UserDto;
import jakarta.persistence.Id;

import java.util.List;

public interface UserService  {


    UserDto createUser (UserDto user);
    UserDto updateUser (UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto>getAllUsers();
    void deleteUser(Integer userId);
}
