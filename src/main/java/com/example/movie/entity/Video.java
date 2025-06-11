package com.example.video.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@Entity
@Table(name = "\"Videos\"")
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "video_id")
  private int id;

  @Column(name = "video_name")
  @NotNull(message = "Video's name can not be null")
  @NotBlank(message = "Video's name can not be empty string")
  private String video_name;

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
  private int rating;

  @Column(name = "post_url")
  @NotNull(message = "post_url can not be null")
  @NotBlank(message = "post_url can not be empty string")
  private String post_url;

  @Column(name = "length")
  @NotNull(message = "length can not be null")
  private int length;

  @Column(name = "status")
  @NotNull(message = "status can not be null")
  @NotBlank(message = "status can not be empty string")
  private String status;

  @Column(name = "update_at")
  private Date update_at;

  @Column(name = "create_at")
  private Date create_at;



  @Column(name = "filePath")
//  @NotNull(message = "filePath can not be null")
//  @NotBlank(message = "status can not be empty string")
  private String filePath;

  @Column(name = "contentType")
  private String contentType;


  @Column(name = "is_deleted")
  @NotNull(message = "is_deleted can not be null")
  private boolean is_deleted;

//  @ManyToMany(cascade = CascadeType.ALL)
//  @JoinTable(
//    name = "video_genres",
//    joinColumns = @JoinColumn(name = "video_id"),
//    inverseJoinColumns = @JoinColumn(name = "genre_id")
//  )
//  private Set<Genre> videoGenres = new HashSet<>();
//
//  @ManyToMany(cascade = CascadeType.ALL)
//  @JoinTable(
//    name = "video_languages",
//    joinColumns = @JoinColumn(name = "video_id"),
//    inverseJoinColumns = @JoinColumn(name = "language_id")
//  )
//  private Set<Language> videoLanguages = new HashSet<>();
//
//  @ManyToMany(cascade = CascadeType.ALL)
//  @JoinTable(
//    name = "video_countries",
//    joinColumns = @JoinColumn(name = "video_id"),
//    inverseJoinColumns = @JoinColumn(name = "country_id")
//  )
//  private Set<Country> videoCountries = new HashSet<>();
//
//  @ManyToMany(cascade = CascadeType.ALL)
//  @JoinTable(
//    name = "video_companies",
//    joinColumns = @JoinColumn(name = "video_id"),
//    inverseJoinColumns = @JoinColumn(name = "company_id")
//  )
//  private Set<Company> videoCompanies = new HashSet<>();


  public Video() {
  }

  public Video(int id, String video_name, String description, String year, int rating, String post_url, int length, String status, Date update_at, Date create_at, String filePath, String contentType, boolean is_deleted) {
    this.id = id;
    this.video_name = video_name;
    this.description = description;
    this.year = year;
    this.rating = rating;
    this.post_url = post_url;
    this.length = length;
    this.status = status;
    this.update_at = update_at;
    this.create_at = create_at;
    this.filePath = filePath;
    this.contentType = contentType;
    this.is_deleted = is_deleted;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public @NotNull(message = "Video's name can not be null") @NotBlank(message = "Video's name can not be empty string") String getVideo_name() {
    return video_name;
  }

  public void setVideo_name(@NotNull(message = "Video's name can not be null") @NotBlank(message = "Video's name can not be empty string") String video_name) {
    this.video_name = video_name;
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
  public int getLength() {
    return length;
  }

  public void setLength(@NotNull(message = "length can not be null") int length) {
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

  public boolean isIs_deleted() {
    return is_deleted;
  }

  public void setIs_deleted(@NotNull(message = "is_deleted can not be null") boolean is_deleted) {
    this.is_deleted = is_deleted;
  }


  public
  //@NotNull(message = "filePath can not be null") @NotBlank(message = "status can not be empty string")
  String getFilePath() {
    return filePath;
  }

  public void setFilePath(
          //@NotNull(message = "filePath can not be null") @NotBlank(message = "status can not be empty string")
          String filePath) {
    this.filePath = filePath;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
}
