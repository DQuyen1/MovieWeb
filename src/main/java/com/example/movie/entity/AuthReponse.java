package com.example.movie.entity;

import com.example.movie.dto.UserDTO;

public class AuthReponse {

  private String jwt;
  private UserDTO user;

  public AuthReponse(String jwt, UserDTO user) {
    this.jwt = jwt;
    this.user = user;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }
}
