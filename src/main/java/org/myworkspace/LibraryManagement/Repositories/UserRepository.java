package org.myworkspace.LibraryManagement.Repositories;

import org.myworkspace.LibraryManagement.Entities.User.User;
import org.myworkspace.LibraryManagement.Entities.User.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPhoneNo(String phoneNo);

    User findByPhoneNoAndUserType(String phoneNo, UserType type);
    // check on book issue based on user type
}
