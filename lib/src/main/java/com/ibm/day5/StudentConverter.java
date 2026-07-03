package com.ibm.day5;
import java.io.*;
public class StudentConverter {
    public static void main(String[] args) {
        try (
            // Read the CSV file
            BufferedReader br = new BufferedReader(new FileReader("student.csv"));
            // Create and write to JSON file
            BufferedWriter bw = new BufferedWriter(new FileWriter("student.json"))
        ) {
            // Start JSON array
            bw.write("[");
            bw.newLine();
            String line;
            // Used to prevent extra comma
            boolean first = true;
            // Read the file line by line
            while ((line = br.readLine()) != null) {
                // Split the CSV line into parts
                String[] data = line.split(",");
                // Add comma before next object
                if (!first) {
                    bw.write(",");
                    bw.newLine();
                }
                // Write JSON object
                bw.write("  {");
                bw.newLine();
                bw.write("    \"id\": \"" + data[0].replace("\"", "") + "\",");
                bw.newLine();
                // Remove extra quotes from CSV values
                bw.write("    \"name\": \"" + data[1].replace("\"", "") + "\",");
                bw.newLine();
                bw.write("    \"course\": \"" + data[2].replace("\"", "") + "\"");
                bw.newLine();
                bw.write("  }");
                // After first object
                first = false;
            }
            // End JSON array
            bw.newLine();
            bw.write("]");
            System.out.println("JSON file created successfully.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}