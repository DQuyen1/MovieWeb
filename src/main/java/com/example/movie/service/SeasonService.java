package com.example.movie.service;

import com.example.movie.dto.SeasonDTO;
import com.example.movie.entity.Season;

import java.util.List;

public interface SeasonService {

  List<SeasonDTO> getAll();

  SeasonDTO findSeasonById(int id);

  SeasonDTO createSeason(Season newSeason);

  SeasonDTO updateSeason(int seasonId, SeasonDTO seasonDTO);

  boolean deleteSeason(int id);

}
