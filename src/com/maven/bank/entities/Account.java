package com.maven.bank.entities;

import com.maven.bank.dataStore.AccountType;
import com.maven.bank.exceptions.MavenBankTransactionException;


import java.math.BigDecimal;
public class Account {

    private long accountNumber;
    private AccountType typeOfAccount;
    private BigDecimal balance = BigDecimal.ZERO;
    private static String accountPin;

    public Account() {
    }

    public Account(long accountNumber, AccountType type) {
        this.accountNumber = accountNumber;
        typeOfAccount = type;
    }

    public Account(long accountNumber, AccountType type, BigDecimal balance) {
        this.accountNumber = accountNumber;
        typeOfAccount = type;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(AccountType typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static String getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(String accountPin) throws MavenBankTransactionException {
        Account.accountPin = accountPin;
        validatePin(accountPin);

    }

    public void validatePin(String pin) throws MavenBankTransactionException {
        if (pin.length() != 4) {
            throw new MavenBankTransactionException("wrong Pin");
        }
        if (!accountPin.equals(pin)) {
            throw new MavenBankTransactionException("Wrong Pin");
        }
    }
}