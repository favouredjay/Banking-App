package com.maven.bank.entities;

import com.maven.bank.dataStore.TransactionType;
import com.maven.bank.services.BankService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankTransaction {
    private long id;
    private LocalDateTime dateTime;
    private TransactionType transactionType;
    private BigDecimal txAmount;

    public BankTransaction(TransactionType type, BigDecimal amount){
        this.id = BankService.generateAccountNumber();
        this.dateTime = LocalDateTime.now();
        this.transactionType = type;
        txAmount = amount;
    }
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

    public BigDecimal getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }




}
