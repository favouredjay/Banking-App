package com.maven.bank.services;

import com.maven.bank.entities.Account;
import com.maven.bank.entities.Loan;
import com.maven.bank.exceptions.MavenBankLoanException;

public interface LoanService {
    public  Loan approveLoan(Account loanService) throws MavenBankLoanException;
}
