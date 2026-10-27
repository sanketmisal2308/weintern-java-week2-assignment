package com.weintern.task3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE: Insert a student
    public boolean addStudent(Student student) throws SQLException {

        String sql = "INSERT INTO students (name, email, course, marks) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setDouble(4, student.getMarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        student.setId(keys.getInt(1));
                    }
                }
                return true;
            }

            return false;
        }
    }

    // READ: Display all students
    public List<Student> getAllStudents() throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT id, name, email, course, marks "
                   + "FROM students ORDER BY id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getDouble("marks")
                );

                students.add(student);
            }
        }

        return students;
    }

    // READ: Find a student by ID
    public Student getStudentById(int id) throws SQLException {

        String sql = "SELECT id, name, email, course, marks "
                   + "FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("course"),
                            rs.getDouble("marks")
                    );
                }
            }
        }

        return null;
    }

    // UPDATE: Modify a student's details
    public boolean updateStudent(Student student) throws SQLException {

        String sql = "UPDATE students SET name = ?, email = ?, "
                   + "course = ?, marks = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setDouble(4, student.getMarks());
            ps.setInt(5, student.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE: Remove a student by ID
    public boolean deleteStudent(int id) throws SQLException {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}