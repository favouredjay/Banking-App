package com.maven.bank.entities;

import com.maven.bank.dataStore.AccountType;

import java.math.BigDecimal;

public class CurrentAccount extends Account {

    public CurrentAccount() {
    }

    public CurrentAccount(long accountNumber) {
       setAccountNumber(accountNumber);
    }

    public CurrentAccount(long accountNumber, BigDecimal balance) {
       setAccountNumber(accountNumber);
       setBalance(balance);
    }

}
