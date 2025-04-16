package com.example.movie.mapper;




import com.example.movie.dto.LanguageDTO;
import com.example.movie.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public LanguageDTO convertToDTO(Language language) {
      if (language == null) {
        return null;
      }
      return new LanguageDTO(language.getLanguage_name(),language.getUpdate_at(),language.getCreate_at(),language.isIs_deleted());
    }



}
