package com.example.movie.listener;


import com.example.movie.entity.Video;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@Component
public class VideoEntityListener {

    @PrePersist
    public void prePersist(Video video) {
        video.setCreate_at(new Date());
        video.setUpdate_at(new Date());
        video.setIs_deleted(false);

        // Set empty lists/sets if null
        if (video.getLikedByUsers() == null) {
            video.setLikedByUsers(new HashSet<>());
        }

        if (video.getSeasons() == null) {
            video.setSeasons(new ArrayList<>());
        }
    }

}
