package com.weintern.task2;

public class CurrentAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 1000.00;

    public CurrentAccount(String accountNumber, String holderName,
                          double openingBalance) {
        super(accountNumber, holderName, openingBalance);

        if (openingBalance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                    "Current Account requires minimum opening balance of Rs. 1000."
            );
        }
    }

    @Override
    public String getAccountType() {
        return "Current";
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