import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day2SolutionTest {

    @Test
    void mainPrintsCorrectSumForPart1() {
        // Save the original System.out
        PrintStream originalOut = System.out;

        try {
            // Set up a new output stream to capture printed output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            Day2Solution.main(new String[]{});

            String printedOutput = outputStream.toString().trim();
            assertTrue(printedOutput.contains("1734"));
        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    void mainPrintsCorrectSumForPart2() {
        // Save the original System.out
        PrintStream originalOut = System.out;

        try {
            // Set up a new output stream to capture printed output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            Day2Solution.main(new String[]{});

            String printedOutput = outputStream.toString().trim();
            assertTrue(printedOutput.contains("70387"));
        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }
    }
}
