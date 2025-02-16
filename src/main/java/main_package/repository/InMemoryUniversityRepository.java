package main_package.repository;

import main_package.entity.UniversityData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InMemoryUniversityRepository implements UniversityRepository{
  @Override
  public ArrayList<UniversityData> getAllUniversitiesDataById(Long id) {
    ArrayList<UniversityData> temp = new ArrayList<>();
    temp.add(new UniversityData("MIPT", 100L, "Dolgoprudniy"));
    temp.add(new UniversityData("MGU", 99L, "Moscow"));
    return temp;
  }

  @Override
  public Long createUniversity(UniversityData university) {
    return 3L;
  }
}
