package com.weintern.task2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final LocalDateTime dateTime;
    private final String type;
    private final double amount;
    private final String details;
    private final double balanceAfter;

    public Transaction(String type, double amount, String details,
                       double balanceAfter) {
        this.dateTime = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.details = details;
        this.balanceAfter = balanceAfter;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return String.format(
                "%-20s | %-12s | Rs. %-10.2f | Balance: Rs. %-10.2f | %s",
                dateTime.format(formatter),
                type,
                amount,
                balanceAfter,
                details
        );
    }
}