package org.myworkspace.LibraryManagement.Services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.myworkspace.LibraryManagement.DTOs.TxnRequest;
import org.myworkspace.LibraryManagement.Entities.Transaction.Txn;
import org.myworkspace.LibraryManagement.Entities.User.User;
import org.myworkspace.LibraryManagement.Exceptions.TxnException;
import org.myworkspace.LibraryManagement.Repositories.TxnRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestTxnService {

    @InjectMocks
    private TxnService txnService;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @Mock
    private TxnRepository txnRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(txnService, "validDays", "12");
        ReflectionTestUtils.setField(txnService, "finePerDay", "5");
    }

    @Test
    public void testCalculateFine(){
        // need to check how to write
    }


    @Test(expected = TxnException.class)
    public void testGetUserFromDB () throws TxnException {
        TxnRequest txnRequest = TxnRequest.builder().build();
        when(userService.getConsumerByPhoneNo(any())).thenReturn(null);
        txnService.getUserFromDB(txnRequest);
    }

    @Test
    public void testGetUserFromDBWhenNoException() throws TxnException {
        TxnRequest txnRequest = TxnRequest.builder().build();
        User user = User.builder().id(1).build();
        when(userService.getConsumerByPhoneNo(any())).thenReturn(user);
        User output = txnService.getUserFromDB(txnRequest);
        Assert.assertEquals(user.getId(),output.getId());
    }
}
