package com.weintern.task2;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankingService {

    private final Map<String, BankAccount> accounts;
    private int nextAccountNumber;

    public BankingService() {
        accounts = new LinkedHashMap<>();
        nextAccountNumber = 1001;
    }

    public BankAccount createAccount(String holderName, String type,
                                     double openingBalance) {

        if (holderName == null || holderName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Account holder name cannot be empty."
            );
        }

        if (type == null) {
            throw new IllegalArgumentException("Account type is required.");
        }

        String accountNumber = String.valueOf(nextAccountNumber);
        BankAccount account;

        if (type.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(
                    accountNumber, holderName.trim(), openingBalance
            );
        } else if (type.equalsIgnoreCase("current")) {
            account = new CurrentAccount(
                    accountNumber, holderName.trim(), openingBalance
            );
        } else {
            throw new IllegalArgumentException(
                    "Invalid account type. Choose Savings or Current."
            );
        }

        accounts.put(accountNumber, account);
        nextAccountNumber++;

        return account;
    }

    public BankAccount findAccount(String accountNumber) {

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number is required.");
        }

        BankAccount account = accounts.get(accountNumber.trim());

        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        return account;
    }

    public void transferMoney(String fromNumber, String toNumber,
                              double amount) {

        if (fromNumber.equals(toNumber)) {
            throw new IllegalArgumentException(
                    "Source and destination accounts must be different."
            );
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Transfer amount must be greater than zero."
            );
        }

        BankAccount source = findAccount(fromNumber);
        BankAccount destination = findAccount(toNumber);

        // Validate withdrawal before changing either account.
        if (!source.canWithdraw(amount)) {
            throw new IllegalArgumentException(
                    "Transfer failed: insufficient balance or minimum balance violation."
            );
        }

        source.withdraw(amount);
        destination.deposit(amount);

        source.recordTransfer(
                "TRANSFER OUT",
                amount,
                "Transferred to account " + toNumber
        );

        destination.recordTransfer(
                "TRANSFER IN",
                amount,
                "Received from account " + fromNumber
        );
    }

    public void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n========== ALL BANK ACCOUNTS ==========");

        for (BankAccount account : accounts.values()) {
            account.displayAccountDetails();
        }
    }

    public void displayTransactionHistory(String accountNumber) {

        BankAccount account = findAccount(accountNumber);

        System.out.println("\n========== TRANSACTION HISTORY ==========");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getHolderName());

        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : account.getTransactions()) {
            System.out.println(transaction);
        }
    }

    public void displayBankStatement(String accountNumber) {

        BankAccount account = findAccount(accountNumber);

        System.out.println("\n============== BANK STATEMENT ==============");
        account.displayAccountDetails();

        System.out.println("Transaction Details:");

        for (Transaction transaction : account.getTransactions()) {
            System.out.println(transaction);
        }

        System.out.printf(
                "%nClosing Balance: Rs. %.2f%n",
                account.getBalance()
        );

        System.out.println("============================================");
    }
}