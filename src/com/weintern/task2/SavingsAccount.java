package com.weintern.task2;

public class SavingsAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 500.00;

    public SavingsAccount(String accountNumber, String holderName,
                          double openingBalance) {
        super(accountNumber, holderName, openingBalance);

        if (openingBalance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                    "Savings Account requires minimum opening balance of Rs. 500."
            );
        }
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    @Override
    public boolean canWithdraw(double amount) {
        return balance - amount >= MINIMUM_BALANCE;
    }
}