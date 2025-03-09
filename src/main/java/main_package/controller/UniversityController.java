package main_package.controller;

import main_package.request.UniversityCreateRequest;
import main_package.response.UniversityGetResponse;
import main_package.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/university")
public class UniversityController implements UniversityControllerInterface{
  private final UniversityService universityService;

  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
  }

  @Override
  public ResponseEntity<List<UniversityGetResponse>> getAllUniversitiesByUserId(Long id) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(universityService.getAllUniversitysById(id).stream().map(universityData -> new UniversityGetResponse(universityData.name(), universityData.students(), universityData.location())).collect(Collectors.toList()));
  }

  @Override
  public ResponseEntity<List<UniversityGetResponse>> addUniversityToUserById(Long id, UniversityCreateRequest university) {
    universityService.addUniversity(university);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
