package com.maven.bank.entities;

import com.maven.bank.dataStore.AccountType;

import java.math.BigDecimal;

public class SavingsAccount extends Account{
    public SavingsAccount() {
    }

    public SavingsAccount(long accountNumber) {
       super.setAccountNumber(accountNumber);
    }

    public SavingsAccount(long accountNumber, BigDecimal balance) {
       setAccountNumber(accountNumber);
       setBalance(balance);
    }

}
