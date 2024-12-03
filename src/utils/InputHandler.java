package utils;

import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        return scanner.nextLine();
    }
}
