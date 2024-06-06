package org.myworkspace.LibraryManagement.Entities.User;

import jakarta.persistence.*;
import lombok.*;
import org.myworkspace.LibraryManagement.Entities.BaseEntity;
import org.myworkspace.LibraryManagement.Entities.Book.Book;
import org.myworkspace.LibraryManagement.Entities.Transaction.Txn;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;

    @Column(nullable = false, unique = true, length = 15)
    private String phoneNo;

    @Column(unique = true, length = 50)
    private String email;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Enumerated
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList;

    @OneToMany(mappedBy = "user")
    private List<Txn> txnList;
}
