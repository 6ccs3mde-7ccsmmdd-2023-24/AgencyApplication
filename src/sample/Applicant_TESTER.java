package sample;

import java.util.InputMismatchException;

/**
 * Class to test the Applicant class
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
public class Applicant_TESTER {

    public static void main(String[] args) {

        Applicant myApplicant;
        char choice ;

        try {

            // The do while will run multiple times unless the user presses  n
            do {

                System.out.print("Enter your email: ");
                String email = EasyScannerPlus.nextString();
                System.out.print("Enter your name:");
                String appName = EasyScannerPlus.nextString();
                System.out.print("Enter skill 1: ");
                String skill_1 = EasyScannerPlus.nextString();
                System.out.print("Enter skill_2: ");
                String skill_2 = EasyScannerPlus.nextString();
                System.out.print("Years of experience: ");
                int appExperience = EasyScannerPlus.nextInt();
                myApplicant = new Applicant( email,appName, skill_1, skill_2, appExperience);

                boolean check = myApplicant.check();
                if (!check) {
                    System.out.println("Check your name and/or skills does not contain digits!");
                } else {
                    System.out.println(myApplicant);

                }
                System.out.print("Try again?(y/n) ");
                choice = EasyScannerPlus.nextChar();

            } while (choice != 'n');
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
    }
}
