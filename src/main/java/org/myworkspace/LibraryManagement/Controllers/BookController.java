package org.myworkspace.LibraryManagement.Controllers;

import jakarta.validation.Valid;
import org.myworkspace.LibraryManagement.DTOs.BookRequest;
import org.myworkspace.LibraryManagement.Entities.Book.Book;
import org.myworkspace.LibraryManagement.Entities.Filtering.FilterType;
import org.myworkspace.LibraryManagement.Entities.Filtering.Operator;
import org.myworkspace.LibraryManagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public Book addBook(@RequestBody @Valid BookRequest bookRequest){
        Book book = bookService.addBook(bookRequest);
        return book;
    }

    @GetMapping("/filter")
    public List<Book> filter(@RequestParam("filterBy") FilterType filterType,
                             @RequestParam("operator") Operator operator,
                             @RequestParam("value") String value){
        return bookService.filter(filterType, operator, value);
    } // need to refactor for more than one request params -- like multiple books by same author
}
