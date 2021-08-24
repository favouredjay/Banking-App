package com.maven.bank.services;

import com.maven.bank.dataStore.LoanRequestStatus;
import com.maven.bank.entities.Account;
import com.maven.bank.entities.Customer;
import com.maven.bank.entities.LoanRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanEngineImpl implements LoanEngine {
    @Override
    public BigDecimal calculateLoanWithRelationship(Account account, Customer customer, LocalDateTime startDate) {
//        LocalDateTime date = LocalDateTime.parse("2021-11-13");
//        LocalDateTime currentMonth = johnC(startDate.getMonthValue());
//        int compareValue = startDate;
        BigDecimal loanAutomaticallyApprovedLoan = BigDecimal.ZERO;
        if (startDate <= 2) {
            BigDecimal relationshipVolumePercentage = BigDecimal.valueOf(0.0);
            loanAutomaticallyApprovedLoan = account.getBalance().multiply(relationshipVolumePercentage);
            LoanRequestStatus decision = LoanRequestStatus.DECLINED;
        } else if (compareValue >= 3 && compareValue <= 5) {
            BigDecimal relationshipVolumePercentage = BigDecimal.valueOf(0.02);
            loanAutomaticallyApprovedLoan = account.getBalance().multiply(relationshipVolumePercentage);
        } else if (compareValue >= 6 && compareValue <= 11) {
            BigDecimal relationshipVolumePercentage = BigDecimal.valueOf(0.04);
            loanAutomaticallyApprovedLoan = account.getBalance().multiply(relationshipVolumePercentage);
        } else if (compareValue >= 12 && compareValue <= 17) {
            BigDecimal relationshipVolumePercentage = BigDecimal.valueOf(0.06);
             loanAutomaticallyApprovedLoan = account.getBalance().multiply(relationshipVolumePercentage);
        } else if (compareValue >= 18 && compareValue <= 23) {
            BigDecimal relationshipVolumePercentage = BigDecimal.valueOf(0.08);
            loanAutomaticallyApprovedLoan = account.getBalance().multiply(relationshipVolumePercentage);
        } else if (compareValue >= 24) {
            BigDecimal relationshipVolumePercentage = BigDecimal.valueOf(0.0);
             loanAutomaticallyApprovedLoan = account.getBalance().multiply(relationshipVolumePercentage);
        } return loanAutomaticallyApprovedLoan;
    }
//    public LoanRequest loanRequest(){
//        LocalDateTime currentMonth = LocalDateTime.now().minusMonths(startDate.getMonthValue());
//        int compareValue = currentMonth.compareTo(startDate);
//        BigDecimal loanAutomaticallyApprovedLoan = BigDecimal.ZERO;
//        if (compareValue < 2) {
//        if()
//    }
}
