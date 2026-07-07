package com.ibm.day7;
import java.time.LocalDateTime;
/**
 * Represents a student record in the database.
 */
public class Student {
    // these match exactly the column names in our student table
    private int studentid;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDateTime dateadded;
    private LocalDateTime dateupdated;
    /**
     * Constructor to create a Student object with all fields.
     */
    public Student(int studentid, String email, String password,
                   String firstname, String lastname,
                   LocalDateTime dateadded, LocalDateTime dateupdated) {
        this.studentid = studentid;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateadded = dateadded;
        this.dateupdated = dateupdated;
    }
    // getters 
    public int getStudentid() { return studentid; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public LocalDateTime getDateadded() { return dateadded; }
    public LocalDateTime getDateupdated() { return dateupdated; }
    
    // setters 
    public void setStudentid(int studentid) { this.studentid = studentid; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setDateadded(LocalDateTime dateadded) { this.dateadded = dateadded; }
    public void setDateupdated(LocalDateTime dateupdated) { this.dateupdated = dateupdated; }
    /**
     * Returns a readable string of the student's info.
     */
    @Override
    public String toString() {
        return "ID: " + studentid +
               " | Name: " + firstname + " " + lastname +
               " | Email: " + email +
               " | Added: " + dateadded +
               " | Last updated: " + dateupdated;
    }
}