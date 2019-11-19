package com.ucx.training.sessions.app.service;

import com.ucx.training.sessions.app.BaseServiceTest;
import com.ucx.training.sessions.app.MockData;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



public class CompanyServiceTests extends BaseServiceTest {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testCreateCompany(){
        Company company = MockData.getCompanies().get(1);
        Integer id = companyService.create(company);
        assertNotNull(id);
        Company foundCompany = companyService.findById(id);
        assertEquals(id, foundCompany.getId());
        deleteMockData(id);
    }

    private void deleteMockData(Integer id){
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
    }
}
