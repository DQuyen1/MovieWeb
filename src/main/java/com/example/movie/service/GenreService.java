package com.example.movie.service;

import com.example.movie.dto.GenreDTO;
import com.example.movie.entity.Genre;

import java.util.List;

public interface GenreService {

  List<GenreDTO> getAll();

  GenreDTO findGerenById(int id);

  GenreDTO createGeren(Genre newGenre);

  GenreDTO updateGeren(int gerenId, GenreDTO genreDTO);

  boolean deleteGeren(int id);

}
