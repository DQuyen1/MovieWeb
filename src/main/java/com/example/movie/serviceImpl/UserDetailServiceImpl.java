package com.example.movie.serviceImpl;

import com.example.movie.entity.CustomUserDetails;
import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import com.example.movie.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository repository;

  public UserDetailServiceImpl(UserRepository repository) {
        this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByUsername(username);
    if (user == null) {
      System.out.println("User Not Found");
      throw new UsernameNotFoundException("user not found");
    }

    return new CustomUserDetails(user);
    }

  }



