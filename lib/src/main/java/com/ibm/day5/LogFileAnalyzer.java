package com.ibm.day5;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
// Custom exception for invalid log lines
class MalformedLogEntryException extends Exception {
    private static final long serialVersionUID = 1L;

	public MalformedLogEntryException(String message) {
        super(message);
    }
}
public class LogFileAnalyzer {
    public static void main(String[] args) {
        // Store total count of each log level
        Map<String, Integer> logCount = new HashMap<>();
        // Store ERROR messages
        List<String> errorMessages = new ArrayList<>();
        // Count valid log entries
        int totalEntries = 0;
        // Variables for earliest and latest timestamps
        LocalDateTime earliest = null;
        LocalDateTime latest = null;
        // Format of timestamps in the log file
        DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss"
            );
        try (
            // Read the server log file
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/server.log")))
         { String line;
            // Read the file line by line
            while ((line = br.readLine()) != null) {
                try {
                    // Check if line format is valid
                    validateLine(line);
                    // Get timestamp from line
                    LocalDateTime timestamp =
                        LocalDateTime.parse(
                            line.substring(1, 20),
                            formatter
                        );
                    // Save earliest timestamp
                    if (earliest == null ||
                        timestamp.isBefore(earliest)) {
                        earliest = timestamp;
                    }
                    // Save latest timestamp
                    if (latest == null ||
                        timestamp.isAfter(latest)) {
                        latest = timestamp;
                    }
                    // Get log level
                    String level = line.substring(
                        line.indexOf("]") + 2,
                        line.indexOf(":", line.indexOf("]"))
                    );
                    // Count log levels
                    logCount.put(
                        level,
                        logCount.getOrDefault(level, 0) + 1
                    );
                    // Save ERROR messages
                    if (level.equals("ERROR")) {
                        errorMessages.add(
                            line.substring(
                                line.indexOf(
                                    ":",
                                    line.indexOf("]")
                                ) + 2
                            )
                        );
                    }
                    // Count valid entries
                    totalEntries++;
                } catch (MalformedLogEntryException e) {
                    System.out.println(
                        "Invalid log line: " + line
                    );
                }
            }
            // Print log counts
            System.out.println(
                "=== LOG COUNTS ==="
            );
            for (String level : logCount.keySet()) {
                System.out.println(
                    level + ": " + logCount.get(level)
                );
            }
            // Print ERROR messages
            System.out.println(
                "\n=== ERROR MESSAGES ==="
            );
            for (String msg : errorMessages) {
                System.out.println(msg);
            }
            // Print timestamps
            System.out.println(
                "\nEarliest: " + earliest
            );
            System.out.println(
                "Latest: " + latest
            );
            // Create summary.txt file
            BufferedWriter bw =
                new BufferedWriter(
                    new FileWriter("summary.txt")
                );
            bw.write("Log Summary Report");
            bw.newLine();
            bw.write("--------------------");
            bw.newLine();
            bw.write(
                "Total Entries: " + totalEntries
            );
            bw.newLine();
            bw.write(
                "INFO: " +
                logCount.getOrDefault("INFO", 0)
            );
            bw.newLine();
            bw.write(
                "WARN: " +
                logCount.getOrDefault("WARN", 0)
            );
            bw.newLine();
            bw.write(
                "ERROR: " +
                logCount.getOrDefault("ERROR", 0)
            );
            bw.newLine();
            bw.newLine();
            bw.write("Error Messages:");
            bw.newLine();
            // Write ERROR messages to summary file
            for (String msg : errorMessages) {
                bw.write("- " + msg);
                bw.newLine();
            }
            bw.newLine();
            bw.write(
                "Earliest Timestamp: " + earliest
            );
            bw.newLine();
            bw.write(
                "Latest Timestamp: " + latest
            );
            // Close writer
            bw.close();
            System.out.println(
                "\nsummary.txt created successfully."
            );
        } catch (FileNotFoundException e) {
            System.out.println(
                "File not found."
            );
        } catch (IOException e) {
            System.out.println(
                "File error: " + e.getMessage()
            );
        }
    }
    // Method to validate log lines
    public static void validateLine(String line)
    throws MalformedLogEntryException {
        // Check if line starts with [
        if (!line.startsWith("[")) {
            throw new MalformedLogEntryException(
                "Missing opening bracket"
            );
        }
        // Check if ] exists
        if (!line.contains("]")) {
            throw new MalformedLogEntryException(
                "Missing closing bracket"
            );
        }
        // Check for valid log levels
        if (!(line.contains("INFO:")
            || line.contains("WARN:")
            || line.contains("ERROR:"))) {
            throw new MalformedLogEntryException(
                "Invalid log level"
            );
        }
        // Check if colon exists
        if (!line.contains(":")) {
            throw new MalformedLogEntryException(
                "Missing colon"
            );
        }
    }
}