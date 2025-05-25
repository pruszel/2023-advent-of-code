package adventofcodesolutions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public interface AdventOfCodeSolution {
    void printSolution();
    int getPart1Solution(String input);
    int getPart2Solution(String input);

    static String readInputFile(String fileName) {
        StringBuilder input = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input.toString();
    }

    static char[][] convertStringTo2DArray(String input) {
        String[] lines = input.strip().split("\n");
        char[][] grid = new char[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }

        return grid;
    }

}
