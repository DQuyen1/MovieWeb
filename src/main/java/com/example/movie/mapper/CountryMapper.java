package com.example.movie.mapper;


import com.example.movie.dto.CountryDTO;
import com.example.movie.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryDTO convertToDTO(Country country) {
      if (country == null) {
        return null;
      }
      return new CountryDTO(country.getCountry_name(),country.getUpdate_at(),country.getUpdate_at(),country.isIs_deleted());
    }



}
