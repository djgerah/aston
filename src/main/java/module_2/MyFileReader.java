package module_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class MyFileReader {
    public static void main(String[] args) {
        runProgram();
    }

    public static void greetings() {
        System.out.println("Hello! This program can read and write files.");
        System.out.println("Current working directory is " + "\"" + System.getProperty("user.dir") + "\"");
        System.out.println("To continue, choose a mode:");
        System.out.println("1 - Read file, 2 - Append to file, 3 - Overwrite file");
        System.out.println("Enter mode followed by file path, e.g., 2 example.txt");
        System.out.println("To quit press \"q\" ...");
    }

    public static void printHints() {
        System.out.println("\nPlease enter a mode and file path.");
        System.out.println("To quit press \"q\" ...");
    }

    public static void runProgram() {
        Scanner console = new Scanner(System.in);

        greetings();

        while (true) {
            String input = console.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("\nQuit program... Bye!");
                break;
            }

            String[] inputData = input.split("\\s+");

            if (inputData.length < 2) {
                System.err.println("\nIncorrect input! Please provide mode and file path ...");
                printHints();
                continue;
            }

            int mode;

            try {
                mode = Integer.parseInt(inputData[0]);
            } catch (NumberFormatException e) {
                System.err.println("\nWrong mode! Hint: mode must be a number 1, 2 or 3 ...");
                printHints();
                continue;
            }

            String path = inputData[1];

            if (mode == 1) {
                readTheFile(path);
            } else if (mode == 2) {
                appendToFile(console, path, 2);
            } else if (mode == 3) {
                appendToFile(console, path, 3);
            } else {
                System.err.println("\nWrong mode! Hint: mode must be a number 1, 2 or 3 ...");
                printHints();
                continue;
            }

            printHints();
        }

        console.close();
    }

    public static void readTheFile(String path) {
        System.out.println("\nReading data from file: " + "\"" + path + "\" ...\n");

        try (BufferedReader buffer = Files.newBufferedReader(Paths.get(path))) {
            String line;
            System.out.println("\n --- Start --- \n");

            while ((line = buffer.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("\n ---  End  --- \n");
        } catch (IOException e) {
            System.err.println("Failed to read file: \"" + path + "\" ...\n");
            e.printStackTrace();
        }
    }

    public static boolean writeTheFile(String path, String dataToWrite, int mode) {
        StandardOpenOption option = (mode == 2) ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;

        try (BufferedWriter buffer = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.CREATE, option)) {
            buffer.write(dataToWrite);

            return true;
        } catch (IOException e) {
            System.err.println("Failed to write file: \"" + path + "\" ...\n");
            e.printStackTrace();

            return false;
        }
    }

    public static void appendToFile(Scanner console, String path, int mode) {
        System.out.println("Enter data to write to file ...");
        System.out.println("Type \"q\" to finish.\n");

        StringBuilder builder = new StringBuilder();

        if (mode == 2 && Files.exists(Paths.get(path))) {
            builder.append(System.lineSeparator());
        }

        while (true) {
            String line = console.nextLine();

            if (line.equalsIgnoreCase("q")) {
                break;
            }

            builder.append(line).append(System.lineSeparator());
        }

        if (writeTheFile(path, builder.toString(), mode)) {
            String action = (mode == 2) ? "appended" : "overwritten";
            System.out.println("\nData successfully " + action + " to \"" + path + "\" ...");
        }
    }
}