package com.example.movie.service;

import com.example.movie.dto.CountryDTO;
import com.example.movie.entity.Country;
import com.example.movie.entity.User;

import java.util.List;

public interface CountryService {

  List<CountryDTO> getAll();

  CountryDTO findCountryById(int id);

  CountryDTO createCountry(Country newCountry);

  CountryDTO updateCountry(int countryId, CountryDTO countryDTO);

  boolean deleteCountry(int id);

}
