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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TxnRepository txnRepository;

    @Value("${book.valid.upto}")
    private String validDays;

    @Value("${book.fine.amount.per.day}")
    private String finePerDay;


    public User getUserFromDB(TxnRequest txnRequest) throws TxnException {
        User userFromDB = userService.getUserByPhoneNo(txnRequest.getUserPhoneNo());
        if (userFromDB == null) {
            throw new TxnException("Consumer Not Registered");
        }
        return userFromDB;
    }

    public Book getBookFromDB(TxnRequest txnRequest) throws TxnException {
        List<Book> ListOfBooks = bookService.filter(FilterType.BOOK_NO, Operator.EQUALS, txnRequest.getBookNo());
        if (ListOfBooks.isEmpty()) {
            throw new TxnException("Book Not Found in Register");
        }
        Book bookFromDB = ListOfBooks.get(0);
        return bookFromDB;
    }

    public String create(TxnRequest txnRequest) throws TxnException {

        User userFromDB = getUserFromDB(txnRequest);
        Book bookFromDB = getBookFromDB(txnRequest);

        if (bookFromDB.getUser() != null) {
            throw new TxnException("Currently not Available ");
        }
        return createTxn(userFromDB, bookFromDB);
    }

    @Transactional(rollbackOn = {TxnException.class})
    public String createTxn(User userFromDB, Book bookFromDB){

        String txnId = UUID.randomUUID().toString();
        Txn txn = Txn.builder().txnId(txnId).user(userFromDB).book(bookFromDB)
                    .txnStatus(TxnStatus.ISSUED).build();

        txnRepository.save(txn);

        bookFromDB.setUser(userFromDB);
        bookService.updateBookData(bookFromDB);

        return txnId;
    }

    @Transactional(rollbackOn = {TxnException.class})
    public String returnBook(TxnRequest txnRequest) throws TxnException {
        User userFromDB = getUserFromDB(txnRequest);
        Book bookFromDB = getBookFromDB(txnRequest);
        if(bookFromDB.getUser() != userFromDB){
            throw new TxnException("Consumer Id DO NOT MATCH with the BookNo");
        }
        Txn txn = txnRepository.findByUserPhoneNoAndBookBookNoAndTxnStatus(
                txnRequest.getUserPhoneNo(), txnRequest.getBookNo(), TxnStatus.ISSUED);
        int settleAmount = calculateFine(txn, bookFromDB.getSecurityAmount());
        if(settleAmount == bookFromDB.getSecurityAmount()){
            txn.setTxnStatus(TxnStatus.RETURNED);
        } else {
            txn.setTxnStatus(TxnStatus.FINED);
        }
        txn.setSettlementAmount(settleAmount);
        bookFromDB.setUser(null);
        bookService.updateBookData(bookFromDB);
        return String.valueOf(settleAmount);
    }

    public int calculateFine(Txn txn, int securityAmount) {
        long issueDate = txn.getCreatedOn().getTime();
        long returnDate = System.currentTimeMillis();
        int HoldingDays = (int) TimeUnit.DAYS.convert(returnDate-issueDate, TimeUnit.MILLISECONDS);
        if (HoldingDays > Integer.valueOf(validDays)) {
            int fineAmount = (HoldingDays-Integer.valueOf(validDays))*Integer.valueOf(finePerDay);
            return securityAmount-fineAmount;
        }
        return securityAmount;
    }
}
