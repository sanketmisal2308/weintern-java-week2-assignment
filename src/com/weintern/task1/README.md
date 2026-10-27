# WeIntern Java Development – Week 2

## Task 1: Student Management System

### Project Description
A console-based Java application used to manage student records. It demonstrates Object-Oriented Programming, ArrayList, exception handling, input validation, and file serialization.

### Technologies Used
- Java 11 or later
- VS Code
- ArrayList
- Scanner
- Java Serialization

### Features
1. Add a student
2. Display all students
3. Search by student ID
4. Search by student name
5. Update grade and subjects
6. Delete a student with confirmation
7. Calculate class average grade
8. Save and load records using a file
9. Validate student input

### Project Structure
```text
src/
└── com/
    └── weintern/
        └── task1/
            ├── Student.java
            ├── StudentManager.java
            └── StudentManagementApp.java
```

### How to Compile
Open the terminal in the project root and run:

```powershell
javac -d out src/com/weintern/task1/*.java
```

### How to Run
```powershell
java -cp out com.weintern.task1.StudentManagementApp
```

### Data Storage
Student records are saved in `students.dat` in the program's working directory.

### Testing
Test student creation, display, searching, updating, deletion, average calculation, invalid inputs, and persistence after restarting the application.

### Author
WeIntern Java Development Intern