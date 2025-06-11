package com.example.movie.serviceImpl;


import com.example.movie.dto.SeasonDTO;
import com.example.movie.entity.Season;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.SeasonMapper;
import com.example.movie.repository.SeasonRepository;
import com.example.movie.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {

  final private SeasonMapper seasonMapper;
  final private SeasonRepository repo;



  @Autowired
  public SeasonServiceImpl(SeasonRepository repo, SeasonMapper seasonMapper) {
    this.repo = repo;
    this.seasonMapper = seasonMapper;

  }


  @Override
  public List<SeasonDTO> getAll() {
    List<Season> seasons = repo.findAll();
    return seasons.stream().map(seasonMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
  @Cacheable(value = "SEASON_CACHE", key="#id")
  public SeasonDTO findSeasonById(int id) {
    Season season = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Season not found");
    });;

    return seasonMapper.convertToDTO(season);

  }

  @Override
  public SeasonDTO createSeason(Season newSeason) {
    Season season  = repo.save(newSeason);
    return seasonMapper.convertToDTO(season);
  }

  @Override
  @CachePut(value = "SEASON_CACHE", key="#result.id()")
  public SeasonDTO updateSeason(int seasonId, SeasonDTO seasonDTO) {
    Season season = repo.findById(seasonId).orElseThrow(() -> {
      return new ResourceNotFoundException("this season is not exist");
    });

    season.setSeasonNumber(seasonDTO.getSeasonNumber());
    season.setTitle(seasonDTO.getTitle());

    Season updatedSeason = repo.save(season);

    return seasonMapper.convertToDTO(updatedSeason);
  }

  @Override
  @CacheEvict(value = "seasons", key="#id")
  public boolean deleteSeason(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
