package com.example.movie.controller;


import com.example.movie.dto.EpisodeDTO;
import com.example.movie.entity.Episode;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.serviceImpl.EpisodeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/episodes")
public class EpisodeController {

  EpisodeServiceImpl episodeService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public EpisodeController(EpisodeServiceImpl episodeService) {
    this.episodeService = episodeService;
  }

  @GetMapping
  public ResponseEntity<ResponseMessage> getAllEpisode() {
    return new ResponseEntity<>(new ResponseMessage("Episodes retrieved successfully", episodeService.getAll()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getEpisode(@PathVariable int id) {
    EpisodeDTO episodeDTO = episodeService.findEpisodeById(id);
    return new ResponseEntity<>(new ResponseMessage("Episode retrieved successfully", episodeDTO), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createEpisode(@Valid @RequestBody Episode episode) {

    return new ResponseEntity<>(new ResponseMessage("Created new episode successfully", episodeService.createEpisode(episode)), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateEpisode(@Valid @RequestBody EpisodeDTO episodeDTO, @PathVariable int id) {
    EpisodeDTO updatedEpisode = episodeService.updateEpisode(id, episodeDTO);
    return new ResponseEntity<>(new ResponseMessage("Update episode successfully", updatedEpisode), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteEpisode(@PathVariable int id) {

    boolean isDeleted = episodeService.deleteEpisode(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("Episode with id #" + id
          + " deleted successfully", null),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("Episode not found", null),
        HttpStatus.NOT_FOUND
      );
    }
  }




}
