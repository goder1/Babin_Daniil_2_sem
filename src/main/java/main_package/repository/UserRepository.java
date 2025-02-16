package main_package.repository;

import main_package.entity.UserData;

public interface UserRepository {
  UserData getUserDataById(Long id);
  Long createUser(UserData user);
}
