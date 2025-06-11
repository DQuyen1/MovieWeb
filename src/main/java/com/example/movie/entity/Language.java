package com.example.movie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"languages\"")
public class Language {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "language_id")
  private int id;

  @Column(name = "language_name")
  @NotNull(message = "language_name can not be null")
  @NotBlank(message = "language_name can not be empty string")
  private String language_name;

  @Column(name = "update_at")
  private Date update_at;

  @Column(name = "create_at")
  private Date create_at;

  @OneToMany(mappedBy = "language")
  private List<Video> videos;

  @Column(name = "is_deleted")
  @NotNull(message = "is_deleted can not be null")
  private boolean is_deleted;

  @PrePersist
  private void onCreate() {
    if (this.create_at == null) this.create_at = new Date();
    if (this.update_at == null) this.update_at = new Date();
    this.is_deleted = false;
  }

  public Language() {
  }

  public Language(int id, String language_name, Date update_at, Date create_at, boolean is_deleted) {
    this.id = id;
    this.language_name = language_name;
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

  public @NotNull(message = "language_name can not be null") @NotBlank(message = "language_name can not be empty string") String getLanguage_name() {
    return language_name;
  }

  public void setLanguage_name(@NotNull(message = "language_name can not be null") @NotBlank(message = "language_name can not be empty string") String language_name) {
    this.language_name = language_name;
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

  public void setIs_deleted(@NotNull(message = "is_deleted can not be null")  boolean is_deleted) {
    this.is_deleted = is_deleted;
  }
}
