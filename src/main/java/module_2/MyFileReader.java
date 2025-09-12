package module_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class MyFileReader {

    public static void greetings() {
        System.out.println("Hello! This program can read and write files.");
        System.out.println("Current working directory is " + "\"" + System.getProperty("user.dir") + "\"");
        System.out.println("To continue, please choose a mode and file path (e.g., 1 example.txt or 2 example.txt).");
        System.out.println("To quit press \"q\" ...");
    }

    public static void printHints() {
        System.out.println("\nPlease enter a mode and file path.");
        System.out.println("To quit press \"q\" ...");
    }

    public static void readTheFile(String path) {
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

    public static boolean writeTheFile(String path, String dataToWrite) {
        try (BufferedWriter buffer = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            buffer.write(dataToWrite);

            return true;
        } catch (IOException e) {
            System.err.println("Failed to write file: \"" + path + "\" ...\n");
            e.printStackTrace();

            return false;
        }
    }

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        greetings();

        while (true) {
            String input = console.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("\nQuit program... Bye!");
                break;
            }

            String inputData[] = input.split(" ");

            if (inputData.length < 2) {
                System.err.println("\nIncorrect input! Please provide mode and file path ...");
                printHints();
                continue;
            }

            int mode;

            try {
                mode = Integer.parseInt(inputData[0]);
            } catch (NumberFormatException e) {
                System.err.println("\nWrong mode! Hint: mode must be a number 1 or 2 ...");
                printHints();
                continue;
            }

            String path = inputData[1];

            if (mode == 1) {
                System.out.println("\nReading data from file: " + "\"" + path + "\" ...\n");
                readTheFile(path);
            } else if (mode == 2) {
                System.out.println("Enter data to write to file ...");
                System.out.println("Type \"q\" to finish.\n");

                StringBuilder builder = new StringBuilder();

                while (true) {
                    String line = console.nextLine();

                    if (line.equalsIgnoreCase("q")) {
                        break;
                    }

                    builder.append(line).append(System.lineSeparator());
                }

                if (writeTheFile(path, builder.toString())) {
                    System.out.println("\nData successfully written to \"" + path + "\" ...");
                }

            } else {
                System.err.println("\nWrong mode! Hint: mode must be a number 1 or 2 ...");
                printHints();
                continue;
            }

            printHints();
        }

        console.close();
    }
}