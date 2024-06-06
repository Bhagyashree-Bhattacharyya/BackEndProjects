package org.myworkspace.LibraryManagement.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myworkspace.LibraryManagement.Entities.Author.Author;
import org.myworkspace.LibraryManagement.Entities.Book.Book;
import org.myworkspace.LibraryManagement.Entities.Book.BookType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {

    @NotBlank(message = "book title should not be blank")
    private String bookTitle;
    @NotBlank(message = "book no is a mandatory field !!!")
    private String bookNo;
    @NotBlank(message = "author name should not be blank")
    private String authorName;
    @NotBlank(message = "author email should not be blank")
    private String authorEmail;
    @NotNull(message = "book type should not be blank")
    private BookType type;
    @Positive(message = "security amount should be positive integer !!!")
    private Integer securityAmount;

    public Author toAuthor() {
        return Author.builder().email(this.authorEmail).name(this.authorName).build();
    }

    public Book toBook() {
        return Book.builder().bookNo(this.bookNo).title(this.bookTitle)
                .securityAmount(this.securityAmount).bookType(this.type).build();
    }
}
