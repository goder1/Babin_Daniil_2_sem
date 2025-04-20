package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.University;
import main_package.entity.UniversityData;
import main_package.repository.UniversityRepository;
import main_package.request.UniversityCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
public class UniversityService {
  private final UniversityRepository universityRepository;

  public UniversityService(UniversityRepository universityRepository) {
    this.universityRepository = universityRepository;
  }

  @Transactional
  public Long addUniversity(UniversityCreateRequest request) {
    log.info("Creating new University with name: {}, location: {}", request.name(), request.location());
    University university = universityRepository.save(new University(null, request.name(), request.students(), request.location()));
    log.info("Created new University with id: {}", university.getId());
    return university.getId();
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public ArrayList<UniversityData> getAllUniversitiesById(Long id) {
    log.info("Getting Universities with user id: {}", id);
    ArrayList<UniversityData> universities = universityRepository.getAllUniversitiesDataById(id);
    log.info("Found Universities with user id: {}", id);
    return universities;
  }
}
