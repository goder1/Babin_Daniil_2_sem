package main_package.repository;

import main_package.entity.University;
import main_package.exception.UniversitiesNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
  ArrayList<UniversityData> getAllUniversitiesDataById(Long id) throws UniversitiesNotFoundException;
}
