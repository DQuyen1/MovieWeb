package com.example.movie.dto;

import java.util.Date;

public class GenreDTO {

  private String name;
  private Date update_at;
  private Date create_at;
  private boolean is_deleted;

  public GenreDTO() {
  }

  public GenreDTO(String name, Date update_at, Date create_at, boolean is_deleted) {
    this.name = name;
    this.update_at = update_at;
    this.create_at = create_at;
    this.is_deleted = is_deleted;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
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

  public boolean isIs_deleted() {
    return is_deleted;
  }

  public void setIs_deleted(boolean is_deleted) {
    this.is_deleted = is_deleted;
  }
}
