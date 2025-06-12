package com.example.movie.serviceImpl;


import com.example.movie.dto.CountryDTO;
import com.example.movie.entity.Country;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.CountryMapper;
import com.example.movie.repository.CountryRepository;
import com.example.movie.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

  final private CountryMapper countryMapper;
  final private CountryRepository repo;

  @Autowired
  public CountryServiceImpl(CountryRepository repo, CountryMapper countryMapper) {
    this.repo = repo;
    this.countryMapper = countryMapper;
  }


  @Override
  @Cacheable("countries")
  public List<CountryDTO> getAll() {
    List<Country> countries = repo.findAll();
    return countries.stream().map(countryMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
  public CountryDTO findCountryById(int id) {
    Country country = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Country not found");
    });;

    return countryMapper.convertToDTO(country);

  }

  @Override
  public CountryDTO createCountry(Country newCountry) {
    Country country  = repo.save(newCountry);
    return countryMapper.convertToDTO(country);
  }

  @Override
  public CountryDTO updateCountry(int countryId, CountryDTO countryDTO) {
    Country country = repo.findById(countryId).orElseThrow(() -> {
      return new ResourceNotFoundException("this country is not exist");
    });

    country.setCountry_name(countryDTO.getCountry_name());
    country.setUpdate_at(countryDTO.getUpdate_at());
    country.setCreate_at(countryDTO.getCreate_at());
    country.setIs_deleted(countryDTO.isIs_deleted());

    Country updatedCountry = repo.save(country);

    return countryMapper.convertToDTO(updatedCountry);
  }

  @Override
  public boolean deleteCountry(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
