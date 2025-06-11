package com.example.movie.mapper;


import com.example.movie.dto.SeasonDTO;
import com.example.movie.entity.Season;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {

    public SeasonDTO convertToDTO(Season season) {
      if (season == null) {
        return null;
      }
      return new SeasonDTO(season.getId(),season.getSeasonNumber(), season.getTitle());
    }



}
