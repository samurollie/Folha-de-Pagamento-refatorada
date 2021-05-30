package src.utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static Scanner input = new Scanner(System.in);

    public static int readInt() {
        Boolean completed = false;
        int n = 0;
        while(!completed) {
            try {
                n = input.nextInt();
                completed = true;
            } catch (InputMismatchException e) {
                System.out.println("Insira um número inteiro!");
            }
            input.nextLine();
        }
        return n;
    }

    public static double readDouble() {
        Boolean completed = false;
        double n = 0;
        while(!completed) {
            try {
                n = input.nextDouble();
                completed = true;
            } catch (InputMismatchException e) {
                System.out.println("Insira um número racional! (Use virgula ao invés de ponto)");
            }
            input.nextLine();
        }
        return n;
    }

    public static String readString() {
        String n = input.nextLine();
        return n;
    }    
}
