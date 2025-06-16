package com.example.movie.entity;

import com.example.movie.listener.VideoEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@Entity
@EntityListeners(VideoEntityListener.class)
@Table(name = "\"videos\"", indexes = { @Index(name = "idx_video_name", columnList = "video_name")})

public class Video {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "video_id")
  private int id;

  @Column(name = "video_name")
  @NotNull(message = "Video's name can not be null")
  @NotBlank(message = "Video's name can not be empty string")
  private String videoName;

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
  private String filePath;

  @Column(name = "contentType")
  private String contentType;

  @Column(name = "is_trending")
  private boolean isTrending;

  @Column(name = "is_top")
  private boolean isTop;

  @Column(name = "galleryImages")
  private List<String> galleryImages;

  @Column(name = "is_deleted")
  @NotNull(message = "is_deleted can not be null")
  private boolean is_deleted;


  @ManyToOne
  @JoinColumn(name = "language_id")
  private Language language;

  @ManyToMany(
          cascade = {CascadeType.ALL},
          fetch = FetchType.LAZY
  )
  @JoinTable(
          name = "video_genre",
          joinColumns = @JoinColumn(name = "video_id"),
          inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private List<Genre> genres;


//  @ManyToMany
//  @JoinTable(
//          name = "video_category",
//          joinColumns = @JoinColumn(name = "video_id"),
//          inverseJoinColumns = @JoinColumn(name = "category_id")
//  )
@ElementCollection
@Column(name = "categories")

  private List<String> categories;


  @ManyToMany
  @JoinTable(
          name = "video_company",
          joinColumns = @JoinColumn(name = "video_id"),
          inverseJoinColumns = @JoinColumn(name = "company_id")
  )
  private Set<Company> companies;

  @ManyToMany
  @JoinTable(
          name = "video_country",
          joinColumns = @JoinColumn(name = "video_id"),
          inverseJoinColumns = @JoinColumn(name = "country_id")
  )
  private Set<Country> countries;


  @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Season> seasons;


  @ManyToMany
  @JoinTable(
          name = "user_video_like",
          joinColumns = @JoinColumn(name = "video_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> likedByUsers;




  public Video() {
  }


  public Video(boolean isTrending, int id, String videoName, String description, String year, int rating, String post_url,
               int length, String status, Date update_at, Date create_at, String filePath, String contentType, boolean isTop,
               List<String> galleryImages, boolean is_deleted, Language language, List<Genre> genres, List<String> categories, Set<Company> companies, Set<Country> countries, List<Season> seasons, Set<User> likedByUsers) {
    this.isTrending = isTrending;
    this.id = id;
    this.videoName = videoName;
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
    this.isTop = isTop;
    this.galleryImages = galleryImages;
    this.is_deleted = is_deleted;
    this.language = language;
    this.genres = genres;
    this.categories = categories;
    this.companies = companies;
    this.countries = countries;
    this.seasons = seasons;
    this.likedByUsers = likedByUsers;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public @NotNull(message = "Video's name can not be null") @NotBlank(message = "Video's name can not be empty string") String getVideoName() {
    return videoName;
  }

  public void setVideoName(@NotNull(message = "Video's name can not be null") @NotBlank(message = "Video's name can not be empty string") String videoName) {
    this.videoName = videoName;
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

  public boolean isTrending() {
    return isTrending;
  }

  public void setTrending(boolean trending) {
    isTrending = trending;
  }

  public boolean isTop() {
    return isTop;
  }

  public void setTop(boolean top) {
    isTop = top;
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

  public List<String> getGalleryImages() {
    return galleryImages;
  }

  public void setGalleryImages(List<String> galleryImages) {
    this.galleryImages = galleryImages;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public Set<Company> getCompanies() {
    return companies;
  }

  public void setCompanies(Set<Company> companies) {
    this.companies = companies;
  }

  public Set<Country> getCountries() {
    return countries;
  }

  public void setCountries(Set<Country> countries) {
    this.countries = countries;
  }

  public List<Season> getSeasons() {
    return seasons;
  }

  public void setSeasons(List<Season> seasons) {
    this.seasons = seasons;
  }

  public Set<User> getLikedByUsers() {
    return likedByUsers;
  }

  public void setLikedByUsers(Set<User> likedByUsers) {
    this.likedByUsers = likedByUsers;
  }
}
