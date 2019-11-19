package com.ucx.training.sessions.app.controller;

import com.ucx.training.sessions.app.BaseControllerTest;
import com.ucx.training.sessions.app.MockData;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.repository.AddressRepository;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import com.ucx.training.sessions.app.repository.EmployeeRepository;
import com.ucx.training.sessions.app.service.CompanyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class EmployeeControllerTests extends BaseControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CompanyController companyController;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testCreateEmployee(){
        Company company = MockData.getCompanies().get(0);
        Integer id = companyService.create(company);
        Employee employee = MockData.getEmployee();
        Map<String, Integer> pathVars = new HashMap<>();
        pathVars.put("id", id);
        Map<String, Integer> result = testRestTemplate
                .postForEntity("/companies/{id}/employees", employee, Map.class, pathVars).getBody();
        assertNotNull(result);
        assertNotNull(result.get("id"));
        removeTestData(id);
    }

    private void removeTestData(Integer id){
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
    }
}
