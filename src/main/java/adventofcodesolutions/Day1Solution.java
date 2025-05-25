package adventofcodesolutions;

import java.util.ArrayList;
import java.util.Map;

public class Day1Solution {
    private static final Map<String, Integer> numericWordsToDigits = Map.of(
            "zero", 0,
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    public static void main(String[] args) {
        String input = AdventOfCodeSolution.readInputFile("src/main/resources/Day1Input.txt");

        // Part 1
        ArrayList<Integer> calibrationValues = getCalibrationValuesUsingDigitsOnly(input);
        Integer sumCalibrationValues = calibrationValues.stream().reduce(0, Integer::sum);
        System.out.println(sumCalibrationValues);

        // Part 2
        ArrayList<Integer> calibrationValuesUsingDigitsAndWords = getCalibrationValuesUsingDigitsAndWords(input);
        Integer sumCalibrationValuesUsingDigitsAndWords = calibrationValuesUsingDigitsAndWords.stream().reduce(0, Integer::sum);
        System.out.println(sumCalibrationValuesUsingDigitsAndWords);
    }

    private static ArrayList<Integer> getCalibrationValuesUsingDigitsOnly(String input) {
        ArrayList<Integer> calibrationValues = new ArrayList<>();

        // Iterate through each line of the input
        String[] lines = input.split("\n");
        for (String line : lines) {
            // Check if the line contains a number
            if (line.matches(".*\\d.*")) {
                IndexAndInteger firstDigitFromLeft = getFirstDigitFromLeft(line);
                IndexAndInteger firstDigitFromRight = getFirstDigitFromRight(line);
                // Make integer by combining the two numbers
                int combinedNumber = firstDigitFromLeft.integer() * 10 + firstDigitFromRight.integer();
                calibrationValues.add(combinedNumber);
            }
        }

        return calibrationValues;
    }

    private static ArrayList<Integer> getCalibrationValuesUsingDigitsAndWords(String input) {
        ArrayList<Integer> calibrationValues = new ArrayList<>();

        // Iterate through each line of the input
        String[] lines = input.split("\n");
        for (String line : lines) {
            IndexAndInteger firstNumericWordFromLeft = getFirstNumericWordFromLeft(line);
            IndexAndInteger firstNumericWordFromRight = getFirstNumericWordFromRight(line);

            IndexAndInteger firstDigitFromLeft = getFirstDigitFromLeft(line);
            IndexAndInteger firstDigitFromRight = getFirstDigitFromRight(line);

            IndexAndInteger firstNumberFromLeft;
            boolean wordComesBeforeDigit = firstNumericWordFromLeft != null && firstDigitFromLeft != null && firstNumericWordFromLeft.index() < firstDigitFromLeft.index();
            if (wordComesBeforeDigit) {
                firstNumberFromLeft = firstNumericWordFromLeft;
            } else if(firstDigitFromLeft != null) {
                firstNumberFromLeft = firstDigitFromLeft;
            } else {
                continue;
            }

            IndexAndInteger firstNumberFromRight;
            boolean wordComesAfterDigit = firstNumericWordFromRight != null && firstDigitFromRight != null && firstNumericWordFromRight.index() > firstDigitFromRight.index();
            if (wordComesAfterDigit) {
                firstNumberFromRight = firstNumericWordFromRight;
            } else if(firstDigitFromRight != null) {
                firstNumberFromRight = firstDigitFromRight;
            } else {
                continue;
            }

            // Make integer by combining the two numbers
            int combinedNumber = firstNumberFromLeft.integer() * 10 + firstNumberFromRight.integer();
            calibrationValues.add(combinedNumber);
        }

        return calibrationValues;
    }

    private static IndexAndInteger getFirstDigitFromLeft(String str) {
        int index = 0;
        while (!str.isEmpty() && !Character.isDigit(str.charAt(index))) {
            index++;
        }
        return new IndexAndInteger(index, Integer.parseInt(str.substring(index, index + 1)));
    }

    private static IndexAndInteger getFirstDigitFromRight(String str) {
        int index = str.length() - 1;
        while (index >= 0 && !Character.isDigit(str.charAt(index))) {
            index--;
        }
        return new IndexAndInteger(index, Integer.parseInt(str.substring(index, index + 1)));
    }

    private static IndexAndInteger getFirstNumericWordFromLeft(String str) {
        IndexAndInteger first = null;
        for (Map.Entry<String, Integer> entry : numericWordsToDigits.entrySet()) {
            String word = entry.getKey();
            int index = str.indexOf(word);
            if (index != -1 && (first == null || index < first.index())) {
                first = new IndexAndInteger(index, entry.getValue());
            }
        }
        return first;
    }

    /**
     * @param str String to search for numeric words
     * @return pruszel.adventofcodesolutions.IndexAndInteger object or null if no numeric word is found
     */
    private static IndexAndInteger getFirstNumericWordFromRight(String str) {
        IndexAndInteger first = null;
        for (Map.Entry<String, Integer> entry : numericWordsToDigits.entrySet()) {
            String word = entry.getKey();
            int index = str.lastIndexOf(word);
            if (index != -1 && (first == null || index > first.index())) {
                first = new IndexAndInteger(index, entry.getValue());
            }
        }
        return first;
    }

}

record IndexAndInteger(int index, int integer) {}
