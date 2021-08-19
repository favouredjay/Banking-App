package com.maven.bank.entities;

import com.maven.bank.exceptions.MavenBankTransactionException;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Account {
    private long accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;
    private static String accountPin;
    private LoanRequest accountLoanRequest;
    private LocalDateTime startDate;
    private Set<BankTransaction> transactions = new HashSet<>();

    public Set<BankTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LoanRequest getAccountLoanRequest() {
        return accountLoanRequest;
    }

    public void setAccountLoanRequest(LoanRequest accountLoanRequest) {
        this.accountLoanRequest = accountLoanRequest;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
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