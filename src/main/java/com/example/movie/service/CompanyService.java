package com.example.movie.service;

import com.example.movie.dto.CompanyDTO;
import com.example.movie.entity.Company;


import java.util.List;

public interface CompanyService {

  List<CompanyDTO> getAll();

  CompanyDTO findCompanyById(int id);

  CompanyDTO createCompany(Company newCompany);

  CompanyDTO updateCompany(int companyId, CompanyDTO companyDTO);

  boolean deleteCompany(int id);

}
