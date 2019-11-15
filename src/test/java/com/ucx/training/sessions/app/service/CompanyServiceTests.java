package com.ucx.training.sessions.app.service;

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

/* If you are using JUnit 4, add @RunWith(SpringRunner.class), otherwise annotations will be ignored
*
*/
@RunWith(SpringRunner.class)
/* With the @SpringBootTest annotation, Spring Boot provides a convenient way to start up an application context
* to be used in a test.
* By default, @SpringBootTest will not start a server. You can use the webEnvironment attribute of @SpringBootTest to further refine how your tests run
* RANDOM_PORT: Loads a WebServerApplicationContext and provides a real web environment. Embedded servers are started and listen on a random port.
*/
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")

public class CompanyServiceTests {
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
