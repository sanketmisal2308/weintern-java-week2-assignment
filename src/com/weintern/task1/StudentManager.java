package com.weintern.task1;

import java.io.*;
import java.util.*;

/**
 * Manages student records and saves them to a file.
 */
public class StudentManager {

    private final List<Student> students =
            new ArrayList<>();

    private final File dataFile;

    public StudentManager(String fileName) {
        dataFile = new File(fileName);
        load();
    }

    public List<Student> getAll() {
        return Collections.unmodifiableList(students);
    }

    // Add a new student
    public boolean add(Student student) {

        if (findById(student.getId()) != null) {
            return false;
        }

        students.add(student);
        save();

        return true;
    }

    // Search by ID
    public Student findById(int id) {

        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    // Search by name (case-insensitive)
    public List<Student> searchByName(String name) {

        List<Student> matches = new ArrayList<>();

        for (Student student : students) {

            if (student.getName()
                    .toLowerCase(Locale.ROOT)
                    .contains(name.toLowerCase(Locale.ROOT))) {

                matches.add(student);
            }
        }

        return matches;
    }

    // Update grade and subjects
    public boolean update(
            int id,
            double grade,
            List<String> subjects) {

        Student student = findById(id);

        if (student == null) {
            return false;
        }

        student.setGrade(grade);
        student.setSubjects(subjects);

        save();

        return true;
    }

    // Delete a student
    public boolean delete(int id) {

        Student student = findById(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        save();

        return true;
    }

    // Calculate average grade
    public double averageGrade() {

        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (Student student : students) {
            total += student.getGrade();
        }

        return total / students.size();
    }

    // Load saved student records
    private void load() {

        if (!dataFile.exists()) {
            return;
        }

        try (
            ObjectInputStream input =
                new ObjectInputStream(
                    new FileInputStream(dataFile)
                )
        ) {

            Object data = input.readObject();

            if (data instanceof List<?>) {

                for (Object item : (List<?>) data) {

                    if (item instanceof Student) {
                        students.add((Student) item);
                    }
                }
            }

        } catch (IOException | ClassNotFoundException ex) {

            System.err.println(
                "Could not load student data: "
                + ex.getMessage()
            );
        }
    }

    // Save student records
    private void save() {

        try (
            ObjectOutputStream output =
                new ObjectOutputStream(
                    new FileOutputStream(dataFile)
                )
        ) {

            output.writeObject(students);

        } catch (IOException ex) {

            System.err.println(
                "Could not save student data: "
                + ex.getMessage()
            );
        }
    }
}