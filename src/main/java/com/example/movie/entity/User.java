package com.example.movie.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;


@Entity
@Table(name = "\"users\"")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private int id;

  @Column(name = "username")
  @NotNull(message = "username can not be null")
  @NotBlank(message = "username can not be empty string")
  private String username;

  @Column(name = "password")
  @NotNull(message = "password can not be null")
  @NotBlank(message = "password can not be empty string")
  private String password;

  @Column(name = "role")
  @NotNull(message = "role can not be null")
  @NotBlank(message = "role can not be empty string")

  private String role;

  @Column(name = "update_at")
  private Date update_at;

  @Column(name = "create_at")
  private Date create_at;

  @Column(name = "is_deleted")
  @NotNull(message = "is_deleted can not be null")
  @NotBlank(message = "is_deleted can not be empty string")
  private boolean is_deleted;

  public User() {
  }

  public User(int id, String username, String password, String role, Date update_at, Date create_at) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.update_at = update_at;
    this.create_at = create_at;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public @NotNull(message = "username can not be null") @NotBlank(message = "username can not be empty string") String getUsername() {
    return username;
  }

  public void setUsername(@NotNull(message = "username can not be null") @NotBlank(message = "username can not be empty string") String username) {
    this.username = username;
  }

  public @NotNull(message = "password can not be null") @NotBlank(message = "password can not be empty string") String getPassword() {
    return password;
  }

  public void setPassword(@NotNull(message = "password can not be null") @NotBlank(message = "password can not be empty string") String password) {
    this.password = password;
  }

  public @NotNull(message = "role can not be null") @NotBlank(message = "role can not be empty string") String getRole() {
    return role;
  }

  public void setRole(@NotNull(message = "role can not be null") @NotBlank(message = "role can not be empty string") String role) {
    this.role = role;
  }

  public Date getUpdate_at() {
    return update_at;
  }

  public void setUpdate_at(Date update_at) {
    this.update_at = update_at;
  }

  public Date getCreate_at() {
    return create_at;
  }

  public void setCreate_at(Date create_at) {
    this.create_at = create_at;
  }

  @NotNull(message = "is_deleted can not be null")
  @NotBlank(message = "is_deleted can not be empty string")
  public boolean isIs_deleted() {
    return is_deleted;
  }

  public void setIs_deleted(@NotNull(message = "is_deleted can not be null") @NotBlank(message = "is_deleted can not be empty string") boolean is_deleted) {
    this.is_deleted = is_deleted;
  }
}
