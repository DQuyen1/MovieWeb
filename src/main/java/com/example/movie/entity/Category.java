package com.example.movie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private long id;

    @Column(name = "name", unique = true)
    @NotNull(message = "name can not be null")
    @NotBlank(message = "name can not be empty string")
    private String name;

    @Column(name = "update_at")
    private Date update_at;

    @Column(name = "create_at")
    private Date create_at;

//    @ManyToMany(mappedBy = "categories")
//    private Set<Video> videos;

    @Column(name = "is_deleted")
    private boolean is_deleted;

    @PrePersist
    private void onCreate() {
        if (this.create_at == null) this.create_at = new Date();
        if (this.update_at == null) this.update_at = new Date();
        this.is_deleted = false;
    }

    public Category() {
    }

    public Category(long id, String name, Date update_at, Date create_at, Set<Video> videos, boolean is_deleted) {
        this.id = id;
        this.name = name;
        this.update_at = update_at;
        this.create_at = create_at;
//        this.videos = videos;
        this.is_deleted = is_deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

//    public Set<Video> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(Set<Video> videos) {
//        this.videos = videos;
//    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
