package org.myworkspace.LibraryManagement.Services;

import org.myworkspace.LibraryManagement.DTOs.BookRequest;
import org.myworkspace.LibraryManagement.Entities.Author.Author;
import org.myworkspace.LibraryManagement.Entities.Book.Book;
import org.myworkspace.LibraryManagement.Repositories.AuthorRepository;
import org.myworkspace.LibraryManagement.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(BookRequest bookRequest) {
        Author authorFromDB = authorRepository.getAuthorByEmail(bookRequest.getAuthorEmail());
        if(authorFromDB == null){
            authorFromDB = authorRepository.save(bookRequest.toAuthor());
        }
        Book book = bookRequest.toBook();
        book.setAuthor(authorFromDB);
        return bookRepository.save(book);
    }
}
