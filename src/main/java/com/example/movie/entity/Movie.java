package com.example.movie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "\"Movies\"")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "movie_id")
  private int id;


  @Column(name = "movie_name")
  @NotNull(message = "Movie's name can not be null")
  @NotBlank(message = "Movie's name can not be empty string")
  private String movie_name;

  @Column(name = "description")
  @NotNull(message = "description can not be null")
  @NotBlank(message = "description can not be empty string")
  private String description;

  @Column(name = "year")
  @NotNull(message = "year can not be null")
  @NotBlank(message = "year can not be empty string")
  private String year;

  @Column(name = "rating")
  @NotNull(message = "rating can not be null")
  @NotBlank(message = "rating can not be empty string")
  private int rating;

  @Column(name = "post_url")
  @NotNull(message = "post_url can not be null")
  @NotBlank(message = "post_url can not be empty string")
  private String post_url;

  @Column(name = "length")
  @NotNull(message = "length can not be null")
  @NotBlank(message = "length can not be empty string")
  private int length;

  @Column(name = "status")
  @NotNull(message = "status can not be null")
  @NotBlank(message = "status can not be empty string")
  private String status;

  @Column(name = "update_at")
  private Date update_at;

  @Column(name = "create_at")
  private Date create_at;

  @Column(name = "is_deleted")
  @NotNull(message = "is_deleted can not be null")
  @NotBlank(message = "is_deleted can not be empty string")
  private boolean is_deleted;



  public Movie(int id, String movie_name, String description, String year, int rating, String post_url, int length, String status, Date update_at, Date create_at, boolean is_deleted) {
    this.id = id;
    this.movie_name = movie_name;
    this.description = description;
    this.year = year;
    this.rating = rating;
    this.post_url = post_url;
    this.length = length;
    this.status = status;
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

  public @NotNull(message = "Movie's name can not be null") @NotBlank(message = "Movie's name can not be empty string") String getMovie_name() {
    return movie_name;
  }

  public void setMovie_name(@NotNull(message = "Movie's name can not be null") @NotBlank(message = "Movie's name can not be empty string") String movie_name) {
    this.movie_name = movie_name;
  }

  public @NotNull(message = "description can not be null") @NotBlank(message = "description can not be empty string") String getDescription() {
    return description;
  }

  public void setDescription(@NotNull(message = "description can not be null") @NotBlank(message = "description can not be empty string") String description) {
    this.description = description;
  }

  public @NotNull(message = "year can not be null") @NotBlank(message = "year can not be empty string") String getYear() {
    return year;
  }

  public void setYear(@NotNull(message = "year can not be null") @NotBlank(message = "year can not be empty string") String year) {
    this.year = year;
  }

  @NotNull(message = "rating can not be null")
  @NotBlank(message = "rating can not be empty string")
  public int getRating() {
    return rating;
  }

  public void setRating(@NotNull(message = "rating can not be null") @NotBlank(message = "rating can not be empty string") int rating) {
    this.rating = rating;
  }

  public @NotNull(message = "post_url can not be null") @NotBlank(message = "post_url can not be empty string") String getPost_url() {
    return post_url;
  }

  public void setPost_url(@NotNull(message = "post_url can not be null") @NotBlank(message = "post_url can not be empty string") String post_url) {
    this.post_url = post_url;
  }

  @NotNull(message = "length can not be null")
  @NotBlank(message = "length can not be empty string")
  public int getLength() {
    return length;
  }

  public void setLength(@NotNull(message = "length can not be null") @NotBlank(message = "length can not be empty string") int length) {
    this.length = length;
  }

  public @NotNull(message = "status can not be null") @NotBlank(message = "status can not be empty string") String getStatus() {
    return status;
  }

  public void setStatus(@NotNull(message = "status can not be null") @NotBlank(message = "status can not be empty string") String status) {
    this.status = status;
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
