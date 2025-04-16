package com.example.movie.service;

import com.example.movie.dto.MovieDTO;
import com.example.movie.entity.Movie;


import java.util.List;

public interface MovieService {

  List<MovieDTO> getAll();

  MovieDTO findMovieById(int id);

  MovieDTO createMovie(Movie newMovie);

  MovieDTO updateMovie(int movieId, MovieDTO movieDTO);

  boolean deleteMovie(int id);

}
