package com.example.movie.mapper;


import com.example.movie.dto.CompanyDTO;
import com.example.movie.dto.CountryDTO;
import com.example.movie.dto.GenreDTO;
import com.example.movie.dto.VideoDTO;
import com.example.movie.entity.Company;
import com.example.movie.entity.Country;
import com.example.movie.entity.Genre;
import com.example.movie.entity.Video;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VideoMapper {

    private final GenreMapper genreMapper;
    private final CompanyMapper companyMapper;
    private final CountryMapper countryMapper;

    public VideoMapper(GenreMapper genreMapper, CompanyMapper companyMapper, CountryMapper countryMapper) {
        this.genreMapper = genreMapper;
        this.companyMapper = companyMapper;
        this.countryMapper = countryMapper;
    }

    public VideoDTO convertToDTO(Video video) {
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
              convertToCompanyDTO(video.getCompanies()),
              convertToCountryDTO(video.getCountries()),
              video.isIs_deleted()
      );
    }

    public Set<GenreDTO> convertToGenreDTO(Set<Genre> genres) {
            return genres.stream().map(genre -> {
                return genreMapper.convertToDTO(genre);
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
