package com.example.movie.controller;


import com.example.movie.dto.UserDTO;
import com.example.movie.entity.LoginRequest;
import com.example.movie.entity.RegisterRequest;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.entity.User;
import com.example.movie.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/users")
public class UserController {

  UserServiceImpl userService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<ResponseMessage> getAllUser() {
    return new ResponseEntity<>(new ResponseMessage("Users retrieved successfully", userService.getAll()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getUser(@PathVariable int id) {
    UserDTO userDTO = userService.findUserById(id);
    return new ResponseEntity<>(new ResponseMessage("User retrieved successfully", userDTO), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody RegisterRequest registerRequest) {

    return new ResponseEntity<>(new ResponseMessage("created new user successfully", userService.createUser(registerRequest)), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable int id) {

    UserDTO updatedUser = userService.updateUser(id, userDTO);

    return new ResponseEntity<>(new ResponseMessage("Update user successfully", updatedUser), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteUser(@PathVariable int id) {

    boolean isDeleted = userService.deleteUser(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("User with id #" + id
          + " deleted successfully", null),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("User not found", null),
        HttpStatus.NOT_FOUND
      );
    }
  }


}
