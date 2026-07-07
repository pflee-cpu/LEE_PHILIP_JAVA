package com.ibm.day7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles all database operations for the student table.
 */
public class StudentDAO {
    // "jdbc:postgresql" = use the PostgreSQL JDBC driver
    // "localhost:5432"  = PostgreSQL is on this same computer, port 5432
    // "studentdb"       = the database we created in pgAdmin
    private static final String URL = "jdbc:postgresql://localhost:5432/studentdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin123";
    /**
     * Opens and returns a connection to the database.
     */
    private Connection getConnection() throws SQLException {
        // DriverManager create connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    /**
     * Inserts a new student into the database.
     */
    public void addStudent(Student student) {
        // the SQL we want to run
        String sql = "INSERT INTO student (studentid, email, password, firstname, lastname, dateadded, dateupdated) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        // try-with-resources automatically closes the connection when done
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // fill in each ? in order (position 1, 2, 3...)
            ps.setInt(1, student.getStudentid());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPassword());
            ps.setString(4, student.getFirstname());
            ps.setString(5, student.getLastname());
            
            // Timestamp.valueOf converts LocalDateTime to SQL Timestamp
            ps.setTimestamp(6, Timestamp.valueOf(student.getDateadded()));
            ps.setTimestamp(7, Timestamp.valueOf(student.getDateupdated()));
            
            // executeUpdate() sends INSERT, UPDATE, DELETE statements
            // returns how many rows were affected
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student added successfully.");
            }
        } catch (SQLException e) {
            // print what went wrong (duplicate ID, duplicate email, etc.)
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
    /**
     * Retrieves all students from the database.
     */
    public List<Student> viewStudents() {
        // we'll collect all students into this list and return it
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        	// table of results we read row by row
             ResultSet rs = ps.executeQuery()) {
            // rs.next() moves to the next row, returns false when no more rows
            while (rs.next()) {
                // rs.getXxx("columnname") reads the value from that column
                int id = rs.getInt("studentid");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                
                // getTimestamp returns a SQL Timestamp, .toLocalDateTime() converts it
                LocalDateTime dateadded = rs.getTimestamp("dateadded").toLocalDateTime();
                LocalDateTime dateupdated = rs.getTimestamp("dateupdated").toLocalDateTime();
                
                // create a Student object from this row and add it to the list
                students.add(new Student(id, email, password, firstname, lastname, dateadded, dateupdated));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
        return students;
    }
    /**
     * Updates the password of a student by their email.
     */
    public void updatePassword(String email, String newPassword) {
        String sql = "UPDATE student SET password = ?, dateupdated = ? WHERE email = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            // update dateupdated to right now
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            // search by email instead of id
            ps.setString(3, email);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Password updated successfully.");
            } else {
                // no student found with that email
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }/**
     * Searches students by any parameter - field and value are passed in.
     * field can be: studentid, email, firstname, lastname
     */
    public List<Student> searchStudents(String field, String value) {
        List<Student> students = new ArrayList<>();
        // we build the SQL dynamically based on which field to search
        // we validate the field name ourselves to prevent SQL injection
        // since we can't use ? for column names in PreparedStatement
        String allowedFields = "studentid,email,firstname,lastname";
        if (!allowedFields.contains(field)) {
            System.out.println("Invalid search field.");
            return students;
        }
        // safe to put field directly in SQL because we validated it above
        String sql = "SELECT * FROM student WHERE " + field + " = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // all values come in as String, so we set them as String
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("studentid");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDateTime dateadded = rs.getTimestamp("dateadded").toLocalDateTime();
                LocalDateTime dateupdated = rs.getTimestamp("dateupdated").toLocalDateTime();
                students.add(new Student(id, email, password, firstname,
                                         lastname, dateadded, dateupdated));
            }
        } catch (SQLException e) {
            System.out.println("Error searching students: " + e.getMessage());
        }
        return students;
    }
    /**
     * Deletes a student from the database by their email.
     */
    public void deleteStudent(String email) {
        String sql = "DELETE FROM student WHERE email = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // search by email instead of id
            ps.setString(1, email);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                // no student found with that email
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}