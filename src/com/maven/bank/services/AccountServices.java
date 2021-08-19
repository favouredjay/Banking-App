package com.maven.bank.services;
import com.maven.bank.dataStore.LoanRequestStatus;
import com.maven.bank.entities.Account;
import com.maven.bank.entities.BankTransaction;
import com.maven.bank.entities.Customer;
import com.maven.bank.dataStore.AccountType;
import com.maven.bank.exceptions.MavenBankException;
import com.maven.bank.exceptions.MavenBankInsufficientBankException;
import com.maven.bank.exceptions.MavenBankTransactionException;

import java.math.BigDecimal;

public interface AccountServices {
     public long openAccount(Customer theCustomer, AccountType type) throws MavenBankException;
     public long openSavingsAccount(Customer customer) throws MavenBankException;
     public long openCurrentAccount(Customer customer) throws MavenBankException;
     public BigDecimal deposit(BigDecimal amount, long accountNumber) throws MavenBankTransactionException, MavenBankException, MavenBankInsufficientBankException;
     public BigDecimal withdraw(BigDecimal amount, long accountNumber) throws MavenBankException, MavenBankInsufficientBankException;
     public Account findAccount(long accountNumber) throws MavenBankException;
     public Account findAccount(Customer customer, long accountNumber) throws MavenBankException;
     public void applyForOverdraft(Account theAccount);
     public LoanRequestStatus applyForLoan(Account theAccount);
     public void addBankTransaction(BankTransaction transaction, Account account) throws MavenBankException, MavenBankInsufficientBankException;





}
