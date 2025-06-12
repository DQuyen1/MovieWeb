package com.example.movie.dto;

import java.util.Date;

public class CountryDTO {
  private String country_name;
  private Date update_at;
  private Date create_at;
  private boolean is_deleted;


  public CountryDTO() {
  }

  public CountryDTO(String country_name, Date update_at, Date create_at, boolean is_deleted) {
    this.country_name = country_name;
    this.update_at = update_at;
    this.create_at = create_at;
    this.is_deleted = is_deleted;
  }

  public String getCountry_name() {
    return country_name;
  }

  public void setCountry_name(String country_name) {
    this.country_name = country_name;
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
