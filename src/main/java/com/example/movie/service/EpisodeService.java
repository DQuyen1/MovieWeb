package com.example.movie.service;

import com.example.movie.dto.EpisodeDTO;
import com.example.movie.entity.Episode;


import java.util.List;

public interface EpisodeService {

  List<EpisodeDTO> getAll();

  EpisodeDTO findEpisodeById(int id);

  EpisodeDTO createEpisode(Episode newEpisode);

  EpisodeDTO updateEpisode(int episodeId, EpisodeDTO episodeDTO);

  boolean deleteEpisode(int id);

}
