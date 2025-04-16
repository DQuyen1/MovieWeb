package com.example.movie.serviceImpl;



import com.example.movie.dto.MovieDTO;
import com.example.movie.entity.Movie;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.MovieMapper;
import com.example.movie.repository.MovieRepository;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

  final private MovieMapper movieMapper;
  final private MovieRepository repo;

  @Autowired
  public MovieServiceImpl(MovieRepository repo, MovieMapper movieMapper) {
    this.repo = repo;
    this.movieMapper = movieMapper;
  }


  @Override
  public List<MovieDTO> getAll() {
    List<Movie> movies = repo.findAll();
    return movies.stream().map(movieMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
  public MovieDTO findMovieById(int id) {
    Movie movie = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Movie not found");
    });;

    return movieMapper.convertToDTO(movie);

  }

  @Override
  public MovieDTO createMovie(Movie newMovie) {
    Movie movie  = repo.save(newMovie);
    return movieMapper.convertToDTO(movie);
  }

  @Override
  public MovieDTO updateMovie(int movieId, MovieDTO movieDTO) {
    Movie movie = repo.findById(movieId).orElseThrow(() -> {
      return new ResourceNotFoundException("This movie is not exist");
    });

    movie.setMovie_name(movieDTO.getMovie_name());
    movie.setDescription(movieDTO.getDescription());
    movie.setYear(movieDTO.getYear());
    movie.setRating(movieDTO.getRating());
    movie.setPost_url(movieDTO.getPost_url());
    movie.setLength(movieDTO.getLength());
    movie.setStatus(movieDTO.getStatus());
    movie.setUpdate_at(movieDTO.getUpdate_at());
    movie.setCreate_at(movieDTO.getCreate_at());
    movie.setIs_deleted(movieDTO.isIs_deleted());

    Movie updatedMovie = repo.save(movie);

    return movieMapper.convertToDTO(updatedMovie);
  }

  @Override
  public boolean deleteMovie(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
