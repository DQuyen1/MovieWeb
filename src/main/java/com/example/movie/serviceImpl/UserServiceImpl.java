package com.example.movie.serviceImpl;


import com.example.movie.config.JwtConfig;
import com.example.movie.dto.UserDTO;
import com.example.movie.entity.*;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.UserMapper;
import com.example.movie.repository.UserRepository;
import com.example.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  final private UserMapper userMapper;
  final private UserRepository repo;
  final private PasswordEncoder passwordEncoder;
  final private JwtConfig jwt;



  @Autowired
  public UserServiceImpl(UserRepository repo, UserMapper userMapper, PasswordEncoder passwordEncode, JwtConfig jwt) {
    this.repo = repo;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncode;
    this.jwt = jwt;
  }


  @Override
  public List<UserDTO> getAll() {
    List<User> users = repo.findAll();
    return users.stream().map(userMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
//  @Cacheable(value = "USER_CACHE", key="#id")
  public UserDTO findUserById(int id) {
    User user = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("User not found");
    });;

    return userMapper.convertToDTO(user);

  }

  @Override
  @CachePut(value = "USER_CACHE", key="#result.id()")
  public UserDTO createUser(RegisterRequest registerRequest) {

    User newUser = new User();
    String newUserName = registerRequest.getUsername();
    String password = registerRequest.getPassword();

    User existingUser = repo.findByUsername(newUserName);
    if (existingUser != null) {
      throw new RuntimeException("User already exists");
    }
    newUser.setUsername(newUserName);
    newUser.setPassword(passwordEncoder.encode(password));
//    newUser.setRole(Role.USER);
//    newUser.setCreate_at(new Date());
//    newUser.setUpdate_at(new Date());
//    newUser.setIs_deleted(false);
    User savedUser = repo.save(newUser);

    return userMapper.convertToDTO(savedUser);
  }

  @Override
  @CachePut(value = "USER_CACHE", key="#result.id()")
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
  @CacheEvict(value = "users", key="#id")
  public boolean deleteUser(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public AuthReponse login(LoginRequest loginRequest) {

    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();

    User user = repo.findByUsername(username);

    if (user == null) {
      throw new RuntimeException("User not found");
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Incorrect password");
    }

    String jwtToken = jwt.generateToken(username);
    UserDTO userDTO = userMapper.convertToDTO(user);

    return new AuthReponse(jwtToken, userDTO);
  }


}
