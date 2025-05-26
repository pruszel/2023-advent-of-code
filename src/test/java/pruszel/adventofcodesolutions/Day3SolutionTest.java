package pruszel.adventofcodesolutions;

import adventofcodesolutions.Day3Solution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day3SolutionTest extends AbstractAdventOfCodeSolutionTest {

    @Override
    public void runSolution() {
        Day3Solution.main(new String[]{});
    }

    @Override
    @Test
    public void shouldPrintTheCorrectSumForPart1() {
        runSolution();
        printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("550934"), "Part 1 output is incorrect for input given by AoC website.");
    }

    @Override
    @Test
    public void shouldPrintTheCorrectSumForPart2() {
        runSolution();
        printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("81997870"), "Part 2 output is incorrect for input given by AoC website.");
    }
}
