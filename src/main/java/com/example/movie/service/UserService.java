package com.example.movie.service;

import com.example.movie.dto.UserDTO;
import com.example.movie.entity.User;


import java.util.List;

public interface UserService {

  List<UserDTO> getAll();

  UserDTO findUserById(int id);

  UserDTO createUser(User newUser);

  UserDTO updateUser(int userId, UserDTO userDTO);

  boolean deleteUser(int id);


}
