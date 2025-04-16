package com.example.movie.serviceImpl;


import com.example.movie.dto.LanguageDTO;
import com.example.movie.entity.Language;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.LanguageMapper;
import com.example.movie.repository.LanguageRepository;
import com.example.movie.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

  final private LanguageMapper languageMapper;
  final private LanguageRepository repo;

  @Autowired
  public LanguageServiceImpl(LanguageRepository repo, LanguageMapper languageMapper) {
    this.repo = repo;
    this.languageMapper = languageMapper;
  }


  @Override
  public List<LanguageDTO> getAll() {
    List<Language> languages = repo.findAll();
    return languages.stream().map(languageMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
  public LanguageDTO findLanguageById(int id) {
    Language language = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Language not found");
    });;

    return languageMapper.convertToDTO(language);

  }

  @Override
  public LanguageDTO createLanguage(Language newLanguage) {
    Language language  = repo.save(newLanguage);
    return languageMapper.convertToDTO(language);
  }

  @Override
  public LanguageDTO updateLanguage(int languageId, LanguageDTO languageDTO) {
    Language language = repo.findById(languageId).orElseThrow(() -> {
      return new ResourceNotFoundException("this language is not exist");
    });

    language.setLanguage_name(languageDTO.getLanguage_name());
    language.setUpdate_at(languageDTO.getUpdate_at());
    language.setCreate_at(languageDTO.getCreate_at());
    language.setIs_deleted(languageDTO.isIs_deleted());

    Language updatedLanguage = repo.save(language);

    return languageMapper.convertToDTO(updatedLanguage);
  }

  @Override
  public boolean deleteLanguage(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
