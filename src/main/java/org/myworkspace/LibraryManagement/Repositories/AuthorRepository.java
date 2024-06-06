package org.myworkspace.LibraryManagement.Repositories;

import org.myworkspace.LibraryManagement.Entities.Author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(value = "select * from author where email = :email", nativeQuery = true)
    Author getAuthorByEmail(String email);
}
