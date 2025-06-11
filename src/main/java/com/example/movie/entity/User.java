package com.example.movie.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


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

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  @Column(name = "update_at")
  private Date update_at;

  @Column(name = "create_at")
  private Date create_at;

  @ManyToMany(mappedBy = "likedByUsers")
  private Set<Video> likedVideos;


  @Column(name = "is_deleted")
  @NotNull(message = "is_deleted can not be null")
  private boolean is_deleted;

//  @OneToMany(cascade = CascadeType.ALL)
//  @JoinColumn(name = "movie_id")
//  private List<Movie> watchedMovies = new ArrayList<>();

  @PrePersist
  private void onCreate() {
    if (this.create_at == null) this.create_at = new Date();
    if (this.update_at == null) this.update_at = new Date();
    if (this.role == null) this.role = Role.ADMIN;
    this.is_deleted = false;
  }

  public User() {
  }

  public User(int id, String username, String password, Role role, Date update_at, Date create_at) {
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
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

  public boolean isIs_deleted() {
    return is_deleted;
  }

  public void setIs_deleted(@NotNull(message = "is_deleted can not be null") boolean is_deleted) {
    this.is_deleted = is_deleted;
  }
}
