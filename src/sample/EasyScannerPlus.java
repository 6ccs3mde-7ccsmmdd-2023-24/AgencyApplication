package sample;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EasyScannerPlus  {

    public static int nextInt() {
        Scanner keyboard = new Scanner(System.in);
        int i;
        try {
            i = keyboard.nextInt();

        } catch (InputMismatchException e) {
            throw  new InputMismatchException("Ops! Only numbers!\n");
        }
        return i;
    }

    public static double nextDouble() {
        Scanner keyboard = new Scanner(System.in);
        double d;
        try {
            d = keyboard.nextDouble();
        } catch (InputMismatchException e) {
            throw  new InputMismatchException("Ops! Only numbers! \n");
        }
        return d;
    }

    public static String nextString() {
        Scanner keyboard = new Scanner(System.in);
        String s = keyboard.nextLine();
        return s;
    }

    public static char nextChar() {
        Scanner keyboard = new Scanner(System.in);
        char c = keyboard.next().charAt(0);
        return c;
    }
}

