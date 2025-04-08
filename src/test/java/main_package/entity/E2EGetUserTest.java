package main_package.entity;

import lombok.extern.slf4j.Slf4j;
import main_package.response.UserGetResponse;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("my_app_test")
class E2EGetUserTest {
  private static final UserGetResponse testUserResponse =
          new UserGetResponse("genius", 6L);

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @ClassRule
  public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13")
      .withDatabaseName("mydb")
      .withUsername("myuser")
      .withPassword("mypass")
      .withInitScript("init.sql");

  @Test
  public void testGetUser() {
    String jdbcUrl = postgresContainer.getJdbcUrl();
    String username = postgresContainer.getUsername();
    String password = postgresContainer.getPassword();

    log.info("postgres: " + jdbcUrl);

    String url = "http://localhost:" + port + "/api/user/1";
    ResponseEntity<UserGetResponse> response = restTemplate.getForEntity(url, UserGetResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(testUserResponse, response.getBody());
  }
}