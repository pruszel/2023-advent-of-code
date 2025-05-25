package pruszel.adventofcodesolutions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractAdventOfCodeSolutionTest {

    protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;
    protected String printedOutput;

    @BeforeEach
    public void setUpOutput() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreOutput() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    // Method to be implemented by day-specific test classes
    protected abstract void runSolution();
    public abstract void shouldPrintTheCorrectSumForPart1();
    public abstract void shouldPrintTheCorrectSumForPart2();
}
