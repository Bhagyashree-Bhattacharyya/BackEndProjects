package org.myworkspace.LibraryManagement.Controllers;

import org.myworkspace.LibraryManagement.Entities.Author.Author;
import org.myworkspace.LibraryManagement.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAuthorData")
    public Author getAuthorData(@RequestParam("authorEmail") String email){
        return authorService.getAuthorData(email);
    }
}
