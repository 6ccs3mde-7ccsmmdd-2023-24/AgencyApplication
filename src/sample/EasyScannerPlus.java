/**
 * This class has been designed to improve the user's experience by
 * making the keyboard input easier. All methods have been made as
 * static so that we can simply use the class name when we call a
 * method.
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 *
 */
package sample;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EasyScannerPlus  {

    //Method to hold integers
    public static int nextInt() {
        Scanner keyboard = new Scanner(System.in);
        int i;
        try {
            i = keyboard.nextInt();

        } catch (InputMismatchException e) {
            throw  new InputMismatchException("Error:Only numbers accepted!");
        }
        return i;
    }

    //Method to hold doubles
    public static double nextDouble() {
        Scanner keyboard = new Scanner(System.in);
        double d;
        try {
            d = keyboard.nextDouble();
        } catch (InputMismatchException e) {
            throw  new InputMismatchException("Error: Only numbers accepted!");
        }
        return d;
    }

    //Method to hold Strings
    public static String nextString() {
        String s;
        Scanner keyboard = new Scanner(System.in);
        try {
             s = keyboard.nextLine();
        } catch (NumberFormatException e) {
            throw  new NumberFormatException("Error: Invalid Entry!");
        }
        return s;
    }

    //Method to hold characters
    public static char nextChar() {
        char c;

        Scanner keyboard = new Scanner(System.in);
            c = keyboard.next().charAt(0);
        return c;
    }
}

