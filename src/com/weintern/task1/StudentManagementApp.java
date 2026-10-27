package com.weintern.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Student Management System.
 */
public class StudentManagementApp {

    private static final Scanner SCANNER =
            new Scanner(System.in);

    private static final StudentManager MANAGER =
            new StudentManager("students.dat");

    public static void main(String[] args) {

        while (true) {

            System.out.println(
                "\n=================================="
            );
            System.out.println(
                "     STUDENT MANAGEMENT SYSTEM"
            );
            System.out.println(
                "=================================="
            );

            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. Calculate Class Average");
            System.out.println("0. Exit");

            int choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        displayAll();
                        break;

                    case 3:
                        searchById();
                        break;

                    case 4:
                        searchByName();
                        break;

                    case 5:
                        updateStudent();
                        break;

                    case 6:
                        deleteStudent();
                        break;

                    case 7:
                        calculateAverage();
                        break;

                    case 0:
                        System.out.println(
                            "Thank you for using the system!"
                        );
                        SCANNER.close();
                        return;

                    default:
                        System.out.println(
                            "Invalid choice. Please try again."
                        );
                }

            } catch (IllegalArgumentException ex) {

                System.out.println(
                    "Invalid input: " + ex.getMessage()
                );
            }
        }
    }

    // 1. Add Student
    private static void addStudent() {

        System.out.println("\n--- Add Student ---");

        int id = readInt("Enter Student ID: ");

        String name = readText("Enter Student Name: ");

        int age = readInt("Enter Student Age: ");

        double grade = readDouble(
            "Enter Grade (0-100): "
        );

        List<String> subjects = readSubjects();

        Student student =
            new Student(id, name, age, grade, subjects);

        if (MANAGER.add(student)) {

            System.out.println(
                "Student added successfully!"
            );

        } else {

            System.out.println(
                "Error: Student ID already exists."
            );
        }
    }

    // 2. Display All Students
    private static void displayAll() {

        System.out.println("\n--- All Students ---");

        List<Student> students = MANAGER.getAll();

        if (students.isEmpty()) {

            System.out.println(
                "No student records found."
            );
            return;
        }

        System.out.printf(
            "%-6s %-22s %-5s %-8s %s%n",
            "ID",
            "NAME",
            "AGE",
            "GRADE",
            "SUBJECTS"
        );

        System.out.println(
            "--------------------------------------------------------------------------"
        );

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // 3. Search by ID
    private static void searchById() {

        System.out.println("\n--- Search by ID ---");

        int id = readInt("Enter Student ID: ");

        Student student = MANAGER.findById(id);

        if (student == null) {

            System.out.println("Student not found.");

        } else {

            System.out.println(student);
        }
    }

    // 4. Search by Name
    private static void searchByName() {

        System.out.println("\n--- Search by Name ---");

        String name = readText(
            "Enter student name to search: "
        );

        List<Student> results =
            MANAGER.searchByName(name);

        if (results.isEmpty()) {

            System.out.println(
                "No matching student found."
            );

        } else {

            for (Student student : results) {
                System.out.println(student);
            }
        }
    }

    // 5. Update Student
    private static void updateStudent() {

        System.out.println("\n--- Update Student ---");

        int id = readInt(
            "Enter Student ID to update: "
        );

        Student existing = MANAGER.findById(id);

        if (existing == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println(
            "Current record: " + existing
        );

        double grade = readDouble(
            "Enter new grade (0-100): "
        );

        List<String> subjects = readSubjects();

        boolean updated =
            MANAGER.update(id, grade, subjects);

        if (updated) {

            System.out.println(
                "Student updated successfully!"
            );
        }
    }

    // 6. Delete Student
    private static void deleteStudent() {

        System.out.println("\n--- Delete Student ---");

        int id = readInt(
            "Enter Student ID to delete: "
        );

        Student student = MANAGER.findById(id);

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println(
            "Student: " + student.getName()
        );

        String confirm = readText(
            "Are you sure? (yes/no): "
        );

        if (confirm.equalsIgnoreCase("yes")) {

            if (MANAGER.delete(id)) {

                System.out.println(
                    "Student deleted successfully!"
                );
            }

        } else {

            System.out.println("Delete cancelled.");
        }
    }

    // 7. Calculate Average
    private static void calculateAverage() {

        System.out.println(
            "\n--- Class Average Grade ---"
        );

        if (MANAGER.getAll().isEmpty()) {

            System.out.println(
                "No students available to calculate average."
            );
            return;
        }

        System.out.printf(
            "Class Average Grade: %.2f%n",
            MANAGER.averageGrade()
        );
    }

    // Read comma-separated subjects
    private static List<String> readSubjects() {

        String input = readText(
            "Enter subjects separated by commas: "
        );

        List<String> subjects = new ArrayList<>();

        for (String subject : input.split(",")) {

            if (!subject.trim().isEmpty()) {
                subjects.add(subject.trim());
            }
        }

        return subjects;
    }

    // Read text
    private static String readText(String prompt) {

        System.out.print(prompt);

        return SCANNER.nextLine().trim();
    }

    // Read integer safely
    private static int readInt(String prompt) {

        while (true) {

            try {

                return Integer.parseInt(
                    readText(prompt)
                );

            } catch (NumberFormatException ex) {

                System.out.println(
                    "Please enter a valid whole number."
                );
            }
        }
    }

    // Read decimal number safely
    private static double readDouble(String prompt) {

        while (true) {

            try {

                return Double.parseDouble(
                    readText(prompt)
                );

            } catch (NumberFormatException ex) {

                System.out.println(
                    "Please enter a valid number."
                );
            }
        }
    }
}