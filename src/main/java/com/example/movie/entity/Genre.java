package com.example.movie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
@Entity
@Table(name = "\"genre\"")
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "genre_id")
  private int id;

  @Column(name = "name")
  @NotNull(message = "name can not be null")
  @NotBlank(message = "name can not be empty string")
  private String name;

  @Column(name = "update_at")
  private Date update_at;

  @Column(name = "create_at")
  private Date create_at;

  @Column(name = "is_deleted")
  @NotNull(message = "is_deleted can not be null")
  @NotBlank(message = "is_deleted can not be empty string")
  private boolean is_deleted;



  public Genre(int id, String name, Date update_at, Date create_at, boolean is_deleted) {
    this.id = id;
    this.name = name;
    this.update_at = update_at;
    this.create_at = create_at;
    this.is_deleted = is_deleted;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public @NotNull(message = "name can not be null") @NotBlank(message = "name can not be empty string") String getName() {
    return name;
  }

  public void setName(@NotNull(message = "name can not be null") @NotBlank(message = "name can not be empty string") String name) {
    this.name = name;
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
