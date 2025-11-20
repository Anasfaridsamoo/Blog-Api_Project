package com.anas.blogapi.services.impl;

import com.anas.blogapi.Dto.Response.UserResDto;
import com.anas.blogapi.Mapper.UserMapper;
import com.anas.blogapi.entities.User;
import com.anas.blogapi.exceptions.ResourceNotFoundException;
import com.anas.blogapi.Dto.Request.UserDto;
import com.anas.blogapi.respositories.UserRepo;
import com.anas.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserResDto createUser(UserDto userDto) {
        User user = this.userMapper.DtoReqToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userMapper.userToResDto(savedUser);

    }

    @Override
    public UserResDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User savedUser = this.userRepo.save(user);
        return this.userMapper.userToResDto(savedUser);

    }

    @Override
    public UserResDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.userMapper.userToResDto(user);
    }

    @Override
    public List<UserResDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserResDto> userDtos = users.stream().map(user-> this.userMapper.userToResDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

}
