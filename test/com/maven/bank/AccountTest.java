package com.maven.bank;

import com.maven.bank.dataStore.AccountType;
import com.maven.bank.dataStore.CustomerRepo;
import com.maven.bank.entities.Account;
import com.maven.bank.entities.Customer;
import com.maven.bank.entities.SavingsAccount;
import com.maven.bank.exceptions.MavenBankTransactionException;
import com.maven.bank.services.BankService;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {


        Customer john;
        SavingsAccount johnSavingsAccount;
        @BeforeEach
        void setUp(){
            john = new Customer();
            johnSavingsAccount = new SavingsAccount();

        }

        @Test
        void openAccount() throws MavenBankTransactionException {
            john.setBvn (BankService.generateBvn ());
            john.getEmail ("john@doe.com");
            john.setFirstName ("john");
            john.setSurname ("doe");
            john.setPhone ("12345678901");

            johnSavingsAccount.setAccountNumber(BankService.generateAccountNumber ());
//            johnSavingsAccount.setTypeOfAccount (AccountType.SAVINGS);
            johnSavingsAccount.setBalance (new BigDecimal (5000));
            johnSavingsAccount.setAccountPin ("1470");
            john.getAccounts().add(johnSavingsAccount);

            assertTrue(CustomerRepo.getCustomers().isEmpty ());

        }


}
