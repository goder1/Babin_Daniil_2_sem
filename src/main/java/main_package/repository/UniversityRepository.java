package main_package.repository;

import main_package.entity.UniversityData;

import java.util.ArrayList;

public interface UniversityRepository {
  ArrayList<UniversityData> getAllUniversitiesDataById(Long id);

  Long createUniversity(UniversityData university);
}
