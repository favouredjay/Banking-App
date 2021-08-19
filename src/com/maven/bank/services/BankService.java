package com.maven.bank.services;

import java.time.LocalDate;

public class BankService {

        private static long currentBVN = 2;
        private static long currentAccountNumber = 1000110003;
    private static long currentTransactionId = 0;
    private static LocalDate yearOfOpening;

    public static long CurrentTransactionId() {
        return currentTransactionId;
    }

    private static long generateTransactionId(long currentTransactionId) {
        currentTransactionId++;
        return currentTransactionId;
    }
    private static void setCurrentTransactionId(long currentTransactionId){
        BankService.currentTransactionId = currentTransactionId;
    }
    public static LocalDate getYearOfOpening() {
        return yearOfOpening;
    }

    public static void setYearOfOpening(LocalDate yearOfOpening) {
        BankService.yearOfOpening = yearOfOpening;
    }

    public static long generateBvn(){
            currentBVN++;
            return currentBVN;
        }

        public static long generateAccountNumber(){
            currentAccountNumber++;
            return currentAccountNumber;

        }

        public static long getCurrentAccountNumber() {
            return currentAccountNumber;
        }

        public static long getCurrentBVN() {
            return currentBVN;
        }

        private static void setCurrentBVN(long currentBVN) {
            BankService.currentBVN = currentBVN;
        }

        private static void setCurrentAccountNumber(long currentAccountNumber){
            BankService.currentAccountNumber = currentAccountNumber;
        }

    public static void tearDown() {
            currentBVN = 2;
            currentAccountNumber = 1000110003;
    }

}
