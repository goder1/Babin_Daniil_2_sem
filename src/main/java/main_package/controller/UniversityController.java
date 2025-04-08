package main_package.controller;

import io.github.resilience4j.ratelimiter.RateLimiter;
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
  private final RateLimiter rateLimiter = RateLimiter.ofDefaults("UniversityControllerRateLimiter");

  public UniversityController(UniversityService universityService) {
    this.universityService = universityService;
  }

  @Override
  public ResponseEntity<List<UniversityGetResponse>> getAllUniversitiesByUserId(Long id) {
    return rateLimiter.executeSupplier(() -> {
      return ResponseEntity.status(HttpStatus.OK)
          .body(universityService.getAllUniversitiesById(id).stream().map(universityData -> new UniversityGetResponse(universityData.name(), universityData.students(), universityData.location())).collect(Collectors.toList()));
    });
  }

  @Override
  public ResponseEntity<List<UniversityGetResponse>> addUniversityToUserById(Long id, UniversityCreateRequest university) {
    return rateLimiter.executeSupplier(() -> {
      universityService.addUniversity(university);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    });
  }
}
