package main_package.repository;

import main_package.entity.UniversityData;
import main_package.exception.UniversitiesNotFoundException;

import java.util.ArrayList;

public interface UniversityRepository {
  ArrayList<UniversityData> getAllUniversitiesDataById(Long id) throws UniversitiesNotFoundException;
  Long createUniversity(UniversityData university);
}
