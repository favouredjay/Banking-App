package com.maven.bank.services;

import com.maven.bank.dataStore.LoanStatus;
import com.maven.bank.entities.Account;
import com.maven.bank.entities.CurrentAccount;
import com.maven.bank.entities.Loan;
import com.maven.bank.exceptions.MavenBankException;
import com.maven.bank.exceptions.MavenBankLoanException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.maven.bank.dataStore.LoanStatus.NEW;
import static com.maven.bank.dataStore.LoanType.SME;
import static org.junit.jupiter.api.Assertions.*;

class LoanServiceImplTest {
    private Loan johnLoan;
    private AccountServices accountService;
    private LoanService loanService;
    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
        loanService = new LoanServiceImpl();
        johnLoan = new Loan();
        johnLoan.setLoanAmount(BigDecimal.valueOf(9000000));
        johnLoan.setStartDate(LocalDateTime.now());
        johnLoan.setInterestRate(0.1);
        johnLoan.setStatus(NEW);
        johnLoan.setTenor(24);
        johnLoan.setType(SME);
    }
    @Test
    void approveLoan(){
        try{
        Account johnCurrentAccount = accountService.findAccount(1000110002);
        assertNull(johnCurrentAccount.getAccountLoan());
        johnCurrentAccount.setAccountLoan(johnLoan);

        Loan processedLoan = loanService.approveLoan(johnCurrentAccount);
        assertEquals(LoanStatus.APPROVED, processedLoan.getStatus());
    } catch (
    MavenBankException e) {
        e.printStackTrace();
    }
    }
    @Test
    void approveLoanWithoutNullAccount(){
        assertThrows(MavenBankLoanException.class,  ()->loanService.approveLoan(null) );
    }
    @Test
    void approvedLoanWithNullLoan() throws MavenBankLoanException {
        CurrentAccount accountWithoutLoan = new CurrentAccount();
        assertThrows(MavenBankLoanException.class,()->loanService.approveLoan(null));
    }
}