package org.myworkspace.LibraryManagement.Controllers;

import org.myworkspace.LibraryManagement.DTOs.TxnRequest;
import org.myworkspace.LibraryManagement.Exceptions.TxnException;
import org.myworkspace.LibraryManagement.Services.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("createTxn")
    public String create(@RequestBody TxnRequest txnRequest) throws TxnException {
        return txnService.create(txnRequest);
    }
}
