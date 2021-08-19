package com.maven.bank.services;

import com.maven.bank.dataStore.CustomerRepo;
import com.maven.bank.dataStore.LoanRequestStatus;
import com.maven.bank.entities.Account;
import com.maven.bank.entities.CurrentAccount;
import com.maven.bank.entities.Customer;
import com.maven.bank.entities.LoanRequest;
import com.maven.bank.exceptions.MavenBankException;
import com.maven.bank.exceptions.MavenBankLoanException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.maven.bank.dataStore.LoanRequestStatus.NEW;
import static com.maven.bank.dataStore.LoanType.SME;
import static org.junit.jupiter.api.Assertions.*;

class LoanRequestServiceImplTest {
    private LoanRequest johnLoanRequest;
    private AccountServices accountService;
    private LoanService loanService;
    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
        loanService = new LoanServiceImpl();
        johnLoanRequest = new LoanRequest();

        johnLoanRequest.setStartDate(LocalDateTime.now());
        johnLoanRequest.setInterestRate(0.1);
        johnLoanRequest.setStatus(NEW);
        johnLoanRequest.setTenor(24);
        johnLoanRequest.setType(SME);
    }
    @Test
    void approveLoanRequestWithAccountBalance(){
        try{
        Account johnCurrentAccount = accountService.findAccount(1000110002);
            johnLoanRequest.setLoanAmount(BigDecimal.valueOf(9000000));
        assertNull(johnCurrentAccount.getAccountLoanRequest());
        johnCurrentAccount.setAccountLoanRequest(johnLoanRequest);

        LoanRequest processedLoanRequest = loanService.approveLoanRequest(johnCurrentAccount);
        assertEquals(LoanRequestStatus.APPROVED, processedLoanRequest.getStatus());
    } catch (
    MavenBankException e) {
        e.printStackTrace();
    }
    }
    @Test
    void approveLoanRequestWithoutNullAccount(){
        assertThrows(MavenBankLoanException.class,  ()->loanService.approveLoanRequest(null) );
    }
    @Test
    void approvedLoanWithNullLoan() throws MavenBankLoanException {
        CurrentAccount accountWithoutLoan = new CurrentAccount();
        assertThrows(MavenBankLoanException.class,()->loanService.approveLoanRequest(accountWithoutLoan));
    }
    @Test
    void approveLoanWithLengthOfRelationship() {
        try {
            Account johnSavingsAccount = accountService.findAccount((1000110001));
            Optional<Customer> optionalCustomer = CustomerRepo.getCustomers().values().stream().findFirst();
            Customer john = optionalCustomer.isPresent() ? optionalCustomer.get() : null;
            assertNotNull(john);
            john.setRelationshipStartDate(johnSavingsAccount.getStartDate().minusYears(2));
            johnLoanRequest.setLoanAmount(BigDecimal.valueOf(3000000));
            johnSavingsAccount.setAccountLoanRequest(johnLoanRequest);

        } catch (MavenBankException e) {
            e.printStackTrace();
        }
    }
        @Test
        void approveLoanRequestWithAccountBalanceAndHighLoanRequestAmount(){
            try{
                Account johnCurrentAccount = accountService.findAccount(1000110002);
                johnLoanRequest.setLoanAmount(BigDecimal.valueOf(90000000));
                assertNull(johnCurrentAccount.getAccountLoanRequest());
                johnCurrentAccount.setAccountLoanRequest(johnLoanRequest);

                LoanRequest processedLoanRequest = loanService.approveLoanRequest(johnCurrentAccount);
                assertEquals(LoanRequestStatus.PENDING, processedLoanRequest.getStatus());
            } catch (
                    MavenBankException e) {
                e.printStackTrace();
            }
    }
}