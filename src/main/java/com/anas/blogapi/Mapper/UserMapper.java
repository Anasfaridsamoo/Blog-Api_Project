package com.anas.blogapi.Mapper;

import com.anas.blogapi.Dto.Response.UserResDto;
import com.anas.blogapi.entities.User;
import com.anas.blogapi.Dto.Request.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResDto userToResDto(User user);
    User DtoReqToUser(UserDto userDto);

}
