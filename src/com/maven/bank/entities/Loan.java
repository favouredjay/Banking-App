package com.maven.bank.entities;

import com.maven.bank.dataStore.LoanRequestStatus;
import com.maven.bank.dataStore.LoanStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Loan {
    private BigDecimal loanAmount;
    private LocalDateTime startDate;
    private int tenor;
    private  double interestRate;
    private Loan loanType;
    private LoanStatus loan;
}
