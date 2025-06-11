package com.example.movie.service;

import com.example.movie.dto.MovieDTO;
import com.example.movie.entity.Movie;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface MovieService {

  List<MovieDTO> getAll();

  MovieDTO findMovieById(int id);

  MovieDTO createMovie(Movie newMovie, MultipartFile file);

  MovieDTO updateMovie(int movieId, MovieDTO movieDTO);

  boolean deleteMovie(int id);

}
