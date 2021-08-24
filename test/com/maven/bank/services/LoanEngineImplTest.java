package com.maven.bank.services;

import com.maven.bank.dataStore.CustomerRepo;
import com.maven.bank.entities.Account;
import com.maven.bank.entities.Customer;
import com.maven.bank.entities.LoanRequest;
import com.maven.bank.entities.SavingsAccount;
import com.maven.bank.exceptions.MavenBankException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoanEngineImplTest {
    private LoanRequest johnLoanRequest;
    private AccountServices accountService;
    private LoanService loanService;
    private LoanEngine loanEngine;

    @BeforeEach
    void setUp(){
        accountService = new AccountServiceImpl();
        loanService = new LoanServiceImpl();
        johnLoanRequest = new LoanRequest();
        loanEngine = new LoanEngineImpl();
    }

    @Test
    void calculateLoanBasedOnRelationship() throws MavenBankException {

        Account johnCurrentAccount = accountService.findAccount(1000110002);
        Optional<Customer> optionalCustomer = CustomerRepo.getCustomers().values().stream().findFirst();
        Customer john = optionalCustomer.isPresent() ? optionalCustomer.get() : null;
        johnCurrentAccount.setStartDate(LocalDateTime.now().minusMonths(24));
//        LocalDateTime currentStartDate = johnCurrentAccount.getStartDate();
        
        john.setRelationshipStartDate(johnCurrentAccount.getStartDate());
        assertEquals(BigDecimal.valueOf(50000000), johnCurrentAccount.getBalance());
        johnLoanRequest.setLoanAmount(BigDecimal.valueOf(3000000));
        johnCurrentAccount.setAccountLoanRequest(johnLoanRequest);
//        BigDecimal decision = loanEngine.calculateLoanWithRelationship(johnCurrentAccount,john, currentStartDate );
        assertEquals(BigDecimal.valueOf(5000000), loanEngine.calculateLoanWithRelationship(johnCurrentAccount,  john, johnCurrentAccount.getStartDate()));


    }

}