package com.ibm.day6;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
public class LogAnalyzerTest {
    /**
     * Test valid log file
     */
    @Test
    void exec001() throws IOException {
        // Input file
        String file = "src/test/resources/exec001/server.log";
        // Run analyzer
        LogAnalyzer.main(new String[]{file});
        // Read expected summary
        String expectedFile = Files.readString(
            Path.of("src/test/resources/exec001/summary.txt")
        );
        // Read generated summary
        String actualFile = Files.readString(
            Path.of("resources/summary.txt")
        );
        // Compare outputs
        assertEquals(expectedFile, actualFile);
    }
    /**
     * Test malformed log line
     */
    @Test
    void exec002() throws IOException {
        // Input file
        String file = "src/test/resources/exec002/server.log";
        // Run analyzer
        LogAnalyzer.main(new String[]{file});
        // Read expected summary
        String expectedFile = Files.readString(
            Path.of("src/test/resources/exec002/summary.txt")
        );
        // Read generated summary
        String actualFile = Files.readString(
            Path.of("resources/summary.txt")
        );
        // Compare outputs
        assertEquals(expectedFile, actualFile);
    }
    /**
     * Test invalid log level
     */
    @Test
    void exec003() throws IOException {
        // Input file
        String file = "src/test/resources/exec003/server.log";
        // Run analyzer
        LogAnalyzer.main(new String[]{file});
        // Read expected summary
        String expectedFile = Files.readString(
            Path.of("src/test/resources/exec003/summary.txt")
        );
        // Read generated summary
        String actualFile = Files.readString(
            Path.of("resources/summary.txt")
        );
        // Compare outputs
        assertEquals(expectedFile, actualFile);
    }
    /**
     * Test missing message
     */
    @Test
    void exec004() throws IOException {
        // Input file
        String file = "src/test/resources/exec004/server.log";
        // Run analyzer
        LogAnalyzer.main(new String[]{file});
        // Read expected summary
        String expectedFile = Files.readString(
            Path.of("src/test/resources/exec004/summary.txt")
        );
        // Read generated summary
        String actualFile = Files.readString(
            Path.of("resources/summary.txt")
        );
        // Compare outputs
        assertEquals(expectedFile, actualFile);
    }
    /**
     * Test file not found
     */
    @Test
    void exec005() {
        // Invalid file path
        String file = "missing.log";
        // Check that program handles error safely
        assertDoesNotThrow(() -> {
            LogAnalyzer.main(new String[]{file});
        });
    }
    /**
     * Test multiple ERROR messages
     */
    @Test
    void exec006() throws IOException {
        // Input file
        String file = "src/test/resources/exec006/server.log";
        // Run analyzer
        LogAnalyzer.main(new String[]{file});
        // Read expected summary
        String expectedFile = Files.readString(
            Path.of("src/test/resources/exec006/summary.txt")
        );
        // Read generated summary
        String actualFile = Files.readString(
            Path.of("resources/summary.txt")
        );
        // Compare outputs
        assertEquals(expectedFile, actualFile);
    }
    /**
     * Test empty log file
     */
    @Test
    void exec007() throws IOException {
        // Input file
        String file = "src/test/resources/exec007/server.log";
        // Run analyzer
        LogAnalyzer.main(new String[]{file});
        // Read expected summary
        String expectedFile = Files.readString(
            Path.of("src/test/resources/exec007/summary.txt")
        );
        // Read generated summary
        String actualFile = Files.readString(
            Path.of("resources/summary.txt")
        );
        // Compare outputs
        assertEquals(expectedFile, actualFile);
    }
}