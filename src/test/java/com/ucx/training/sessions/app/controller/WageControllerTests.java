package com.ucx.training.sessions.app.controller;


import com.ucx.training.sessions.app.BaseControllerTest;
import com.ucx.training.sessions.app.MockData;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import com.ucx.training.sessions.app.repository.WageRepository;
import com.ucx.training.sessions.app.service.CompanyService;
import com.ucx.training.sessions.app.service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class WageControllerTests extends BaseControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WageRepository wageRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testCalculateWages(){
        Company company = MockData.getCompanies().get(0);
        Integer companyId = companyService.create(company);
        Employee employee = MockData.getEmployee();
        Company foundCompany = companyService.findById(companyId);
        employeeService.create(employee, foundCompany);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.MULTIPART_FORM_DATA));

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("csvFile", new ClassPathResource("wages.csv"));

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange("/companies/{id}/employees/wages", HttpMethod.POST, httpEntity,Void.class, companyId);

        assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
        removeTestData(companyId);
    }

    private void removeTestData(Integer id){
        wageRepository.deleteAll();
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
    }
}
