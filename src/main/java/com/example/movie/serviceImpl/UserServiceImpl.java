package com.example.movie.serviceImpl;


import com.example.movie.dto.UserDTO;
import com.example.movie.entity.User;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.UserMapper;
import com.example.movie.repository.UserRepository;
import com.example.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  final private UserMapper userMapper;
  final private UserRepository repo;



  @Autowired
  public UserServiceImpl(UserRepository repo, UserMapper userMapper, PasswordEncoder passwordEncode) {
    this.repo = repo;
    this.userMapper = userMapper;
  }


  @Override
  public List<UserDTO> getAll() {
    List<User> users = repo.findAll();
    return users.stream().map(userMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
  public UserDTO findUserById(int id) {
    User user = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("User not found");
    });;

    return userMapper.convertToDTO(user);

  }

  @Override
  public UserDTO createUser(User newUser) {
    User user  = repo.save(newUser);
    return userMapper.convertToDTO(user);
  }

  @Override
  public UserDTO updateUser(int userId, UserDTO userDTO) {
    User user = repo.findById(userId).orElseThrow(() -> {
      return new ResourceNotFoundException("this user is not exist");
    });

    user.setUsername(userDTO.getUsername());
    user.setUpdate_at(userDTO.getUpdate_at());
    user.setCreate_at(userDTO.getCreate_at());
    user.setIs_deleted(userDTO.isIs_deleted());

    User updatedUser = repo.save(user);

    return userMapper.convertToDTO(updatedUser);
  }

  @Override
  public boolean deleteUser(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }




}
