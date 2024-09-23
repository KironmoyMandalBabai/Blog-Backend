package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

@Autowired
    private UserService userService;

    //POST - create user

    @PostMapping
public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
{
   UserDto createUserDto= this.userService.createUser(userDto);
    return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
}

    //PUT - update user

        @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto ,@PathVariable("userId") Integer uid){
       UserDto updateUser= this.userService.updateUser(userDto, uid);
        return  ResponseEntity.ok(updateUser);
    }

    //DELETE  -delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?>deleteUser(@PathVariable("userId")Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<>(Map.of("messege","User deleted successfully"),HttpStatus.OK);

    }

    // GET - user get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUser(){
        return ResponseEntity.ok((this.userService.getAllUsers()));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId) {

        return ResponseEntity.ok(this.userService.getUserById(userId));

    }

}
