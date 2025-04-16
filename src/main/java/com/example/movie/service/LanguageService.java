package com.example.movie.service;


import com.example.movie.dto.LanguageDTO;
import com.example.movie.entity.Language;

import java.util.List;

public interface LanguageService {

  List<LanguageDTO> getAll();

  LanguageDTO findLanguageById(int id);

  LanguageDTO createLanguage(Language newLanguage);

  LanguageDTO updateLanguage(int languageId, LanguageDTO languageDTO);

  boolean deleteLanguage(int id);

}
