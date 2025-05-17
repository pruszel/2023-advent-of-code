import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventOfCodeSolution {

    public static String readInputFile(String fileName) {
        StringBuilder input = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input.toString();
    }
}
