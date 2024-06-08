package org.myworkspace.LibraryManagement.Repositories;

import org.myworkspace.LibraryManagement.Entities.Transaction.Txn;
import org.myworkspace.LibraryManagement.Entities.Transaction.TxnStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn, Integer> {
    Txn findByUserPhoneNoAndBookBookNoAndTxnStatus(String phoneNo, String bookNo, TxnStatus status);
}
