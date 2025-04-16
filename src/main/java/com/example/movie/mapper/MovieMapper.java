package com.example.movie.mapper;


import com.example.movie.dto.MovieDTO;
import com.example.movie.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDTO convertToDTO(Movie movie) {
      if (movie == null) {
        return null;
      }
      return new MovieDTO(movie.getMovie_name(), movie.getDescription(),
        movie.getYear(),movie.getRating(),movie.getPost_url(),
        movie.getLength(),movie.getStatus(),movie.getUpdate_at(),
        movie.getCreate_at(),movie.isIs_deleted()
      );
    }



}
