package com.example.movie.dto;

import java.util.Date;

public class MovieDTO {
  private String movie_name;
  private String description;
  private String year;
  private int rating;
  private String post_url;
  private int length;
  private String status;
  private Date update_at;
  private Date create_at;
  private boolean is_deleted;


  public MovieDTO(String movie_name, String description, String year, int rating, String post_url, int length, String status, Date update_at, Date create_at, boolean is_deleted) {
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


  public String getMovie_name() {
    return movie_name;
  }

  public void setMovie_name(String movie_name) {
    this.movie_name = movie_name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getPost_url() {
    return post_url;
  }

  public void setPost_url(String post_url) {
    this.post_url = post_url;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
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

  public boolean isIs_deleted() {
    return is_deleted;
  }

  public void setIs_deleted(boolean is_deleted) {
    this.is_deleted = is_deleted;
  }
}


