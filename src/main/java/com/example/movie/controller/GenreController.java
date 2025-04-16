package com.example.movie.controller;

import com.example.movie.dto.GenreDTO;
import com.example.movie.entity.Genre;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.serviceImpl.GenreServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping(path = "/api/v1/genres")
public class GenreController {

  GenreServiceImpl genreService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public GenreController(GenreServiceImpl genreService) {

    this.genreService = genreService;
  }

  @GetMapping
  public ResponseEntity<ResponseMessage> getAllGenre() {
    return new ResponseEntity<>(new ResponseMessage("Genres retrieved successfully", genreService.getAll(), new Date()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getGenre(@PathVariable int id) {
    GenreDTO genreDTO = genreService.findGerenById(id);
    return new ResponseEntity<>(new ResponseMessage("Genre retrieved successfully", genreDTO, new Date()), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createGenre(@Valid @RequestBody Genre genre) {

    return new ResponseEntity<>(new ResponseMessage("Created new genre successfully", genreService.createGeren(genre) ,new Date()), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateGenre(@Valid @RequestBody GenreDTO genreDTO, @PathVariable int id) {

    GenreDTO updatedGenre = genreService.updateGeren(id, genreDTO);

    return new ResponseEntity<>(new ResponseMessage("Update genre successfully", updatedGenre, new Date()), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteGenre(@PathVariable int id) {

    boolean isDeleted = genreService.deleteGeren(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("Genre with id #" + id
          + " deleted successfully", null, new Date()),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("Genre not found", null, new Date()),
        HttpStatus.NOT_FOUND
      );
    }
  }




}
