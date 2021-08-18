package com.maven.bank.exceptions;

public class MavenBankLoanException extends MavenBankException{
    public MavenBankLoanException(String message){
        super(message);
    }
    public MavenBankLoanException(String message, Throwable e){
        super(message, e);
    }
    public MavenBankLoanException(Throwable e){
        super(e);
    }
}
