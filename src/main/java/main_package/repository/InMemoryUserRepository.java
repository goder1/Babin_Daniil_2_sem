package main_package.repository;

import main_package.entity.UserData;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository{
  public UserData testUserOne = new UserData("genius", 6L);
  public UserData testUserTwo = new UserData("helper", 2L);

  @Override
  public UserData getUserDataById(Long id) {
    return testUserOne;
  }
  @Override
  public Long createUser(UserData user) {
    return 3L;
  }
}
