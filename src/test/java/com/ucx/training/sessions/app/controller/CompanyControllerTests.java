package com.ucx.training.sessions.app.controller;

import com.ucx.training.sessions.app.MockData;
import com.ucx.training.sessions.app.entity.Company;
import com.ucx.training.sessions.app.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
public class CompanyControllerTests {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void testCreateCompany(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        Company company = MockData.getCompanies().get(1);
        HttpEntity<Company> entity = new HttpEntity<>(company, httpHeaders);
        Map<String, Integer> result = testRestTemplate.exchange("/companies", HttpMethod.POST, entity, Map.class).getBody();
        assertNotNull(result);
        assertNotNull(result.get("id"));
        removeMockData();
    }

    private void removeMockData(){
        companyRepository.deleteAll();
    }
}
