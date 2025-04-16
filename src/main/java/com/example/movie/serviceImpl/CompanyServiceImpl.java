package com.example.movie.serviceImpl;


import com.example.movie.dto.CompanyDTO;
import com.example.movie.entity.Company;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.mapper.CompanyMapper;
import com.example.movie.repository.CompanyRepository;
import com.example.movie.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

  final private CompanyMapper companyMapper;
  final private CompanyRepository repo;

  @Autowired
  public CompanyServiceImpl(CompanyRepository repo, CompanyMapper companyMapper) {
    this.repo = repo;
    this.companyMapper = companyMapper;
  }


  @Override
  public List<CompanyDTO> getAll() {
    List<Company> companies = repo.findAll();
    return companies.stream().map(companyMapper::convertToDTO).collect(Collectors.toList());

  }

  @Override
  public CompanyDTO findCompanyById(int id) {
    Company company = repo.findById(id).orElseThrow(() -> {
      return new ResourceNotFoundException("Company not found");
    });;

    return companyMapper.convertToDTO(company);

  }

  @Override
  public CompanyDTO createCompany(Company newCompany) {
    Company company  = repo.save(newCompany);
    return companyMapper.convertToDTO(company);
  }

  @Override
  public CompanyDTO updateCompany(int companyId, CompanyDTO companyDTO) {
    Company company = repo.findById(companyId).orElseThrow(() -> {
      return new ResourceNotFoundException("This company is not exist");
    });

    company.setCompany_name(companyDTO.getCompany_name());
    company.setUpdate_at(companyDTO.getUpdate_at());
    company.setCreate_at(companyDTO.getCreate_at());
    company.setIs_deleted(companyDTO.isIs_deleted());

    Company updatedCompany = repo.save(company);

    return companyMapper.convertToDTO(updatedCompany);
  }

  @Override
  public boolean deleteCompany(int id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
