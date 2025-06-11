package com.example.movie.controller;

import com.example.movie.dto.UserDTO;
import com.example.movie.entity.AuthReponse;
import com.example.movie.entity.LoginRequest;
import com.example.movie.entity.RegisterRequest;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/auth")
public class AuthController {


  UserServiceImpl userService;

  public AuthController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseMessage> login(@Valid @RequestBody LoginRequest loginRequest) {
    AuthReponse authReponse = userService.login(loginRequest);
    return new ResponseEntity<>(new ResponseMessage("User Login Successfully", authReponse), HttpStatus.OK);
  }


  @PostMapping("/register")
  public ResponseEntity<ResponseMessage> register(@Valid @RequestBody RegisterRequest registerRequest) {
    UserDTO userDTO = userService.createUser(registerRequest);
    return new ResponseEntity<>(new ResponseMessage("User Register Successfully", userDTO), HttpStatus.OK);
  }





}
