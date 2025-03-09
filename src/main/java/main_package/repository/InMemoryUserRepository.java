package main_package.repository;

import main_package.entity.UserData;
import main_package.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository{
  public UserData testUserOne = new UserData("genius", 6L);
  public UserData testUserTwo = new UserData("helper", 2L);

  @Override
  public UserData getUserDataById(Long id) throws UserNotFoundException{
    if (id > 2) {
      throw new UserNotFoundException();
    }
    return testUserOne;
  }
  @Override
  public UserData createUser(UserData user) {
    return new UserData("Dan", 24L);
  }
}
