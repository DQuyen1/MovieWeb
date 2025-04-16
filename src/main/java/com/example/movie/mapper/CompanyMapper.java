package com.example.movie.mapper;


import com.example.movie.dto.CompanyDTO;
import com.example.movie.dto.CountryDTO;
import com.example.movie.dto.UserDTO;
import com.example.movie.entity.Company;
import com.example.movie.entity.Country;
import com.example.movie.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDTO convertToDTO(Company company) {
      if (company == null) {
        return null;
      }
      return new CompanyDTO(company.getCompany_name(), company.getUpdate_at(), company.getCreate_at(), company.isIs_deleted());
    }



}
