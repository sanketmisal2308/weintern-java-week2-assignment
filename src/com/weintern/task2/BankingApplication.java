package com.weintern.task2;

import java.util.Scanner;

public class BankingApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BankingService bankingService =
            new BankingService();

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("       WElNTERN BANKING SYSTEM");
        System.out.println("======================================");

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;

                    case 2:
                        bankingService.displayAllAccounts();
                        break;

                    case 3:
                        depositMoney();
                        break;

                    case 4:
                        withdrawMoney();
                        break;

                    case 5:
                        transferMoney();
                        break;

                    case 6:
                        checkBalance();
                        break;

                    case 7:
                        transactionHistory();
                        break;

                    case 8:
                        bankStatement();
                        break;

                    case 9:
                        System.out.println(
                                "Thank you for using WeIntern Banking System!"
                        );
                        running = false;
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select 1 to 9."
                        );
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println(
                        "Unexpected error: " + e.getMessage()
                );
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n========== BANKING MENU ==========");
        System.out.println("1. Create Account");
        System.out.println("2. Display All Accounts");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Transfer Money");
        System.out.println("6. Check Balance");
        System.out.println("7. Transaction History");
        System.out.println("8. Bank Statement");
        System.out.println("9. Exit");
        System.out.println("==================================");
    }

    private static void createAccount() {

        System.out.println("\n--- Create Bank Account ---");

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine().trim();

        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");

        int typeChoice = readInt("Select account type: ");

        String type;

        if (typeChoice == 1) {
            type = "savings";
        } else if (typeChoice == 2) {
            type = "current";
        } else {
            System.out.println("Invalid account type.");
            return;
        }

        double openingBalance =
                readDouble("Enter opening balance (Rs.): ");

        BankAccount account = bankingService.createAccount(
                name, type, openingBalance
        );

        System.out.println("\nAccount created successfully!");
        account.displayAccountDetails();
    }

    private static void depositMoney() {

        System.out.println("\n--- Deposit Money ---");

        String accountNumber =
                readText("Enter account number: ");

        double amount = readDouble("Enter deposit amount (Rs.): ");

        BankAccount account =
                bankingService.findAccount(accountNumber);

        account.deposit(amount);

        System.out.println("Money deposited successfully!");
        System.out.printf(
                "Updated Balance: Rs. %.2f%n",
                account.getBalance()
        );
    }

    private static void withdrawMoney() {

        System.out.println("\n--- Withdraw Money ---");

        String accountNumber =
                readText("Enter account number: ");

        double amount = readDouble("Enter withdrawal amount (Rs.): ");

        BankAccount account =
                bankingService.findAccount(accountNumber);

        account.withdraw(amount);

        System.out.println("Money withdrawn successfully!");
        System.out.printf(
                "Updated Balance: Rs. %.2f%n",
                account.getBalance()
        );
    }

    private static void transferMoney() {

        System.out.println("\n--- Transfer Money ---");

        String fromAccount =
                readText("Enter sender account number: ");

        String toAccount =
                readText("Enter receiver account number: ");

        double amount = readDouble("Enter transfer amount (Rs.): ");

        bankingService.transferMoney(
                fromAccount, toAccount, amount
        );

        System.out.println("Money transferred successfully!");

        System.out.printf(
                "Sender's Balance: Rs. %.2f%n",
                bankingService.findAccount(fromAccount).getBalance()
        );

        System.out.printf(
                "Receiver's Balance: Rs. %.2f%n",
                bankingService.findAccount(toAccount).getBalance()
        );
    }

    private static void checkBalance() {

        System.out.println("\n--- Check Balance ---");

        String accountNumber =
                readText("Enter account number: ");

        BankAccount account =
                bankingService.findAccount(accountNumber);

        System.out.println("Account Holder: " + account.getHolderName());
        System.out.println("Account Type: " + account.getAccountType());

        System.out.printf(
                "Available Balance: Rs. %.2f%n",
                account.getBalance()
        );
    }

    private static void transactionHistory() {

        System.out.println("\n--- Transaction History ---");

        String accountNumber =
                readText("Enter account number: ");

        bankingService.displayTransactionHistory(accountNumber);
    }

    private static void bankStatement() {

        System.out.println("\n--- Bank Statement ---");

        String accountNumber =
                readText("Enter account number: ");

        bankingService.displayBankStatement(accountNumber);
    }

    private static String readText(String message) {

        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                double value = Double.parseDouble(input);

                if (!Double.isFinite(value)) {
                    System.out.println("Please enter a finite number.");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}