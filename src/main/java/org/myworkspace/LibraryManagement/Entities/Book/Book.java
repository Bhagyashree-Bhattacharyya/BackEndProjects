package org.myworkspace.LibraryManagement.Entities.Book;

import jakarta.persistence.*;
import lombok.*;
import org.myworkspace.LibraryManagement.Entities.Author.Author;
import org.myworkspace.LibraryManagement.Entities.BaseEntity;
import org.myworkspace.LibraryManagement.Entities.Transaction.Txn;
import org.myworkspace.LibraryManagement.Entities.User.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String title;

    @Column(length = 20, unique = true)
    private String bookNo;

    @Enumerated(value = EnumType.STRING)
    private BookType bookType;

    private Integer securityAmount;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Txn> txnList;
}
