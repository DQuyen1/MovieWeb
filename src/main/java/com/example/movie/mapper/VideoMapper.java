package com.example.movie.mapper;


import com.example.movie.dto.*;
import com.example.movie.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VideoMapper {

    private final GenreMapper genreMapper;
    private final CompanyMapper companyMapper;
    private final CountryMapper countryMapper;
    private final CategoryMapper categoryMapper;

    public VideoMapper(GenreMapper genreMapper, CompanyMapper companyMapper, CountryMapper countryMapper, CategoryMapper categoryMapper) {
        this.genreMapper = genreMapper;
        this.companyMapper = companyMapper;
        this.countryMapper = countryMapper;
        this.categoryMapper = categoryMapper;
    }

    public VideoDTO convertToDTO(Video video) {

//        System.out.println(video.getCategories());
      if (video == null) {
        return null;
      }
      return new VideoDTO(video.getId(), video.getVideoName(), video.getDescription(),
              video.getYear(),video.getRating(),video.getPost_url(),
              video.getLength(),video.getStatus(),video.getUpdate_at(),
              video.getCreate_at(),video.getFilePath(), video.getContentType(),
              video.getGalleryImages(),
              video.isTrending(),
              video.isTop(),
              video.getLanguage(),
              convertToGenreDTO(video.getGenres()),
              video.getCategories(),
              convertToCompanyDTO(video.getCompanies()),
              convertToCountryDTO(video.getCountries()),
              video.isIs_deleted()
      );
    }

    public List<GenreDTO> convertToGenreDTO(List<Genre> genres) {
        return genres.stream()
                .map(genre -> genreMapper.convertToDTO(genre))
                .collect(Collectors.toList());
    }


    public Set<CategoryDTO> convertToCategoryDTO(Set<Category> categories) {
        return categories.stream().map(category -> {
            return categoryMapper.convertToDTO(category);
        }).collect(Collectors.toSet());
    }

    public Set<CompanyDTO> convertToCompanyDTO(Set<Company> companies) {
        return companies.stream().map(company -> {
            return companyMapper.convertToDTO(company);
        }).collect(Collectors.toSet());
    }


    public Set<CountryDTO> convertToCountryDTO(Set<Country> countries) {
        return countries.stream().map(country -> {
            return countryMapper.convertToDTO(country);
        }).collect(Collectors.toSet());
    }





}
