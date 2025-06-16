package com.example.movie.dto;

import com.example.movie.entity.Language;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class VideoDTO {
  private int id;
  private String videoName;
  private String description;
  private String year;
  private int rating;
  private String post_url;
  private int length;
  private String status;
  private Date update_at;
  private Date create_at;
  private String filePath;
  private String contentType;
  private List<String> galleryImages;
  private boolean isTrending;
  private boolean isTop;
  private Language language;
  private List<GenreDTO> genres;
  private List<String> categories;
  private Set<CompanyDTO> companies;
  private Set<CountryDTO> countries;
  private boolean is_deleted;


  public VideoDTO() {
  }

  public VideoDTO(int id, String videoName, String description, String year, int rating, String post_url, int length,
                  String status, Date update_at, Date create_at, String filePath, String contentType, List<String> galleryImages,
                  boolean isTrending, boolean isTop, Language language, List<GenreDTO> genres, List<String> categories, Set<CompanyDTO> companies, Set<CountryDTO> countries, boolean is_deleted) {
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
    this.galleryImages = galleryImages;
    this.isTrending = isTrending;
    this.isTop = isTop;
    this.language = language;
    this.genres = genres;
    this.categories = categories;
    this.companies = companies;
    this.countries = countries;
    this.is_deleted = is_deleted;
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

  public String getVideoName() {
    return videoName;
  }

  public void setVideoName(String videoName) {
    this.videoName = videoName;
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

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
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

  public boolean isIs_deleted() {
    return is_deleted;
  }

  public void setIs_deleted(boolean is_deleted) {
    this.is_deleted = is_deleted;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
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

  public List<GenreDTO> getGenres() {
    return genres;
  }

  public void setGenres(List<GenreDTO> genres) {
    this.genres = genres;
 }

  public Set<CompanyDTO> getCompanies() {
    return companies;
  }

  public void setCompanies(Set<CompanyDTO> companies) {
    this.companies = companies;
  }

  public Set<CountryDTO> getCountries() {
    return countries;
  }

  public void setCountries(Set<CountryDTO> countries) {
    this.countries = countries;
  }
}


