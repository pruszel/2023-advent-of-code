package adventofcodesolutions;

public class Day3Solution implements AdventOfCodeSolution {
    private static final char GEAR_SYMBOL = '*';

    public static void main(String[] args) {
        Day3Solution day3Solution = new Day3Solution();
        day3Solution.printSolution();
    }

    public void printSolution() {
        String input = AdventOfCodeSolution.readInputFile("src/main/resources/Day3Input.txt");
        System.out.println("Part 1: " + getPart1Solution(input));
        System.out.println("Part 2: " + getPart2Solution(input));
    }

    @Override
    public int getPart1Solution(String input) {
        char[][] inputAsMatrix = AdventOfCodeSolution.convertStringTo2DArray(input);
        int sumOfPartNumbers = 0;

        // Iterate through each row of the matrix
        for (int i = 0; i < inputAsMatrix.length; i++) {
            boolean isNumber = false;
            StringBuilder currentNumber = new StringBuilder();
            boolean isAdjacentToSymbol = false;

            // Iterate through each column of the matrix
            for (int j = 0; j < inputAsMatrix[i].length; j++) {
                char currentChar = inputAsMatrix[i][j];

                // If we encounter a digit, build the number
                if (Character.isDigit(currentChar)) {
                    isNumber = true;
                    currentNumber.append(currentChar);

                    // Check if this digit is adjacent to a symbol
                    if (!isAdjacentToSymbol && isDigitAdjacentToSymbol(inputAsMatrix, i, j)) {
                        isAdjacentToSymbol = true;
                    }
                }
                // If we encounter a non-digit but we were building a number
                else if (isNumber) {
                    // Add to sum if the number is adjacent to a symbol
                    if (isAdjacentToSymbol) {
                        sumOfPartNumbers += Integer.parseInt(currentNumber.toString());
                    }

                    // Reset for the next number
                    currentNumber = new StringBuilder();
                    isNumber = false;
                    isAdjacentToSymbol = false;
                }
            }

            // Handle case where a number ends at the end of a line
            if (isNumber && isAdjacentToSymbol) {
                sumOfPartNumbers += Integer.parseInt(currentNumber.toString());
            }
        }

        return sumOfPartNumbers;
    }

    public static boolean isDigitAdjacentToSymbol(char[][] grid, int row, int col) {
        // Check all 8 adjacent positions
        for (int i = Math.max(0, row - 1); i <= Math.min(grid.length - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(grid[i].length - 1, col + 1); j++) {
                // Skip the current position
                if (i == row && j == col) continue;

                char c = grid[i][j];
                // A symbol is any character that is not a digit and not a period
                if (!Character.isDigit(c) && c != '.') {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int getPart2Solution(String input) {
        char[][] inputAsMatrix = AdventOfCodeSolution.convertStringTo2DArray(input);
        int sumOfGearRatios = 0;

        // Iterate through every cell in the matrix
        for (int i = 0; i < inputAsMatrix.length; i++) {
            for (int j = 0; j < inputAsMatrix[i].length; j++) {
                // Check if the current cell is a gear
                if (isGear(inputAsMatrix, i, j)) {
                    sumOfGearRatios += getGearRatio(inputAsMatrix, i, j);
                }
            }
        }

        return sumOfGearRatios;
    }

    public static boolean isGear(char[][] grid, int searchRow, int searchCol) {
        if (debug) {
            System.out.println("Checking if position (" + searchRow + ", " + searchCol + ") is a gear.");
        }

        if (grid[searchRow][searchCol] != GEAR_SYMBOL) {
            return false;
        }

        int partNumberCount = 0;  // number = one or more digits

        // Search vertically then horizontally around the given position for adjacent part numbers
        // for example,
        // 1. search top left through top right (first row)
        // 2. search left through right (middle row)
        // 3. search bottom left through bottom right (last row)
        for (int rowIndex = Math.max(0, searchRow - 1); rowIndex <= Math.min(grid.length - 1, searchRow + 1); rowIndex++) {
            for (int colIndex = Math.max(0, searchCol - 1); colIndex <= Math.min(grid[rowIndex].length - 1, searchCol + 1); colIndex++) {
                // Skip the given position
                if (rowIndex == searchRow && colIndex == searchCol) continue;

                char c = grid[rowIndex][colIndex];
                if (Character.isDigit(c)) {
                    // Found a part number
                    partNumberCount++;

                    // Advance the column index to the right to skip over the rest of the number
                    // to avoid counting the number multiple times
                    while ((colIndex + 1) < Math.min(grid[rowIndex].length, searchCol + 1) && Character.isDigit(grid[rowIndex][colIndex + 1])) {
                        colIndex++;
                    }
                }
            }
        }

        if (debug && partNumberCount >= 2) {
            System.out.println("Found gear at (" + searchRow + ", " + searchCol + ")");
        }

        return partNumberCount >= 2;
    }

    public static int getGearRatio(char[][] grid, int gearRow, int gearCol) {
        int firstPartNumber = 0, secondPartNumber = 0;

        // Search vertically then horizontally around the gear for adjacent part numbers
        // for example,
        // 1. search top left through top right (first row)
        // 2. search left through right (middle row)
        // 3. search bottom left through bottom right (last row)
        for (int rowIndex = Math.max(0, gearRow - 1); rowIndex <= Math.min(grid.length - 1, gearRow + 1); rowIndex++) {
            for (int colIndex = Math.max(0, gearCol - 1); colIndex <= Math.min(grid[rowIndex].length - 1, gearCol + 1); colIndex++) {
                // Skip the gear itself
                if (rowIndex == gearRow && colIndex == gearCol) continue;

                char c = grid[rowIndex][colIndex];
                if (Character.isDigit(c)) {
                    // Found a digit, now we need to find the whole number it belongs to
                    int wholeNumber = getWholeNumberFromSingleDigit(grid, rowIndex, colIndex);

                    // Assign the first and second numbers based on their order of appearance
                    if (firstPartNumber == 0) {
                        firstPartNumber = wholeNumber;
                        if (debug) {
                            System.out.println("Found first part number: " + firstPartNumber + " at (" + rowIndex + ", " + colIndex + ")");
                        }
                    } else if (secondPartNumber == 0) {
                        secondPartNumber = wholeNumber;
                        if (debug) {
                            System.out.println("Found second part number: " + secondPartNumber + " at (" + rowIndex + ", " + colIndex + ")");
                        }
                        break;
                    }

                    // Advance the column to the right to skip over the rest of the number
                    while (colIndex < grid[rowIndex].length - 1 && Character.isDigit(grid[rowIndex][colIndex + 1])) {
                        colIndex++;
                    }
                }
            }
        }

        return firstPartNumber * secondPartNumber;
    }

    public static int getWholeNumberFromSingleDigit(char[][] grid, int row, int col) {
        StringBuilder digitString = new StringBuilder();
        int digit = Character.getNumericValue(grid[row][col]);
        digitString.append(digit);

        // Expand left
        int leftIndex = col - 1;
        while (leftIndex >= 0 && Character.isDigit(grid[row][leftIndex])) {
            digitString.insert(0, grid[row][leftIndex]);
            leftIndex--;
        }

        // Expand right
        int rightIndex = col + 1;
        while (rightIndex < grid[row].length && Character.isDigit(grid[row][rightIndex])) {
            digitString.append(grid[row][rightIndex]);
            rightIndex++;
        }

        return Integer.parseInt(digitString.toString());
    }
}
