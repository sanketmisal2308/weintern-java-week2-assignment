package com.weintern.task1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student record.
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private String name;
    private int age;
    private double grade;
    private List<String> subjects;

    public Student(int id, String name, int age,
                   double grade, List<String> subjects) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "Student ID must be positive."
            );
        }

        this.id = id;
        setName(name);
        setAge(age);
        setGrade(grade);
        setSubjects(subjects);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGrade() {
        return grade;
    }

    public List<String> getSubjects() {
        return new ArrayList<>(subjects);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Name cannot be empty."
            );
        }

        this.name = name.trim();
    }

    public void setAge(int age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException(
                "Age must be between 1 and 120."
            );
        }

        this.age = age;
    }

    public void setGrade(double grade) {
        if (!Double.isFinite(grade)
                || grade < 0 || grade > 100) {
            throw new IllegalArgumentException(
                "Grade must be between 0 and 100."
            );
        }

        this.grade = grade;
    }

    public void setSubjects(List<String> subjects) {
        if (subjects == null) {
            throw new IllegalArgumentException(
                "Subjects cannot be null."
            );
        }

        this.subjects = new ArrayList<>();

        for (String subject : subjects) {
            if (subject != null
                    && !subject.trim().isEmpty()) {
                this.subjects.add(subject.trim());
            }
        }
    }

    @Override
    public String toString() {
        return String.format(
            "%-6d %-22s %-5d %-8.2f %s",
            id,
            name,
            age,
            grade,
            String.join(", ", subjects)
        );
    }
}