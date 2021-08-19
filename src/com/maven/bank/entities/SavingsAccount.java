package com.maven.bank.entities;

import com.maven.bank.dataStore.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SavingsAccount extends Account{
    public SavingsAccount() {

        setStartDate(LocalDateTime.now());
    }

    public SavingsAccount(long accountNumber) {
        this();
       super.setAccountNumber(accountNumber);

    }

    public SavingsAccount(long accountNumber, BigDecimal balance) {
       this(accountNumber);
       setBalance(balance);
    }

}
