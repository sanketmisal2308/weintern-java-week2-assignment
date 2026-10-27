package com.weintern.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BankAccount {

    private final String accountNumber;
    private final String holderName;
    protected double balance;

    private final List<Transaction> transactions;

    public BankAccount(String accountNumber, String holderName,
                       double openingBalance) {

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number is required.");
        }

        if (holderName == null || holderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name is required.");
        }

        if (!Double.isFinite(openingBalance) || openingBalance < 0) {
            throw new IllegalArgumentException("Invalid opening balance.");
        }

        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
        this.transactions = new ArrayList<>();

        transactions.add(new Transaction(
                "OPENING", openingBalance, "Account opened", balance
        ));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public abstract String getAccountType();

    public abstract double getMinimumBalance();

    public abstract boolean canWithdraw(double amount);

    public void deposit(double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero."
            );
        }

        balance += amount;

        transactions.add(new Transaction(
                "DEPOSIT", amount, "Money deposited", balance
        ));
    }

    public void withdraw(double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (!canWithdraw(amount)) {
            throw new IllegalArgumentException(
                    "Insufficient balance or minimum balance requirement violated."
            );
        }

        balance -= amount;

        transactions.add(new Transaction(
                "WITHDRAWAL", amount, "Money withdrawn", balance
        ));
    }

    void recordTransfer(String type, double amount, String details) {
        transactions.add(new Transaction(
                type, amount, details, balance
        ));
    }

    public void displayAccountDetails() {
        System.out.println("-----------------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Holder Name    : " + holderName);
        System.out.println("Account Type   : " + getAccountType());
        System.out.printf("Balance        : Rs. %.2f%n", balance);
        System.out.printf("Minimum Balance: Rs. %.2f%n", getMinimumBalance());
        System.out.println("-----------------------------------");
    }
}