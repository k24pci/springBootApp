package com.ucx.training.sessions.app.controller;

import com.ucx.training.sessions.app.BaseControllerTest;
import com.ucx.training.sessions.app.MockData;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;


public class CompanyControllerTests extends BaseControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void testCreateCompany(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Company company = MockData.getCompanies().get(1);
        HttpEntity<Company> entity = new HttpEntity<>(company, httpHeaders);
        Map<String, Integer> result = testRestTemplate.exchange("/companies", HttpMethod.POST, entity, Map.class).getBody();
        assertNotNull(result);
        assertNotNull(result.get("id"));
        removeMockData(result.get("id"));
    }

    private void removeMockData(Integer id){
      Company company = companyRepository.findById(id).get();
      companyRepository.delete(company);
    }
}
