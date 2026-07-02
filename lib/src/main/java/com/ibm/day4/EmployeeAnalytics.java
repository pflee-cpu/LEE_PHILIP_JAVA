package com.ibm.day4;
import java.util.*;

// Class for employee objects
class Employee {
    // Employee information
    private String name;
    private String department;
    private double salary;
    // Constructor
    public Employee(
        String name,
        String department,
        double salary
    ) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    // Getter for name
    public String getName() {
        return name;
    }
    // Getter for department
    public String getDepartment() {
        return department;
    }
    // Getter for salary
    public double getSalary() {
        return salary;
    }
    // Prints employee details nicely
    @Override
    public String toString() {
        return name + " - "
            + department + " - "
            + salary;
    }
}
public class EmployeeAnalytics {
    public static void main(String[] args) {
        // Make a list for employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 52000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 50000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name
        // Print all employees
        System.out.println( "=== EMPLOYEE LIST ===");
        for (Employee e : employees) {
            System.out.println(e);
        }
        // Store unique names only
        Set<String> uniqueNames =
            new HashSet<>();
        // New list for no duplicate names
        // List<Employee> uniqueEmployees =
            new ArrayList<>();
        // Check employees one by one
        for (Employee e : employees) {
            uniqueNames.add(
                    e.getName()
                );
            // If name is not yet saved
            // if (!uniqueNames.contains(
            //     e.getName()
            // )) {
            //     // Save the name
            //     uniqueNames.add(
            //         e.getName()
            //     );
            //     // Add employee to new list
            //     uniqueEmployees.add(e);
            // }
        }
        // Print employees with unique names
        System.out.println( "\n=== UNIQUE EMPLOYEES ===" );
        for (String name : uniqueNames) {
            System.out.println(name);
        }
        // Group employees by department
        Map<String, List<Employee>>
            departmentMap =
                new HashMap<>();
        // Loop through all employees
        for (Employee e : employees) {
            // Get employee department
            String dept = e.getDepartment();
            // If department is not in map yet
            if (!departmentMap.containsKey( dept)) {
                // Create new list for department
                departmentMap.put( dept, new ArrayList<>());
            }
            // Add employee to department
            departmentMap.get(dept).add(e);
        }
        // Print employees by department
        System.out.println( "\n=== EMPLOYEES BY DEPARTMENT ===" );
        
        for (String dept : departmentMap.keySet()) {
            System.out.println( dept + ":");
            // Print employees in that department
            for (Employee e : departmentMap.get(dept)) {
                System.out.println( "- " + e);                
            }
        }
        // Find highest paid employee in each department
        System.out.println(
            "\n=== HIGHEST PAID EMPLOYEE ==="
        );
        // Check every department
        for (String dept :
            departmentMap.keySet()) {
            // First employee is temporary highest
            Employee highest =
                departmentMap
                    .get(dept)
                    .get(0);
            // Compare salaries
            for (Employee e :
                departmentMap.get(dept)) {
                // If salary is bigger
                if (e.getSalary() >
                    highest.getSalary()) {
                    // Replace highest employee
                    highest = e;
                }
            }
            // Print highest employee
            System.out.println(
                dept + ": " + highest
            );
        }
        // Sort employees by salary from highest to lowest
        employees.sort((a, b) ->
            Double.compare(
                b.getSalary(),
                a.getSalary()
            )
        );
        // Print sorted employees
        System.out.println( "\n=== SORTED BY SALARY ===" );
        for (Employee e : employees) {
            System.out.println(e);
        }
        // TreeSet removes duplicates
        // and sorts automatically
        Set<Double> salaries = new TreeSet<>();
        // Add salaries to set
            for (Employee e : employees) {
                salaries.add(
                    e.getSalary()
                );
        }
        // Print unique salaries
        System.out.println(  "\n=== UNIQUE SALARIES ===");
        // System.out.println(salaries);
        salaries.forEach(s -> System.out.println(s));
    }
}