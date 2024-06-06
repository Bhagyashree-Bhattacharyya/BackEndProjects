package org.myworkspace.LibraryManagement.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.myworkspace.LibraryManagement.Entities.Author.Author;
import org.myworkspace.LibraryManagement.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;

    public Author getAuthorData(String email) {
        logger.info("Fetching author with email: {}", email);
        Author author = authorRepository.getAuthorByEmail(email);
        logger.info("Author found: {}", author);
        return author;
    }
}
