package com.ucx.training.sessions.app.controller;


import com.ucx.training.sessions.app.MockData;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.entity.Employee;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import com.ucx.training.sessions.app.repository.WageRepository;
import com.ucx.training.sessions.app.service.CompanyService;
import com.ucx.training.sessions.app.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


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
public class WageControllerTests {
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

        ResponseEntity responseEntity = testRestTemplate
                .exchange("/companies/{id}/employees/wages", HttpMethod.POST, httpEntity,ResponseEntity.class, companyId).getBody();

        assertThat(responseEntity.getStatusCode(),is(HttpStatus.OK));
        removeTestData(companyId);
    }

    private void removeTestData(Integer id){
        wageRepository.deleteAll();
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
    }
}
