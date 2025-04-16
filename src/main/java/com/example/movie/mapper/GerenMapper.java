package com.example.movie.mapper;



import com.example.movie.dto.GenreDTO;
import com.example.movie.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GerenMapper {

    public GenreDTO convertToDTO(Genre genre) {
      if (genre == null) {
        return null;
      }
      return new GenreDTO(genre.getName(), genre.getUpdate_at(), genre.getCreate_at(), genre.isIs_deleted());
    }



}
