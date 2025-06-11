package com.example.movie.controller;


import com.example.movie.dto.LanguageDTO;
import com.example.movie.dto.UserDTO;
import com.example.movie.entity.Language;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.entity.User;
import com.example.movie.serviceImpl.LanguageServiceImpl;
import com.example.movie.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/languages")
public class LanguageController {

  LanguageServiceImpl languageService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public LanguageController(LanguageServiceImpl languageService) {

    this.languageService = languageService;
  }

  @GetMapping
  public ResponseEntity<ResponseMessage> getAllLanguage() {
    return new ResponseEntity<>(new ResponseMessage("Languages retrieved successfully", languageService.getAll()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getLanguage(@PathVariable int id) {
    LanguageDTO languageDTO = languageService.findLanguageById(id);
    return new ResponseEntity<>(new ResponseMessage("Language retrieved successfully", languageDTO), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createLanguage(@Valid @RequestBody Language language) {
    return new ResponseEntity<>(new ResponseMessage("created new language successfully", languageService.createLanguage(language)), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateLanguage(@Valid @RequestBody LanguageDTO languageDTO, @PathVariable int id) {

    LanguageDTO updatedLanguage = languageService.updateLanguage(id, languageDTO);

    return new ResponseEntity<>(new ResponseMessage("Update language successfully", updatedLanguage), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteLanguage(@PathVariable int id) {

    boolean isDeleted = languageService.deleteLanguage(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("Language with id #" + id
          + " deleted successfully", null),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("Language not found", null),
        HttpStatus.NOT_FOUND
      );
    }
  }




}
