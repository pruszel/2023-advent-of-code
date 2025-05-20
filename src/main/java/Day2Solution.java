import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day2Solution extends AdventOfCodeSolution {
    private static final HashMap<String, Integer> colorToLimit = new HashMap<>(
            Map.of(
                    "red", 12,
                    "green", 13,
                    "blue", 14
            )
    );

    public static void main(String[] args) {
        String input = readInputFile("src/main/resources/Day2Input.txt");
        ArrayList<Game> validGames = new ArrayList<>();

        // For Part 2, track the sum of powers of cubes revealed
        int sumOfPowers = 0;

        // Iterate through each game represented by a line of input
        String[] lines = input.split("\n");
        for (String line : lines) {
            // If true, add the game to set the set of valid games
            boolean isValid = true;

            // For Part 2, track the maximum number of cubes revealed for each color
            HashMap<String, Integer> colorToMax = new HashMap<>(
                    Map.of(
                            "red", 1,
                            "green", 1,
                            "blue", 1
                    )
            );

            // first, chop off first part with Game ID
            // e.g. "Game 1: 3 blue, 4 red; 2 green" -> "3 blue, 4 red; 2 green"
            String cubesPart = line.split(":")[1].trim();

            // Get list of cube sets revealed denoted by semicolon
            // e.g. "3 blue, 4 red; 2 green" -> ["3 blue, 4 red", "2 green"]
            String[] listOfCubeSets = cubesPart.split(";");

            // Check each cube set to determine if the game is valid
            for (String cubeSet : listOfCubeSets) {

                // Get each number of cubes revealed for each color
                // e.g. "3 blue, 4 red" -> ["3 blue", "4 red"]
                String[] cubes = cubeSet.split(",");

                // Determine if game is valid by checking if each number of cubes exceeds the limit for that color
                for (String cube : cubes) {
                    String color = cube.trim().split(" ")[1];
                    int number = Integer.parseInt(cube.trim().split(" ")[0]);

                    // Update the maximum number of cubes revealed for each color
                    if (number > colorToMax.get(color)) {
                        colorToMax.put(color, number);
                    }

                    // Check if the number of cubes revealed exceeds the limit for that color
                    if (number > colorToLimit.get(color)) {
                        isValid = false;
                    }
                }
            }


            if (isValid) {
                // Game ID is between "Game " and ":"
                int gameId = Integer.parseInt(line.split(":")[0].split(" ")[1]);
                Game game = new Game(gameId);
                validGames.add(game);
            }

            int power = colorToMax.get("red") * colorToMax.get("green") * colorToMax.get("blue");
            sumOfPowers += power;
        }

        int gameIdSum = validGames.stream().reduce(0, (sum, game) -> sum + game.id, Integer::sum);
        System.out.println("Part 1 answer: " + gameIdSum);

        System.out.println("Part 2 answer: " + sumOfPowers);
    }
}

class Game {
    public final int id;

    public Game(int id) {
        this.id = id;
    }
}
