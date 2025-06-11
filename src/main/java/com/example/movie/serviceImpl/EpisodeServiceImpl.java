package com.example.movie.serviceImpl;



import com.example.movie.dto.EpisodeDTO;
import com.example.movie.entity.*;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.EpisodeMapper;
import com.example.movie.repository.EpisodeRepository;
import com.example.movie.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {

  final private EpisodeMapper episodeMapper;
  final private EpisodeRepository repo;



  @Autowired
  public EpisodeServiceImpl(EpisodeRepository repo, EpisodeMapper episodeMapper) {
    this.repo = repo;
    this.episodeMapper = episodeMapper;

  }


  @Override
  public List<EpisodeDTO> getAll() {
    List<Episode> episodes = repo.findAll();
    return episodes.stream().map(episodeMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
  @Cacheable(value = "EPISODE_CACHE", key="#id")
  public EpisodeDTO findEpisodeById(int id) {
    Episode episode = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Episode not found");
    });;

    return episodeMapper.convertToDTO(episode);

  }

  @Override
  public EpisodeDTO createEpisode(Episode newEpisode) {
    Episode episode  = repo.save(newEpisode);
    return episodeMapper.convertToDTO(episode);
  }

  @Override
  @CachePut(value = "EPISODE_CACHE", key="#result.id()")
  public EpisodeDTO updateEpisode(int episodeId, EpisodeDTO episodeDTO) {
    Episode episode = repo.findById(episodeId).orElseThrow(() -> {
      return new ResourceNotFoundException("this episode is not exist");
    });

    episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
    episode.setTitle(episodeDTO.getTitle());
    episode.setDescription(episodeDTO.getDescription());
    episode.setReleaseDate(episodeDTO.getReleaseDate());
    episode.setDurationMinutes(episodeDTO.getDurationMinutes());
    episode.setVideoUrl(episodeDTO.getVideoUrl());

    Episode updatedEpisode = repo.save(episode);

    return episodeMapper.convertToDTO(updatedEpisode);
  }

  @Override
  @CacheEvict(value = "episodes", key="#id")
  public boolean deleteEpisode(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
