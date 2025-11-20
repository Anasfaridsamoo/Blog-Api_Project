package com.anas.blogapi.controllers;

import com.anas.blogapi.Dto.Response.UserResDto;
import com.anas.blogapi.payloads.ApiResponse;
import com.anas.blogapi.Dto.Request.UserDto;
import com.anas.blogapi.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

private final UserServiceImpl userService;

    @PostMapping("/admin/")
    public ResponseEntity<UserResDto> createUser(@Valid @RequestBody UserDto userDto){
        UserResDto CreatedUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(CreatedUserDto, HttpStatus.CREATED);
    }
    @PreAuthorize("@userSecurity.isPostOwner(#userId,authentication)")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResDto> UpdateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        UserResDto updatedUserDto = this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUserDto);
    }

    @GetMapping("/admin/{userId}")
    public ResponseEntity<UserResDto> GetUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @GetMapping("/admin/")
    public ResponseEntity<List<UserResDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PreAuthorize("@userSecurity.isPostOwner(#userId,authentication)")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",HttpStatus.OK),HttpStatus.OK);
    }

}
