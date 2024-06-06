package org.myworkspace.LibraryManagement.Repositories;

import org.myworkspace.LibraryManagement.Entities.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
