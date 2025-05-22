package main_package.repository;

import jakarta.transaction.Transactional;
import main_package.entity.University;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static jakarta.transaction.Transactional.TxType.NOT_SUPPORTED;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(value = NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("my_app_test")
public class UniversityRepositoryTest {

  @Container
  @ServiceConnection
  public static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres");

  @Autowired
  UniversityRepository universityRepository;

  @Test
  void addUniversityTest() {
    University university = universityRepository.save(new University("UserName", 2L, "dolgoprudniy"));
    University responseUniversity = universityRepository.findById(university.getUniversityId()).orElseThrow();

    assertEquals(university, responseUniversity);
  }

  @Test
  void getAllUniversitiesByIdTest() {
    University university = universityRepository.save(new University("UserName", 2L, "dolgoprudniy"));
    Optional<University> responseUniversity = universityRepository.findById(university.getUniversityId());

    assertTrue(responseUniversity.isPresent());
  }
}