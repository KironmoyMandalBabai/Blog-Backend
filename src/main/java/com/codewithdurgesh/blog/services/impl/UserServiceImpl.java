package com.codewithdurgesh.blog.services.impl;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;
import jakarta.transaction.UserTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    private java.util.stream.Collectors Collectors;

    @Override
    public UserDto createUser(UserDto userDto) {
       User user= this.dtoToUser(userDto);
       user=this.userRepo.save(user);

        return this.UserToDto(user);
    }


    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

      User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());

        User updateUser=this.userRepo.save(user);
        UserDto userDto1 =this.UserToDto(updateUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
     User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return this.UserToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User>users= this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(java.util.stream.Collectors.toList());


        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
//        User user =new User();
//        user.setId(userDto.getId());
//        user.setEmail(userDto.getEmail());
//        user.setName(userDto.getName());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
return user;
    }
    public UserDto UserToDto(User user) {

        UserDto userDto=this.modelMapper.map(user,UserDto.class);

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId()); // Set the ID from the User entity
//        userDto.setEmail(user.getEmail()); // Set the Email from the User entity
//        userDto.setName(user.getName()); // Set the Name from the User entity
//        userDto.setAbout(user.getAbout()); // Set the About from the User entity
//        userDto.setPassword(user.getPassword()); // Set the Password from the User entity
        return userDto;
    }

}
