package com.maven.bank.services;

import com.maven.bank.Account;
import com.maven.bank.Customer;
import com.maven.bank.dataStore.AccountType;
import com.maven.bank.dataStore.CustomerRepo;
import com.maven.bank.dataStore.TransactionType;
import com.maven.bank.exceptions.MavenBankException;
import com.maven.bank.exceptions.MavenBankInsufficientBankException;
import com.maven.bank.exceptions.MavenBankTransactionException;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountServices {


        @Override
        public long openAccount (Customer theCustomer, AccountType type) throws MavenBankException {
        if (theCustomer == null || type == null) {
            throw new MavenBankException("customer and account type required to open new account");
        }
        if (accountTypeExists(theCustomer, type)) {
            throw new MavenBankException("Customer already has the requested account type ");
        }

        Account newAccount = new Account();
        newAccount.setAccountNumber(BankService.generateAccountNumber());
        newAccount.setTypeOfAccount(type);
        theCustomer.getAccounts().add(newAccount);
        CustomerRepo.getCustomers().put(theCustomer.getBvn(), theCustomer);
        return newAccount.getAccountNumber();
    }

    @Override
    public BigDecimal deposit(BigDecimal amount, long accountNumber) throws MavenBankTransactionException, MavenBankException, MavenBankInsufficientBankException {


        Account theAccount= findAccount(accountNumber);
       transactionType(amount,theAccount, TransactionType.DEPOSIT);
        BigDecimal newBalance = theAccount.getBalance().add(amount);
        theAccount.setBalance(newBalance);
        return newBalance;
    }



    @Override
    public BigDecimal withdraw(BigDecimal amount, long accountNumber) throws MavenBankException, MavenBankInsufficientBankException {
            Account theAccount= findAccount(accountNumber);
            transactionType(amount, theAccount, TransactionType.WITHDRAW);
            BigDecimal newBalance = debitAccount(amount, accountNumber);

        return newBalance;
    }

    private BigDecimal debitAccount(BigDecimal amount, long accountNumber) throws MavenBankException {
            Account theAccount = findAccount(accountNumber);
        BigDecimal newBalance = theAccount.getBalance().subtract(amount);
        theAccount.setBalance(newBalance);
        return newBalance;
    }

//    public void validateWithdraw(BigDecimal amount, Account account) throws MavenBankInsufficientBankException, MavenBankException {
//
//    if(amount.compareTo(BigDecimal.ZERO)< BigDecimal.ONE.intValue()){
//        throw new MavenBankInsufficientBankException("Invalid withdrawal amount");
//    }
//    Account withdrawAccount = findAccount(account);
//    if(withdrawAccount == null){
//        throw new MavenBankException("Account not found");
//    }
//    Account depositAccount = findAccount(account);
//    if(amount.compareTo(depositAccount.getBalance()) > 0){
//        throw new MavenBankInsufficientBankException("Insufficient Account balance");
//    }



    @Override
    public Account findAccount(long accountNumber) throws MavenBankException {
       Account foundAccount = null;
       boolean accountFound = false;
       for(Customer customer : CustomerRepo.getCustomers().values()){
               for(Account anAccount:customer.getAccounts()){
                   if(anAccount.getAccountNumber() == accountNumber){
                       foundAccount = anAccount;
                       accountFound = true;
                       break;
                   }
               }
               if (accountFound){
                   break;

               }
           }return foundAccount;

    }

    @Override
    public Account findAccount(Customer customer, long accountNumber) throws MavenBankException {
        return null;
    }

    private boolean accountTypeExists (Customer aCustomer, AccountType type){
        boolean accountTypeExists = false;
        for (Account customerAccount : aCustomer.getAccounts()) {
            if (customerAccount.getTypeOfAccount() == type) {
                accountTypeExists = true;
                break;
            }
        }
        return accountTypeExists;

    }
   public void transactionType(BigDecimal amount, Account account, TransactionType type) throws MavenBankException, MavenBankInsufficientBankException {
       if(amount.compareTo(BigDecimal.ZERO)< BigDecimal.ZERO.intValue()){
           throw new MavenBankTransactionException("Amount cannot be negative");
       }

       if(account == null){
           throw new MavenBankException("Account not found");
       }

       if(type== TransactionType.WITHDRAW&& amount.compareTo(account.getBalance()) > BigDecimal.ZERO.intValue()){
           throw new MavenBankInsufficientBankException("Insufficient Account balance");
       }


   }




}