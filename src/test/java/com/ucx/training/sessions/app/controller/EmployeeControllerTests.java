package com.ucx.training.sessions.app.controller;

import com.ucx.training.sessions.app.MockData;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.repository.AddressRepository;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import com.ucx.training.sessions.app.repository.EmployeeRepository;
import com.ucx.training.sessions.app.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/* If you are using JUnit 4, add @RunWith(SpringRunner.class), otherwise annotations will be ignored
 *
 */
@RunWith(SpringRunner.class)
/* With the @SpringBootTest annotation, Spring Boot provides a convenient way to start up an application context
 * to be used in a test.
 * By default, @SpringBootTest will not start a server. You can use the webEnvironment attribute of @SpringBootTest to further refine how your tests run
 * RANDOM_PORT: Loads a WebServerApplicationContext and provides a real web environment. Embedded servers are started and listen on a random port.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class EmployeeControllerTests {
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
