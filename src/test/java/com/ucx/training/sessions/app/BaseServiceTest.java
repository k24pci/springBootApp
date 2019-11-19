package com.ucx.training.sessions.app;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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
public abstract class BaseServiceTest {
}
