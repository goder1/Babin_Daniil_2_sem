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
public class UniversityController {
  private final UniversityService universityService;

  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<UniversityGetResponse>> getAllUniversitiesByUserId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(universityService.getAllUniversitysById(id).stream().map(universityData -> new UniversityGetResponse(universityData.name(), universityData.students(), universityData.location())).collect(Collectors.toList()));
  }
  @PutMapping("/user/{id}")
  public ResponseEntity<List<UniversityGetResponse>> addUniversityToUserById(@PathVariable Long id, @RequestBody UniversityCreateRequest university) {
    universityService.addUniversity(university);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
