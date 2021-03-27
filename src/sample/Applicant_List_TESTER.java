package sample;

import java.util.InputMismatchException;
import java.util.Optional;

/**
 * This is the main tester class that will implement  Applicant_List.
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */

public class Applicant_List_TESTER {

    private static Applicant_List applicantList;
    private static Applicant applicant;

    public static void main(String[] args) {

        applicantList = new Applicant_List();

        int choice = 0; //Initialize the choice

        System.out.println("\n**** Applicant List Tester **** \n");
        do {
            //Menu
            System.out.println("1. Add applicant");
            System.out.println("2. Remove applicant");
            System.out.println("3. Get applicant details");
            System.out.println("4. Display all Applicants ");
            System.out.println("5. Leave\n");

            try {
                //Get choice
                System.out.print("Enter a choice: ");
                choice = EasyScannerPlus.nextInt();
                System.out.println();

                //Process the user choice
                switch (choice) {
                    case 1 -> option1();
                    case 2 -> option2();
                    case 3 -> option3();
                    case 4 -> option4();
                    case 5 -> System.out.println("Thank you for testing the Agency Menu!");
                    default -> System.out.println("Enter choices between 1 and 10 only!\n");
                }
                //Catch block that will be executed if the user enters a character instead a number
            } catch (InputMismatchException e) {
                System.out.println("\n"+e +"\n");
            }

        } while (choice != 5);
    }

    //Add applicant
    static void option1() {

        try {
            System.out.print("Enter your name:");
            String appName = EasyScannerPlus.nextString();
            System.out.print("Enter your email: ");
            String email = EasyScannerPlus.nextString();
            if (applicantList.getApplicant(email).isPresent()) {
                System.out.println("This email is already registered!");
            } else {
                System.out.print("Enter skill 1: ");
                String skill_1 = EasyScannerPlus.nextString();
                System.out.print("Enter skill_2: ");
                String skill_2 = EasyScannerPlus.nextString();
                System.out.print("Years of experience: ");
                int appExperience = EasyScannerPlus.nextInt();
                if (appExperience < 0) {
                    System.out.println("\nYour experience cannot smaller than 0");
                } else {
                    applicant = new Applicant(appName, email, skill_1, skill_2, appExperience);
                    boolean checkApplicant = applicant.check();// call the check method from the Applicant class
                    if (checkApplicant) { // This block is executed if the applicant name and skills does not contain digits
                        boolean fine = applicantList.addApplicant(applicant);
                        if (fine) {
                            //Attempt to add applicant to the Applicant_List
                            System.out.println("Applicant added!");
                        } else {
                            System.out.println("ERROR: Applicant already registered!");
                        }
                    } else {
                        System.out.println("WARNING: Your name and/or skills must not have digits!");
                    }
                }
            }

            /**
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type.
             */
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        System.out.println();
    }

    //Remove an Applicant
    static void option2() {
        System.out.print("Enter your email: ");
        String email = EasyScannerPlus.nextString();
        System.out.println();
        boolean found = applicantList.removeApplicant(email);
        if (found) {      //Attempt to remove applicant to the Applicant_List
            System.out.println("\nApplicant successfully removed\n !");
        } else {
            System.out.println("\nApplicant not found in the list!\n");
        }
    }

    //Get applicant details
    static void option3() {
        System.out.print("Enter your email: ");
        String position = EasyScannerPlus.nextString();

        //Retrieve the applicant using the Optional class
        Optional<Applicant> applicant = applicantList.getApplicant(position);
        if (applicant.isPresent()) {  //Check if the applicant is present in the list
            System.out.println(applicant.toString());
        } else {
            System.out.println("\nApplicant not found in the list!\n");
        }
    }

    // Display all applicants
    static void option4() {
        if (applicantList.isEmpty()) {
            System.out.println("Applicant list is empty\n");
        } else {
            System.out.println("**** Applicant Details ****");
            System.out.println(applicantList);
            System.out.println();
        }
    }
}
