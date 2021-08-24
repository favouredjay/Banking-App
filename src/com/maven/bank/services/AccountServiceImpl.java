package com.maven.bank.services;

import com.maven.bank.dataStore.LoanRequestStatus;
import com.maven.bank.entities.*;
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
        long accountNumber = BigDecimal.ZERO.longValue();
            if(type == AccountType.SAVINGSACCOUNT) {
            openSavingsAccount(theCustomer);
        }else if (type == AccountType.CURRENTACCOUNT) {
            openCurrentAccount(theCustomer);
        }
        return accountNumber;
    }

    @Override
    public long openSavingsAccount(Customer customer) throws MavenBankException {
        if (customer == null ) {
            throw new MavenBankException("customer and account type required to open new account");
        }
        SavingsAccount
                newAccount = new SavingsAccount();
        if (accountTypeExists(customer, newAccount.getClass().getTypeName())) {
            throw new MavenBankException("Customer already has the requested account type ");
        }


        newAccount.setAccountNumber(BankService.generateAccountNumber());

        customer.getAccounts().add(newAccount);
        CustomerRepo.getCustomers().put(customer.getBvn(), customer);
        return newAccount.getAccountNumber();
    }

    @Override
    public long openCurrentAccount(Customer customer) throws MavenBankException {
        if (customer == null ) {
            throw new MavenBankException("customer and account type required to open new account");
        }
        CurrentAccount newAccount = new CurrentAccount();
        if (accountTypeExists(customer, newAccount.getClass().getTypeName())) {
            throw new MavenBankException("Customer already has the requested account type ");
        }


        newAccount.setAccountNumber(BankService.generateAccountNumber());

        customer.getAccounts().add(newAccount);
        CustomerRepo.getCustomers().put(customer.getBvn(), customer);
        return newAccount.getAccountNumber();
    }


    @Override
    public BigDecimal deposit(BigDecimal amount, long accountNumber) throws MavenBankTransactionException, MavenBankException, MavenBankInsufficientBankException {


        Account theAccount= findAccount(accountNumber);
       transactionType(amount,theAccount);

        BigDecimal newBalance = theAccount.getBalance().add(amount);
        theAccount.setBalance(newBalance);
        return newBalance;
    }



    @Override
    public BigDecimal withdraw(BigDecimal amount, long accountNumber) throws MavenBankException, MavenBankInsufficientBankException {
            Account theAccount= findAccount(accountNumber);
            transactionType(amount, theAccount);
            try {
                checkForInsufficientAmount(amount, theAccount, TransactionType.WITHDRAW);
            }
            catch (MavenBankInsufficientBankException insufficientFundsException){
                this.applyForOverdraft(theAccount);
                throw insufficientFundsException;
            }
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

    @Override
    public void applyForOverdraft(Account theAccount) {
        //TODO
    }

    @Override
    public LoanRequestStatus applyForLoan(Account theAccount) {
return null;
    }

    @Override
    public void addBankTransaction(BankTransaction transaction, Account account) throws MavenBankException, MavenBankInsufficientBankException {
        if(transaction == null || account == null){
            throw new MavenBankTransactionException("No transaction provided");
        }
        if(transaction.getTransactionType() == TransactionType.DEPOSIT){
            deposit(transaction.getTxAmount(), account.getAccountNumber());
        }
        else if(transaction.getTransactionType() == TransactionType.WITHDRAW){
            withdraw(transaction.getTxAmount(), account.getAccountNumber());
        }
        account.getTransactions().add(transaction);
    }


    private boolean accountTypeExists (Customer aCustomer, String typeName){
        boolean accountTypeExists = false;
        for (Account customerAccount : aCustomer.getAccounts()) {

            if (customerAccount.getClass().getTypeName() == typeName) {
                accountTypeExists = true;
                break;
            }
        }
        return accountTypeExists;

    }
   public void transactionType(BigDecimal amount, Account account) throws MavenBankException, MavenBankInsufficientBankException {
       if(amount.compareTo(BigDecimal.ZERO)< BigDecimal.ZERO.intValue()){
           throw new MavenBankTransactionException("Amount cannot be negative");
       }

       if(account == null){
           throw new MavenBankException("Account not found");
       }
        }

   public void checkForInsufficientAmount(BigDecimal amount, Account account, TransactionType type) throws MavenBankInsufficientBankException {
       if(type== TransactionType.WITHDRAW&& amount.compareTo(account.getBalance()) > BigDecimal.ZERO.intValue()){
           throw new MavenBankInsufficientBankException("Insufficient Account balance");
       }
        }




}