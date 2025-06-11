package com.example.movie.dto;


public class SeasonDTO {

    private Long id;
    private int seasonNumber;
    private String title;


    public SeasonDTO(Long id, int seasonNumber, String title) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.title = title;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
