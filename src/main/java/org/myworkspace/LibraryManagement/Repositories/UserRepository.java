package org.myworkspace.LibraryManagement.Repositories;

import org.myworkspace.LibraryManagement.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
