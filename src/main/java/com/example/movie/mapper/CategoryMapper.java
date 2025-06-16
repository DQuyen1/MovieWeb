package com.example.movie.mapper;

import com.example.movie.dto.CategoryDTO;
import com.example.movie.dto.LanguageDTO;
import com.example.movie.entity.Category;
import com.example.movie.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO convertToDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getId(),category.getName(),category.getUpdate_at(),category.getCreate_at(),category.isIs_deleted());
    }
}
