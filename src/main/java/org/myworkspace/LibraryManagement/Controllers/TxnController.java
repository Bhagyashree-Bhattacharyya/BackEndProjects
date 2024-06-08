package org.myworkspace.LibraryManagement.Controllers;

import org.myworkspace.LibraryManagement.DTOs.TxnRequest;
import org.myworkspace.LibraryManagement.Exceptions.TxnException;
import org.myworkspace.LibraryManagement.Services.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("createTxn")
    public ResponseEntity<String> create(@RequestBody TxnRequest txnRequest) throws TxnException {
        String txnId = txnService.create(txnRequest);
        return new ResponseEntity<>(txnId, HttpStatus.OK);
    }

    @PutMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody TxnRequest txnRequest) throws TxnException {
        String txnId = txnService.returnBook(txnRequest);
        return new ResponseEntity<>("to be settled with Rs." + txnId, HttpStatus.OK);
    }
}
