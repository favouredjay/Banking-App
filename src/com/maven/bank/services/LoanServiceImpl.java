package com.maven.bank.services;

import com.maven.bank.dataStore.LoanRequestStatus;
import com.maven.bank.entities.Account;
import com.maven.bank.entities.Customer;
import com.maven.bank.entities.LoanRequest;
import com.maven.bank.exceptions.MavenBankLoanException;

import java.math.BigDecimal;

public class LoanServiceImpl implements LoanService{
    @Override
    public LoanRequest approveLoanRequest(Account accountSeekingLoan) throws MavenBankLoanException {
        if (accountSeekingLoan == null) {
            throw  new MavenBankLoanException("Account not found");
        }
        if(accountSeekingLoan.getAccountLoanRequest() == null){
            throw new MavenBankLoanException("No loan provided for processing");
        }
        LoanRequest theLoanRequest = accountSeekingLoan.getAccountLoanRequest();
        theLoanRequest.setStatus(decideOnLoanRequest(accountSeekingLoan));


        return theLoanRequest;
    }

    @Override
    public LoanRequest approveLoanRequest(Account accountSeekingLoan, Customer customer) throws MavenBankLoanException {
        return approveLoanRequest(accountSeekingLoan);
    }

    private LoanRequestStatus decideOnLoanRequestWithAccountBalance(Account accountSeekingLoan) throws MavenBankLoanException {
        LoanRequestStatus decision = LoanRequestStatus.PENDING;
        LoanRequest theLoanRequest = accountSeekingLoan.getAccountLoanRequest();
        BigDecimal accountBalancePercentage = BigDecimal.valueOf(0.2);
        BigDecimal loanAmountApprovedAutomatically = accountSeekingLoan.getBalance().multiply(accountBalancePercentage);
        if(theLoanRequest.getLoanAmount().compareTo(loanAmountApprovedAutomatically) <BigDecimal.ZERO.intValue()){
            decision = LoanRequestStatus.APPROVED;
        }
        return decision;
    }

    private LoanRequestStatus decideOnLoanRequest(Account accountSeekingLoan) throws MavenBankLoanException {
        LoanRequestStatus decision = decideOnLoanRequestWithAccountBalance(accountSeekingLoan);
        return decision;
    }
    private LoanRequestStatus decideOnLoanRequestWithLengthOfRelationship(Account accountSeekingLoan) throws MavenBankLoanException {
        LoanRequestStatus decision = LoanRequestStatus.PENDING;
        LoanRequest theLoanRequest = accountSeekingLoan.getAccountLoanRequest();
        BigDecimal accountBalancePercentage = BigDecimal.valueOf(0.2);
        BigDecimal loanAmountApprovedAutomatically = accountSeekingLoan.getBalance().multiply(accountBalancePercentage);
        if(theLoanRequest.getLoanAmount().compareTo(loanAmountApprovedAutomatically) <BigDecimal.ZERO.intValue()){
            decision = LoanRequestStatus.APPROVED;
        }
        return decision;
    }

}
