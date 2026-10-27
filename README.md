# WeIntern Java Development – Week 2 Assignment

## 📌 About the Project
This repository contains my Week 2 Java Development Internship assignments at WeIntern. The projects demonstrate Java programming, Object-Oriented Programming (OOP), Collections Framework, File Handling, and JDBC connectivity with MySQL.

## 🚀 Tasks Completed

### Task 1: Student Management System
- Add, view, search, update, and delete student records.
- Uses Java Collections (`ArrayList`).
- Calculates average grades.
- Saves and loads student data using file handling and serialization.

### Task 2: Banking Management System
- Create and manage bank accounts.
- Supports Savings Account and Current Account.
- Deposit and withdraw money.
- Transfer funds between accounts.
- Maintains transaction history.
- Demonstrates inheritance, abstraction, and polymorphism.

### Task 3: Student Database Management System (JDBC)
- Connects Java application to MySQL using JDBC.
- Add, view, search, update, and delete student records.
- Uses `PreparedStatement` for database operations.
- Demonstrates CRUD operations with MySQL.

## 🛠️ Technologies Used
- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
- File Handling and Serialization
- JDBC
- MySQL
- Visual Studio Code

## 📂 Project Structure

```text
Internship-java-week2-assignment/
├── src/
│   └── com/
│       └── weintern/
│           ├── task1/
│           ├── task2/
│           └── task3/
├── sql/
│   └── schema.sql
├── lib/
│   └── MySQL Connector/J
├── screenshots/
├── .gitignore
└── README.md
```

## ⚙️ Requirements
- Java JDK 8 or later
- MySQL Server (for Task 3)
- MySQL Connector/J (for Task 3)
- Visual Studio Code or any Java IDE

## ▶️ How to Run

### Task 1 – Student Management System
Compile:
```bash
javac -d out src/com/weintern/task1/*.java
```

Run:
```bash
java -cp out com.weintern.task1.StudentManagementApp
```

### Task 2 – Banking Management System
Compile:
```bash
javac -d out src/com/weintern/task2/*.java
```

Run:
```bash
java -cp out com.weintern.task2.BankingApplication
```

### Task 3 – JDBC Student Management System
1. Create the database and table using `sql/schema.sql`.
2. Configure your MySQL username and password in `DBConnection.java`.
3. Add the MySQL Connector/J JAR file to the `lib/` folder.

Compile:
```bash
javac -cp "lib\mysql-connector-j-<version>.jar" -d out src\com\weintern\task3\*.java
```

Run:
```bash
java -cp "out;lib\mysql-connector-j-<version>.jar" com.weintern.task3.StudentManagementApp
```

Replace `<version>` with the actual version in your JAR filename.

## 🔐 Security Note
Do not upload database passwords, API keys, or other sensitive credentials to GitHub.

## 👨‍💻 Author
GitHub: [sanketmisal2308](https://github.com/sanketmisal2308)

---

**WeIntern Java Development Internship – Week 2**
