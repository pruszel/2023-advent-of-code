import java.util.ArrayList;

public class Day1Solution extends AdventOfCodeSolution {

    public static void main(String[] args) {
        String input = readInputFile("src/Day1Input.txt");
        ArrayList<Integer> calibrationValues = getCalibrationValues(input);
        Integer sumCalibrationValues = calibrationValues.stream().reduce(0, Integer::sum);
        System.out.println(sumCalibrationValues);
    }

    private static ArrayList<Integer> getCalibrationValues(String input) {
        ArrayList<Integer> calibrationValues = new ArrayList<Integer>();

        // Iterate through each line of the input
        String[] lines = input.split("\n");
        for (String line : lines) {
            // Check if the line contains a number
            if (line.matches(".*\\d.*")) {
                int firstNumberFromLeft = getFirstNumberFromLeft(line);
                int firstNumberFromRight = getFirstNumberFromRight(line);
                // Make integer by combining the two numbers
                int combinedNumber = Integer.parseInt(String.valueOf(firstNumberFromLeft) + String.valueOf(firstNumberFromRight));
                calibrationValues.add(combinedNumber);
            }
        }

        return calibrationValues;
    }

    private static int getFirstNumberFromLeft(String str) {
        int index = 0;
        while (str.length() > 0 && !Character.isDigit(str.charAt(index))) {
            index++;
        }
        return Integer.parseInt(str.substring(index, index + 1));
    }

    private static int getFirstNumberFromRight(String str) {
        int index = str.length() - 1;
        while (index >= 0 && !Character.isDigit(str.charAt(index))) {
            index--;
        }
        return Integer.parseInt(str.substring(index, index + 1));
    }
}
