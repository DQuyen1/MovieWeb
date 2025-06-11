package com.example.movie.service;

import com.example.movie.dto.UserDTO;
import com.example.movie.entity.*;


import java.util.List;

public interface UserService {

  List<UserDTO> getAll();

  UserDTO findUserById(int id);

  UserDTO createUser(RegisterRequest registerRequest);

  UserDTO updateUser(int userId, UserDTO userDTO);

  boolean deleteUser(int id);

  AuthReponse login(LoginRequest loginRequest);


}
