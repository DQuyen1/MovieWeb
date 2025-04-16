package com.example.movie.controller;


import com.example.movie.dto.MovieDTO;
import com.example.movie.entity.Movie;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.serviceImpl.MovieServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {

  MovieServiceImpl movieService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public MovieController(MovieServiceImpl movieService) {

    this.movieService = movieService;
  }

  @GetMapping
  public ResponseEntity<ResponseMessage> getAllMovie() {
    return new ResponseEntity<>(new ResponseMessage("Movies retrieved successfully", movieService.getAll(), new Date()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getMovie(@PathVariable int id) {
    MovieDTO movieDTO = movieService.findMovieById(id);
    return new ResponseEntity<>(new ResponseMessage("Movie retrieved successfully", movieDTO, new Date()), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createMovie(@Valid @RequestBody Movie movie) {

    return new ResponseEntity<>(new ResponseMessage("created new movie successfully", movieService.createMovie(movie) ,new Date()), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateMovie(@Valid @RequestBody MovieDTO movieDTO, @PathVariable int id) {

    MovieDTO updatedMovie = movieService.updateMovie(id, movieDTO);

    return new ResponseEntity<>(new ResponseMessage("Update movie successfully", updatedMovie, new Date()), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteMovie(@PathVariable int id) {

    boolean isDeleted = movieService.deleteMovie(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("Movie with id #" + id
          + " deleted successfully", null, new Date()),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("Movie not found", null, new Date()),
        HttpStatus.NOT_FOUND
      );
    }
  }




}
