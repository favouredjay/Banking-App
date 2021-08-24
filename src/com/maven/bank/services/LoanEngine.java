package com.maven.bank.services;

import com.maven.bank.entities.Account;
import com.maven.bank.entities.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface LoanEngine {

    public  BigDecimal calculateLoanWithRelationship(Account account, Customer customer, LocalDateTime startDate);


}
