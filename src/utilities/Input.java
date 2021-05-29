package src.utilities;

import java.util.Scanner;

public class Input {
    private static Scanner input = new Scanner(System.in);

    public static int readInt() {
        int n = input.nextInt();
        input.nextLine();
        return n;
    }

    public static double readDouble() {
        double n = input.nextDouble();
        input.nextLine();
        return n;
    }

    public static String readString() {
        String n = input.nextLine();
        return n;
    }    
}
