package com.example.movie.mapper;


import com.example.movie.dto.UserDTO;
import com.example.movie.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO convertToDTO(User user) {
      if (user == null) {
        return null;
      }
      return new UserDTO(user.getUsername(), user.getUpdate_at(), user.getCreate_at(), user.isIs_deleted());
    }



}
