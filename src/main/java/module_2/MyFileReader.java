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

    public static void runProgram() {
        Scanner console = new Scanner(System.in);

        greetings();

        while (true) {
            String input = console.nextLine().trim();

            if (input.equalsIgnoreCase("-q")) {
                quiteProg();
                break;
            }

            if (input.equalsIgnoreCase("--help") || input.equalsIgnoreCase("-h")) {
                help();
                continue;
            }

            String[] inputData = input.split("\\s+");

            if (inputData.length < 2) {
                lackOfArgs();
                printHints();
                continue;
            }

            int mode;

            try {
                mode = Integer.parseInt(inputData[0]);
            } catch (NumberFormatException e) {
                wrongTypeMode();
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
                wrongTypeMode();
                printHints();
                continue;
            }

            printHints();
        }

        console.close();
    }

    public static void readTheFile(String path) {
        reading();

        if (!Files.exists(Paths.get(path))) {
            notExist();
            return;
        }

        try (BufferedReader buffer = Files.newBufferedReader(Paths.get(path))) {
            String line;
            System.out.println("\n --- Start --- \n");

            while ((line = buffer.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("\n ---  End  --- \n");
        } catch (IOException e) {
            failedToReadOrWrite("read ");
            System.err.println("Exception: " + e.toString());
        }
    }

    public static boolean writeTheFile(String path, String dataToWrite, int mode) {
        StandardOpenOption option = (mode == 2) ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;

        try (BufferedWriter buffer = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.CREATE, option)) {
            buffer.write(dataToWrite);

            return true;
        } catch (IOException e) {
            failedToReadOrWrite("write");
            System.err.println("Exception: " + e.toString());
            return false;
        }
    }

    public static void appendToFile(Scanner console, String path, int mode) {
        appending();

        StringBuilder builder = new StringBuilder();
        boolean done = true;

        if (mode == 2 && Files.exists(Paths.get(path))) {
            builder.append(System.lineSeparator());
        }

        while (true) {
            String line = console.nextLine();

            if (line.equalsIgnoreCase("-q")) {
                break;
            }

            if (line.equalsIgnoreCase("help") || line.equalsIgnoreCase("-h")) {
                done = false;
                break;
            }

            builder.append(line).append(System.lineSeparator());
        }

        if (writeTheFile(path, builder.toString(), mode) && done) {
            writingSuccess(mode);
        }
    }

    public static void greetings() {
        System.out.println("\n|-----------------------------------------------------------|");
        System.out.println("| Hello!   This   program   can   read   and  write  files. |");
        System.out.println("| Type  \"--help\"  or  type   \"-h\"   to   get   more   info. |");
        System.out.println("| To   continue,   choose    mode.  To   quit   type  \"-q\". |");
        System.out.println("|-----------------------------------------------------------|\n");
    }

    public static void help() {
        System.out.println("\n|-----------------------------------------------------------|");
        System.out.println("| 1 to Read file, 2 to Append to file, 3 to Overwrite file. |");
        System.out.println("| Enter  file path followed by mode, e.g., \"2 example.txt\". |");
        System.out.println("|-----------------------------------------------------------|\n");
    }

    public static void printHints() {
        System.out.println("\n|-----------------------------------------------------------|");
        System.out.println("| Type  \"--help\"  or  type   \"-h\"   to   get   more   info. |");
        System.out.println("| To   continue,   choose    mode.  To   quit   type  \"-q\". |");
        System.out.println("|-----------------------------------------------------------|\n");
    }

    public static void reading() {
        System.out.println("\n|-----------------------------------------------------------|");
        System.out.println("|    Reading    data    from   file.    Please,  wait   ... |");
        System.out.println("|-----------------------------------------------------------|\n");
    }

    public static void appending() {
        System.out.println("\n|-----------------------------------------------------------|");
        System.out.println("|      Enter     data    to    write    to    file   ...    |");
        System.out.println("|          Type      \"-q\"         when     finish.          |");
        System.out.println("| Type  \"--help\"  or  type   \"-h\"   to   get   more   info. |");
        System.out.println("|-----------------------------------------------------------|\n");
    }

    public static void writingSuccess(int mode) {
        String action = (mode == 2) ? "  appended " : "overwritten";
        System.out.println("\n|-----------------------------------------------------------|");
        System.out.println("|       Data       successfully      " + action + "     ...    |");
        System.out.println("|-----------------------------------------------------------|\n");
    }

    public static void quiteProg() {
        System.out.println("\n|-----------------------------------------------------------|");
        System.out.println("| Quiting  the  program .......................  Bye - Bye! |");
        System.out.println("|-----------------------------------------------------------|\n");
    }

    public static void lackOfArgs() {
        System.err.println("\n|-----------------------------------------------------------|");
        System.err.println("| Incorrect input!  Please provide mode and  file  path ... |");
        System.err.println("|-----------------------------------------------------------|\n");
    }

    public static void wrongTypeMode() {
        System.err.println("\n|-----------------------------------------------------------|");
        System.err.println("| Wrong mode! Hint: mode must be a number 1,  2  or  3  ... |");
        System.err.println("|-----------------------------------------------------------|\n");
    }

    public static void notExist() {
        System.err.println("\n|-----------------------------------------------------------|");
        System.err.println("|  File   does   not   exist!   Please  check  path   ...   |");
        System.err.println("|-----------------------------------------------------------|\n");
    }

    public static void failedToReadOrWrite(String msg) {
        System.err.println("\n|-----------------------------------------------------------|");
        System.err.println("|      Failed       to       " + msg + "       file!      ...     |");
        System.err.println("|-----------------------------------------------------------|\n");
    }
}