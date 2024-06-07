package org.myworkspace.LibraryManagement.Services;

import jakarta.transaction.Transactional;
import org.myworkspace.LibraryManagement.DTOs.TxnRequest;
import org.myworkspace.LibraryManagement.Entities.Book.Book;
import org.myworkspace.LibraryManagement.Entities.Filtering.FilterType;
import org.myworkspace.LibraryManagement.Entities.Filtering.Operator;
import org.myworkspace.LibraryManagement.Entities.Transaction.Txn;
import org.myworkspace.LibraryManagement.Entities.Transaction.TxnStatus;
import org.myworkspace.LibraryManagement.Entities.User.User;
import org.myworkspace.LibraryManagement.Exceptions.TxnException;
import org.myworkspace.LibraryManagement.Repositories.TxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TxnService {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TxnRepository txnRepository;


    @Transactional(rollbackOn = {TxnException.class})
    public String create(TxnRequest txnRequest) throws TxnException {

        User userFromDB = userService.getUserByPhoneNo(txnRequest.getUserPhoneNo());
        if (userFromDB == null) {
            throw new TxnException("Consumer Not Registered");
        }

        List<Book> ListOfBooks = bookService.filter(FilterType.BOOK_NO, Operator.EQUALS, txnRequest.getBookNo());
        if (ListOfBooks.isEmpty()) {
            throw new TxnException("Book Not Found in Register");
        }
        Book bookFromDB = ListOfBooks.get(0);
        if (bookFromDB.getUser() != null) {
            throw new TxnException("Currently not Available ");
        }

        String txnId = UUID.randomUUID().toString();
        Txn txn = Txn.builder().txnId(txnId).user(userFromDB).book(bookFromDB).txnStatus(TxnStatus.ISSUED).build();

        txnRepository.save(txn);

        bookFromDB.setUser(userFromDB);
        bookService.updateBookData(bookFromDB);

        return txnId;
    }
}
