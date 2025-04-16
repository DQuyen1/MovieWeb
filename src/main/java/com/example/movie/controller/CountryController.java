package com.example.movie.controller;


import com.example.movie.dto.CountryDTO;
import com.example.movie.entity.Country;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.serviceImpl.CountryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping(path = "/api/v1/countries")
public class CountryController {

  CountryServiceImpl countryService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public CountryController(CountryServiceImpl countryService) {
    this.countryService = countryService;
  }

  @GetMapping
  public ResponseEntity<ResponseMessage> getAllCountry() {
    return new ResponseEntity<>(new ResponseMessage("Countries retrieved successfully", countryService.getAll(), new Date()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getCountry(@PathVariable int id) {
    CountryDTO countryDTO = countryService.findCountryById(id);
    return new ResponseEntity<>(new ResponseMessage("Country retrieved successfully", countryDTO, new Date()), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createCountry(@Valid @RequestBody Country country) {

    return new ResponseEntity<>(new ResponseMessage("Created new country successfully", countryService.createCountry(country) ,new Date()), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateCountry(@Valid @RequestBody CountryDTO countryDTO, @PathVariable int id) {
    CountryDTO updatedCountry = countryService.updateCountry(id, countryDTO);
    return new ResponseEntity<>(new ResponseMessage("Update country successfully", updatedCountry, new Date()), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteCountry(@PathVariable int id) {

    boolean isDeleted = countryService.deleteCountry(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("Country with id #" + id
          + " deleted successfully", null, new Date()),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("Country not found", null, new Date()),
        HttpStatus.NOT_FOUND
      );
    }
  }




}
