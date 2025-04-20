package main_package.repository;

import main_package.entity.User;
import main_package.entity.UserData;
import main_package.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
  UserData getUserDataById(Long id) throws UserNotFoundException;
}
