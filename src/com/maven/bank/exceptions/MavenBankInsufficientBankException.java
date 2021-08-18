package com.maven.bank.exceptions;

public class MavenBankInsufficientBankException extends Exception {
    public MavenBankInsufficientBankException(String message){
        super(message);
    }
    public MavenBankInsufficientBankException(String message, Throwable ex){
        super(message, ex);

    }
    public MavenBankInsufficientBankException(Throwable ex){
        super(ex);
    }
}
