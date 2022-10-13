package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Reader {

    public static Scanner scanner;

    static String line;

    public Reader(Scanner scanner) {
        Reader.scanner = scanner;
    }

    public String read() {
        try {
            line = "";
            while (line.equals("") && scanner.hasNextLine()) {
                line = scanner.nextLine();
                String lineNoneSpace = line.replaceAll(" ", "");
                if (!(lineNoneSpace.length() == 0)) {
                    return line;
                } else {
                    System.out.println("Вы ничего не ввели");
                }
            }
            if (!scanner.hasNextLine() && line.equals("")) {
                return "exit";
            } else return line;
        } catch (NoSuchElementException | IllegalStateException exception) {
            return "exit";
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Reader.scanner = scanner;
    }
}