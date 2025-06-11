package com.example.movie.controller;


import com.example.movie.dto.SeasonDTO;
import com.example.movie.entity.Season;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.serviceImpl.SeasonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/seasons")
public class SeasonController {

  SeasonServiceImpl seasonService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public SeasonController(SeasonServiceImpl seasonService) {
    this.seasonService = seasonService;
  }

  @GetMapping
  public ResponseEntity<ResponseMessage> getAllSeason() {
    return new ResponseEntity<>(new ResponseMessage("Seasons retrieved successfully", seasonService.getAll()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getSeason(@PathVariable int id) {
    SeasonDTO seasonDTO = seasonService.findSeasonById(id);
    return new ResponseEntity<>(new ResponseMessage("Season retrieved successfully", seasonDTO), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createSeason(@Valid @RequestBody Season season) {

    return new ResponseEntity<>(new ResponseMessage("Created new season successfully", seasonService.createSeason(season)), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateSeason(@Valid @RequestBody SeasonDTO seasonDTO, @PathVariable int id) {
    SeasonDTO updatedSeason = seasonService.updateSeason(id, seasonDTO);
    return new ResponseEntity<>(new ResponseMessage("Update season successfully", updatedSeason), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteSeason(@PathVariable int id) {

    boolean isDeleted = seasonService.deleteSeason(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("Season with id #" + id
          + " deleted successfully", null),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("Season not found", null),
        HttpStatus.NOT_FOUND
      );
    }
  }




}
