package org.myworkspace.LibraryManagement.Entities.Author;

import jakarta.persistence.*;
import lombok.*;
import org.myworkspace.LibraryManagement.Entities.BaseEntity;
import org.myworkspace.LibraryManagement.Entities.Book.Book;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(unique = true, length = 50)
    private String email;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList;
}
