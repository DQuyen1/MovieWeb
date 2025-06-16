package com.example.movie.dto;

import java.util.Date;

public class CategoryDTO {
    private long id;
    private String name;
    private Date update_at;
    private Date create_at;
    private boolean is_deleted;


    public CategoryDTO() {
    }

    public CategoryDTO(long id, String name, Date update_at, Date create_at, boolean is_deleted) {
        this.id = id;
        this.name = name;
        this.update_at = update_at;
        this.create_at = create_at;
        this.is_deleted = is_deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
