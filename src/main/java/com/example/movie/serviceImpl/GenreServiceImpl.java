package com.example.movie.serviceImpl;


import com.example.movie.dto.GenreDTO;
import com.example.movie.entity.Genre;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.GerenMapper;
import com.example.movie.repository.GenreRepository;
import com.example.movie.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

  final private GerenMapper gerenMapper;
  final private GenreRepository repo;

  @Autowired
  public GenreServiceImpl(GenreRepository repo, GerenMapper gerenMapper) {
    this.repo = repo;
    this.gerenMapper = gerenMapper;
  }


  @Override
  public List<GenreDTO> getAll() {
    List<Genre> genres = repo.findAll();
    return genres.stream().map(gerenMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
  public GenreDTO findGerenById(int id) {
    Genre genre = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Geren not found");
    });;

    return gerenMapper.convertToDTO(genre);

  }

  @Override
  public GenreDTO createGeren(Genre newGenre) {
    Genre genre = repo.save(newGenre);
    return gerenMapper.convertToDTO(genre);
  }

  @Override
  public GenreDTO updateGeren(int gerenId, GenreDTO genreDTO) {
    Genre genre = repo.findById(gerenId).orElseThrow(() -> {
      return new ResourceNotFoundException("this geren is not exist");
    });

    genre.setName(genreDTO.getName());
    genre.setUpdate_at(genreDTO.getUpdate_at());
    genre.setCreate_at(genreDTO.getCreate_at());
    genre.setIs_deleted(genreDTO.isIs_deleted());

    Genre updatedGenre = repo.save(genre);

    return gerenMapper.convertToDTO(updatedGenre);
  }

  @Override
  public boolean deleteGeren(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
