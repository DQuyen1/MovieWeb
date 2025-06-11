package com.example.movie.controller;


import com.example.movie.dto.CompanyDTO;
import com.example.movie.dto.UserDTO;
import com.example.movie.entity.Company;
import com.example.movie.entity.ResponseMessage;
import com.example.movie.entity.User;
import com.example.movie.serviceImpl.CompanyServiceImpl;
import com.example.movie.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/companies")
public class CompanyController {

  CompanyServiceImpl companyService;

  //if only has 1 constructor this annotation autowired is optional
  @Autowired
  public CompanyController(CompanyServiceImpl companyService) {

    this.companyService = companyService;
  }

  @GetMapping
  public ResponseEntity<ResponseMessage> getAllCompany() {
    return new ResponseEntity<>(new ResponseMessage("Companies retrieved successfully", companyService.getAll()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseMessage> getcompany(@PathVariable int id) {
    CompanyDTO companyDTO = companyService.findCompanyById(id);
    return new ResponseEntity<>(new ResponseMessage("Company retrieved successfully", companyDTO), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<ResponseMessage> createCompany(@Valid @RequestBody Company company) {

    return new ResponseEntity<>(new ResponseMessage("Created new company successfully", companyService.createCompany(company)), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateCompany(@Valid @RequestBody CompanyDTO companyDTO, @PathVariable int id) {

    CompanyDTO updatedCompany = companyService.updateCompany(id, companyDTO);

    return new ResponseEntity<>(new ResponseMessage("Update company successfully", updatedCompany), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteCompany(@PathVariable int id) {

    boolean isDeleted = companyService.deleteCompany(id);

    if (isDeleted) {
      return new ResponseEntity<>(
        new ResponseMessage("Company with id #" + id
          + " deleted successfully", null),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        new ResponseMessage("Company not found", null),
        HttpStatus.NOT_FOUND
      );
    }
  }




}
