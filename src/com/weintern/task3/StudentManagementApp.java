package com.weintern.task3;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("  WEINTERN JDBC STUDENT SYSTEM");
        System.out.println("==================================");

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;

                    case 2:
                        displayStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        updateStudent();
                        break;

                    case 5:
                        deleteStudent();
                        break;

                    case 6:
                        System.out.println(
                                "Thank you for using the application!"
                        );
                        running = false;
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Enter a number from 1 to 6."
                        );
                }

            } catch (SQLException e) {
                System.out.println("Database operation failed.");
                System.out.println("Details: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("===============================");
    }

    private static void addStudent() throws SQLException {

        System.out.println("\n--- Add Student ---");

        String name = readRequiredText("Enter student name: ");
        String email = readRequiredText("Enter email: ");
        String course = readRequiredText("Enter course: ");
        double marks = readMarks();

        Student student = new Student(name, email, course, marks);

        if (dao.addStudent(student)) {
            System.out.println("Student added successfully!");
            System.out.println("Generated Student ID: " + student.getId());
        } else {
            System.out.println("Student could not be added.");
        }
    }

    private static void displayStudents() throws SQLException {

        System.out.println("\n--- All Students ---");

        List<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudent() throws SQLException {

        System.out.println("\n--- Search Student ---");

        int id = readInt("Enter student ID: ");

        if (id <= 0) {
            System.out.println("Student ID must be positive.");
            return;
        }

        Student student = dao.getStudentById(id);

        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudent() throws SQLException {

        System.out.println("\n--- Update Student ---");

        int id = readInt("Enter student ID to update: ");

        if (id <= 0) {
            System.out.println("Student ID must be positive.");
            return;
        }

        Student existing = dao.getStudentById(id);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(existing);

        String name = readRequiredText("Enter new name: ");
        String email = readRequiredText("Enter new email: ");
        String course = readRequiredText("Enter new course: ");
        double marks = readMarks();

        Student updated = new Student(id, name, email, course, marks);

        if (dao.updateStudent(updated)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("No changes were made.");
        }
    }

    private static void deleteStudent() throws SQLException {

        System.out.println("\n--- Delete Student ---");

        int id = readInt("Enter student ID to delete: ");

        if (id <= 0) {
            System.out.println("Student ID must be positive.");
            return;
        }

        Student student = dao.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println(student);

        System.out.print("Are you sure you want to delete? (yes/no): ");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (dao.deleteStudent(id)) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student could not be deleted.");
            }
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }

    private static String readRequiredText(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("This field cannot be empty.");
        }
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

    private static double readMarks() {

        while (true) {
            System.out.print("Enter marks (0-100): ");
            String input = scanner.nextLine().trim();

            try {
                double marks = Double.parseDouble(input);

                if (Double.isFinite(marks) && marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println("Marks must be between 0 and 100.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numeric marks.");
            }
        }
    }
}