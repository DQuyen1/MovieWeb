package com.example.movie.mapper;


import com.example.movie.dto.EpisodeDTO;
import com.example.movie.dto.VideoDTO;
import com.example.movie.entity.Episode;
import com.example.movie.entity.Video;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper {

    public EpisodeDTO convertToDTO(Episode episode) {
      if (episode == null) {
        return null;
      }
      return new EpisodeDTO(episode.getId(),episode.getEpisodeNumber(), episode.getTitle(),
              episode.getDescription(),episode.getReleaseDate(),episode.getDurationMinutes(),
              episode.getVideoUrl());
    }



}
