package main_package.repository;

import main_package.entity.UserData;
import main_package.exception.UserNotFoundException;

public interface UserRepository {
  UserData getUserDataById(Long id) throws UserNotFoundException;
  UserData createUser(UserData user);
}
