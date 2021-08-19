package com.maven.bank.dataStore;

import com.maven.bank.entities.Account;
import com.maven.bank.entities.CurrentAccount;
import com.maven.bank.entities.Customer;
import com.maven.bank.entities.SavingsAccount;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepo {
    private static Map<Long, Customer> customers = new HashMap<> ();

    public static  Map<Long, Customer> getCustomers() {
        return customers;
    }

    private void setCustomers(Map<Long, Customer> customers) {
        this.customers = customers;
    }
    static {
        tearDown();

    }
    public static void tearDown(){
        Customer john = new Customer();

        Account johnAccount = new SavingsAccount();

        john.setBvn (1);
        john.getEmail ("john@doe.com");
        john.setFirstName ("john");
        john.setRelationshipStartDate(johnAccount.getStartDate());
        john.setSurname ("doe");
        john.setPhone ("12345678901");
        Account johnSavingsAccount = new SavingsAccount(1000110001);
        john.getAccounts().add(johnSavingsAccount);


        Account johnCurrentAccount = new CurrentAccount(1000110002,  new BigDecimal(50000000));
        john.getAccounts().add(johnCurrentAccount);
        customers.put(john.getBvn(), john);

        Customer jane = new Customer ();
        jane.setBvn (2);

        jane.getEmail ("jane@blackie.com");
        jane.setFirstName ("jane");
        jane.setSurname ("blackie");
        jane.setPhone ("90876543211");
        Account janeSavingsAccount = new SavingsAccount(1000110003);
        jane.setRelationshipStartDate(janeSavingsAccount.getStartDate());
        jane.getAccounts().add(janeSavingsAccount);
        customers.put(jane.getBvn(), jane);
    }


}
