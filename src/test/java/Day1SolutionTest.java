import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Day1SolutionTest {

    @Test
    void mainPrintsCorrectSum() {
        // Save the original System.out
        PrintStream originalOut = System.out;

        try {
            // Set up a new output stream to capture printed output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            Day1Solution.main(new String[]{});

            String printedOutput = outputStream.toString().trim();
            assertTrue(printedOutput.contains("53974"));
        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }
    }
}
