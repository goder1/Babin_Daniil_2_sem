package main_package.service;

import lombok.extern.slf4j.Slf4j;
import main_package.entity.UniversityData;
import main_package.repository.UniversityRepository;
import main_package.request.UniversityCreateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class UniversityService {
  private final UniversityRepository universityRepository;

  public UniversityService(UniversityRepository universityRepository) {
    this.universityRepository = universityRepository;
  }

  public Long addUniversity(UniversityCreateRequest request) {
    log.info("Creating new University with name: {}, location: {}", request.name(), request.location());
    Long universityId = universityRepository.createUniversity(new UniversityData(request.name(), request.students(), request.location()));
    log.info("Created new University with id: {}", universityId);
    return universityId;
  }

  public ArrayList<UniversityData> getAllUniversitysById(Long id) {
    log.info("Getting Universities with user id: {}", id);
    ArrayList<UniversityData> universities = universityRepository.getAllUniversitiesDataById(id);
    log.info("Found Universities with user id: {}", id);
    return universities;
  }
}
