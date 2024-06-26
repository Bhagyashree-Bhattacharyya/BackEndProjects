package org.myworkspace.LibraryManagement.Repositories;

import org.myworkspace.LibraryManagement.Entities.User.User;
import org.myworkspace.LibraryManagement.Entities.User.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPhoneNo(String phoneNo);

    User findByPhoneNoAndUserType(String phoneNo, UserType type);

    User findByEmail(String email);
    // check on book issue based on user type
}
