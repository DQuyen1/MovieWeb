package com.example.movie.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "season_number")
    private int seasonNumber;


    @Column(name = "title")
    @NotNull(message = "Season's title can not be null")
    @NotBlank(message = "Season's title can not be empty string")
    private String title;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episode> episodes;

    public Season() {
    }

    public Season(Long id, int seasonNumber, String title) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.title = title;
    }

    // Getters and setters

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
