package com.anas.blogapi.services;

import com.anas.blogapi.Dto.Request.UserDto;
import com.anas.blogapi.Dto.Response.UserResDto;

import java.util.List;

public interface UserService {
    UserResDto createUser(UserDto user);
    UserResDto updateUser(UserDto user,Integer userId);
    UserResDto getUserById(Integer userId);
    List<UserResDto> getAllUsers();
    void deleteUser(Integer userId);
}
