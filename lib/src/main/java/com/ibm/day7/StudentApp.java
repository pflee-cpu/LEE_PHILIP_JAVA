package com.ibm.day7;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
/**
 * Main application - shows the menu and handles user input.
 */
public class StudentApp {
    public static void main(String[] args) {
        // Scanner reads what the user types in the console
        Scanner scanner = new Scanner(System.in);
        
        // create one DAO object - we use this to call all database operations
        StudentDAO dao = new StudentDAO();
        
        // this controls the loop - when false, the program exits
        boolean running = true;
        while (running) {
            // print the menu every loop
            System.out.println("\n=== MENU ===");
            System.out.println("[A]dd");
            System.out.println("[V]iew");
            System.out.println("[U]pdate Password");
            System.out.println("[D]elete");
            System.out.println("[Q]uit");
            System.out.print("Enter choice: ");
            
            // read what the user typed, trim spaces, convert to uppercase
            String choice = scanner.nextLine().trim().toUpperCase();
            
            // check what the user picked
            switch (choice) {
                case "A":
                    // ask for each piece of student info one by one
                    System.out.print("Enter Student ID: ");
                    int id = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine().trim();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine().trim();
                    System.out.print("Enter First Name: ");
                    String firstname = scanner.nextLine().trim();
                    System.out.print("Enter Last Name: ");
                    String lastname = scanner.nextLine().trim();
                    
                    // dateadded and dateupdated are set to right now automatically
                    LocalDateTime now = LocalDateTime.now();
                    
                    // create a Student object with the info we collected
                    Student student = new Student(id, email, password, firstname, lastname, now, now);
                    
                    // send it to the database
                    dao.addStudent(student);
                    break;
                    
                case "V":
                    System.out.println("Search by:");
                    System.out.println("[1] View All");
                    System.out.println("[2] Student ID");
                    System.out.println("[3] Email");
                    System.out.println("[4] First Name");
                    System.out.println("[5] Last Name");
                    System.out.print("Enter choice: ");
                    String viewChoice = scanner.nextLine().trim();
                    if (viewChoice.equals("1")) {
                        // view all - no filter
                        List<Student> all = dao.viewStudents();
                        if (all.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student s : all) {
                                System.out.println(s);
                            }
                        }
                    } else if (viewChoice.equals("2")) {
                        System.out.print("Enter Student ID: ");
                        String searchId = scanner.nextLine().trim();
                        List<Student> results = dao.searchStudents("studentid", searchId);
                        if (results.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student s : results) {
                                System.out.println(s);
                            }
                        }
                    } else if (viewChoice.equals("3")) {
                        System.out.print("Enter Email: ");
                        String searchEmail = scanner.nextLine().trim();
                        List<Student> results = dao.searchStudents("email", searchEmail);
                        if (results.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student s : results) {
                                System.out.println(s);
                            }
                        }
                    } else if (viewChoice.equals("4")) {
                        System.out.print("Enter First Name: ");
                        String searchFirst = scanner.nextLine().trim();
                        List<Student> results = dao.searchStudents("firstname", searchFirst);
                        if (results.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student s : results) {
                                System.out.println(s);
                            }
                        }
                    } else if (viewChoice.equals("5")) {
                        System.out.print("Enter Last Name: ");
                        String searchLast = scanner.nextLine().trim();
                        List<Student> results = dao.searchStudents("lastname", searchLast);
                        if (results.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student s : results) {
                                System.out.println(s);
                            }
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                    
                case "U":
                    System.out.print("Enter Email to update: ");
                    String updateEmail = scanner.nextLine().trim();
                    System.out.print("Enter New Password: ");
                    String newPassword = scanner.nextLine().trim();
                    System.out.print("Confirm New Password: ");
                    String confirmPassword = scanner.nextLine().trim();
                    // check if both passwords match before sending to database
                    if (!newPassword.equals(confirmPassword)) {
                        System.out.println("Passwords do not match. Update cancelled.");
                        break;
                    }
                    dao.updatePassword(updateEmail, newPassword);
                    break;
                    
                case "D":
                    System.out.print("Enter Email to delete: ");
                    String deleteEmail = scanner.nextLine().trim();
                    dao.deleteStudent(deleteEmail);
                    break;
                    
                case "Q":
                    System.out.println("Goodbye!");
                    // set running to false so the while loop stops
                    running = false;
                    break;
                    
                default:
                    // anything else that isn't A, V, U, D, or Q
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        // close the scanner when the program ends
        scanner.close();
    }
}