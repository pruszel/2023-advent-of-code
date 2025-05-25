package adventofcodesolutions;

public class Day3Solution implements AdventOfCodeSolution {

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
                    if (!isAdjacentToSymbol && isCellAdjacentToSymbol(inputAsMatrix, i, j)) {
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

    public static boolean isCellAdjacentToSymbol(char[][] grid, int row, int col) {
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
        return 0;
    }
}
