package com.ucx.training.sessions.app.service;

import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * An object annotated with @Component annotation (more specifically @Service) represents the business logic of application
 */
@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Integer create(Company company){
        Company savedCompany = companyRepository.save(company);
        return savedCompany.getId();
    }

    public Company findById(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) throw new RuntimeException("Company can not be found!");
        return optionalCompany.get();
    }

}
