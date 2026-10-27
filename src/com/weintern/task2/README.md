# WeIntern Java Development – Week 2
## Task 2: Banking System

### 1. Project Overview
The Banking System is a console-based Java application developed as part of the WeIntern Java Development Week 2 assignment.

It allows users to create and manage bank accounts, deposit and withdraw money, transfer money between accounts, check balances, view transaction history, and generate bank statements.

### 2. Objectives
- Understand Object-Oriented Programming (OOP) concepts.
- Implement inheritance and polymorphism.
- Use encapsulation to protect account data.
- Manage multiple bank accounts using Java collections.
- Maintain transaction history.
- Handle invalid inputs and banking operation errors.

### 3. Technologies Used
- Java
- Java Collections Framework
- ArrayList
- LinkedHashMap
- Scanner
- LocalDateTime
- VS Code

### 4. Features
1. Create Savings Account.
2. Create Current Account.
3. Display all bank accounts.
4. Deposit money.
5. Withdraw money.
6. Transfer money between accounts.
7. Check account balance.
8. View transaction history.
9. Generate bank statements.
10. Validate account numbers and transaction amounts.
11. Enforce minimum balance requirements.

### 5. Project Structure

```text
Internship-java-week2-assignment/
├── src/
│   └── com/
│       └── weintern/
│           └── task2/
│               ├── BankAccount.java
│               ├── SavingsAccount.java
│               ├── CurrentAccount.java
│               ├── Transaction.java
│               ├── BankingService.java
│               └── BankingApplication.java
├── out/
├── screenshots/
└── README.md
```

### 6. Account Rules
- Savings Account minimum balance: Rs. 500.
- Current Account minimum balance: Rs. 1,000.
- Deposits and withdrawals must be greater than zero.
- Transfers require valid, different source and destination accounts.
- Overdraft is not supported.

These minimum balances are example rules implemented for this assignment.

### 7. How to Compile and Run

Open the terminal in the project root directory.

Compile the application:

```bash
javac -d out src/com/weintern/task2/*.java
```

Run the application:

```bash
java -cp out com.weintern.task2.BankingApplication
```

### 8. Menu Options
1. Create Account
2. Display All Accounts
3. Deposit Money
4. Withdraw Money
5. Transfer Money
6. Check Balance
7. Transaction History
8. Bank Statement
9. Exit

### 9. Testing
The following operations can be tested:
- Create Savings and Current Accounts.
- Deposit money into an account.
- Withdraw money while maintaining the minimum balance.
- Transfer money between two accounts.
- Check account balance.
- Display transaction history.
- Generate a bank statement.
- Test invalid account numbers and insufficient balance.

### 10. Current Limitations
- Account and transaction data are stored in memory.
- Data is not preserved after the application closes.
- This is an educational console application, not a production banking system.

### 11. Author
Developed as part of the WeIntern Java Development Week 2 assignment.