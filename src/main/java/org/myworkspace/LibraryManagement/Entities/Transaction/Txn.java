package org.myworkspace.LibraryManagement.Entities.Transaction;

import jakarta.persistence.*;
import lombok.*;
import org.myworkspace.LibraryManagement.Entities.BaseEntity;
import org.myworkspace.LibraryManagement.Entities.Book.Book;
import org.myworkspace.LibraryManagement.Entities.User.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
public class Txn extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, unique = true)
    private String txnId;

    @Enumerated
    private TxnStatus txnStatus;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Book book;

//    @CreationTimestamp
//    private Date createdOn;
//
//    @CreationTimestamp
//    private Date updatedOn;
}