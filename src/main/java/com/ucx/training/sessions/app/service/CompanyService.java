package com.ucx.training.sessions.app.service;

import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Integer create(Company company){
        Company savedCompany = companyRepository.save(company);
        return savedCompany.getId();
    }

}