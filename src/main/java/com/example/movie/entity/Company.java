package com.example.movie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "\"companies\"")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "company_id")
  private int id;

  @Column(name = "company_name")
  @NotNull(message = "company_name can not be null")
  @NotBlank(message = "company_name can not be empty string")
  private String company_name;


  @Column(name = "update_at")
  private Date update_at;

  @Column(name = "create_at")
  private Date create_at;

  @ManyToMany(mappedBy = "companies")
  private Set<Video> videos;

  @Column(name = "is_deleted")
  @NotNull(message = "is_deleted can not be null")
  private boolean is_deleted;

  @PrePersist
  private void onCreate() {
    if (this.create_at == null) this.create_at = new Date();
    if (this.update_at == null) this.update_at = new Date();
    this.is_deleted = false;
  }


  public Company() {
  }

  public Company(int id, String company_name, Date update_at, Date create_at, boolean is_deleted) {
    this.id = id;
    this.company_name = company_name;
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

  public @NotNull(message = "company_name can not be null") @NotBlank(message = "company_name can not be empty string") String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(@NotNull(message = "company_name can not be null") @NotBlank(message = "company_name can not be empty string") String company_name) {
    this.company_name = company_name;
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
