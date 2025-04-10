package main_package.entity;

import main_package.response.UserGetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class E2EGetUserTest {
  private static final UserGetResponse testUserResponse =
          new UserGetResponse("genius", 6L);

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetUser() {
    String url = "http://localhost:" + port + "/api/user/1";
    ResponseEntity<UserGetResponse> response = restTemplate.getForEntity(url, UserGetResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(testUserResponse, response.getBody());
  }
}
