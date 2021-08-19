package com.maven.bank.entities;

import com.maven.bank.dataStore.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankTransaction {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private long id;
   private LocalDateTime dateTime;
   private TransactionType transactionType;
   private BigDecimal amount;



}
