package com.weintern.task3;

public class Student {

    private int id;
    private String name;
    private String email;
    private String course;
    private double marks;

    public Student(int id, String name, String email,
                   String course, double marks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public Student(String name, String email,
                   String course, double marks) {
        this(0, name, email, course, marks);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Name: %s | Email: %s | Course: %s | Marks: %.2f",
                id, name, email, course, marks
        );
    }
}