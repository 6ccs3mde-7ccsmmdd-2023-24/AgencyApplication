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

    public static void main(String[] args) {

        applicantList = new Applicant_List();

        int choice = 0; //Initialize the choice

        System.out.println("\n**** Applicant List Tester **** \n");
        do {
            //Menu
            System.out.println("1. Add applicant");
            System.out.println("2. Remove applicant");
            System.out.println("3. Update applicant");
            System.out.println("4. Get applicant details");
            System.out.println("5. Display all Applicants ");
            System.out.println("6. Leave\n");

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
                    case 5 -> option5();
                    case 6 -> System.out.println("Thank you for testing the Agency Menu!");
                    default -> System.out.println("Enter choices between 1 and 6 only!\n");
                }
                //Catch block that will be executed if the user enters a character instead a number
                // Also this catch block executes AgencyException
            } catch (InputMismatchException | AgencyException e) {
                System.out.println("\n"+e +"\n");
            }

        } while (choice != 6);
    }

    //Add applicant
    static void option1() {
        try {
            System.out.print("What is your email: ");
            String email = EasyScannerPlus.nextString();
            if (applicantList.getApplicant(email).isPresent()) {
                System.out.println("This email is already registered!");
            } else {
                System.out.print("What is your name:");
                String appName = EasyScannerPlus.nextString();
                System.out.print("What is your skill 1: ");
                String skill_1 = EasyScannerPlus.nextString();
                System.out.print("What is your skill_2: ");
                String skill_2 = EasyScannerPlus.nextString();
                System.out.print("How many years of experience do you have: ");
                int appExperience = EasyScannerPlus.nextInt();
                if (appExperience < 0) {
                    System.out.println("\nYour experience cannot smaller than 0");
                } else {
                    if (email.isEmpty() || appName.isEmpty() || skill_1.isEmpty() || skill_2.isEmpty() ) {
                        System.out.println("\nError: Make sure all field are entered!\n");
                    } else {
                        Applicant applicant = new Applicant(email,appName, skill_1, skill_2, appExperience);
                        boolean checkApplicant = applicant.check();// call the check method from the Applicant class
                        if (checkApplicant) { // This block is executed if the applicant name and skills does not contain digits
                            boolean fine = applicantList.addApplicant(applicant);
                            if (fine) {
                                //Attempt to add applicant to the Applicant_List
                                System.out.println("\nApplicant added!");
                            } else {
                                System.out.println("\nERROR: Applicant already registered!");
                            }
                        } else {
                            System.out.println("\nWARNING: Your name and/or skills must not have digits!");
                        }
                    }
                }
            }

            /*
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type.
             */
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        System.out.println();
    }

    //Remove an Applicant
    static void option2()  {
        String email;
        System.out.print("Enter your email: "); //Retrieve the applicant's email
        email = EasyScannerPlus.nextString();
        Optional<Applicant> applicant = applicantList.getApplicant(email);
        if (applicant.isPresent()) {

            System.out.print("WARNING: Deleting this applicant of the application may also be deleted from the list of matches ,\n" +
                    "if the applicant has a match. Proceed? (y/n) ");
            char choice = EasyScannerPlus.nextChar();

            //Execute this block of code if the user enters a 'y' or 'Y'
            if (choice == 'y'|| choice == 'Y') {
                applicantList.removeApplicant(email);
                System.out.println("\nApplicant removed!\n");
                if (!applicantList.isEmpty()) {
                    System.out.println("\nApplicants on the list: " + applicantList.getTotalOfApplicants() + '\n');
                } else {
                    System.out.println("\nList is empty\n");
                }
            } else {
                System.out.println("\nApplicant has been kept in the recordings!\n");
            }
        } else {
            System.out.println("\nApplicant not found!\n");
        }
    }

    //Update Applicant
    static void option3() {
        String email;
        System.out.print("Enter your email: ");
        email = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        Optional<Applicant> applicant = applicantList.getApplicant(email);
        System.out.println();
        if (applicant.isEmpty()) { //Check if the applicant is in the list
            System.out.println("This applicant is not registered\n");
        } else {
            char answer;
            int choice ;

            try {
                System.out.print("WARNING: Updating the applicant details may affect the matches list.\nProceed? (y/n) ");
                answer = EasyScannerPlus.nextChar();
                if (answer == 'y' || answer == 'Y') {
                    do {
                        System.out.println("\t\t1. Update Name.");
                        System.out.println("\t\t2. Update Skill 1.");
                        System.out.println("\t\t3. Update Skill 2.");
                        System.out.println("\t\t4. Update Experience.");
                        System.out.println("\t\t5. Go back to Applicant Menu.\n");
                        System.out.print("Enter option: (1-5) ");
                        choice = EasyScannerPlus.nextInt();

                        //An enhanced switch statement
                        switch (choice) {
                            case 1 -> {
                                System.out.println("Current Name: " + applicantList.getApplicant(email).get().getName());
                                System.out.print("Enter new Name: ");
                                String newName = EasyScannerPlus.nextString();
                                String newApplicantName = applicantList.getApplicant(email).get().setName(newName);
                                System.out.println("New Name: " + newApplicantName);
                            }
                            case 2 -> {
                                System.out.println("Current Skill 1: " + applicantList.getApplicant(email).get().getSkill_1());
                                System.out.print("Enter new Skill 1: ");
                                String newSkill1 = EasyScannerPlus.nextString();
                                String newApplicantSkill1 = applicantList.getApplicant(email).get().setSkill_1(newSkill1);
                                System.out.println("New Skill 1: " + newApplicantSkill1);
                            }
                            case 3 -> {
                                System.out.println("Current Skill 2: " + applicantList.getApplicant(email).get().getSkill_1());
                                System.out.print("Enter new Skill 2: ");
                                String newSkill2 = EasyScannerPlus.nextString();
                                String newApplicantSkill2 = applicantList.getApplicant(email).get().setSkill_1(newSkill2);
                                System.out.println("New Skill 1: " + newApplicantSkill2);
                            }
                            case 4 -> {
                                System.out.println("Current Experience: " + applicantList.getApplicant(email).get().getYourExperience());
                                System.out.print("Enter new Experience: ");
                                int experience = EasyScannerPlus.nextInt();
                                int newJobSkill = applicantList.getApplicant(email).get().setYourExperience(experience);
                                System.out.println("New Experience: " + newJobSkill);
                            }

                            case 5 -> {
                                System.out.println("Going to Applicant Menu...");
                                Thread.sleep(2000);// Make the application sleep for 2 seconds
                            }

                            default -> System.out.println("\nChoices 1 and 7 only!\n");
                        }

                    } while (choice != 5);
                } else {
                    System.out.println("Going back to Applicant Menu...\n");
                    Thread.sleep(2000); // Make the application to sleep for 2 seconds
                }
                /*
                 * Catch block to be executed if choice is not a digit. Also, we catch the InterruptedException
                 * when a thread waits or sleeps, and other threads are interrupted and cannot proceed further.
                 * The block also catches the user choice is not a digit.
                 */
            } catch (InputMismatchException | InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    //Get applicant details
    static void option4() {
        System.out.print("Enter your email: ");
        String email = EasyScannerPlus.nextString();

        //Retrieve the applicant using the Optional class
        Optional<Applicant> applicant = applicantList.getApplicant(email);

        if (applicant.isPresent()) {  //Check if the applicant is present in the list
            System.out.println("*** Applicant Details ***");
            System.out.println(applicant.get().toString());
        } else {
            System.out.println("Applicant not found in the list!\n");
        }
    }

    //Method to display 5 records from the list. Throws AgencyException
    static void option5() throws AgencyException {

        int fromWhere;
        System.out.print("Where would you like to start seeing the records? ");
        fromWhere = EasyScannerPlus.nextInt();
        applicantList.displayApplicantRecords(fromWhere);
    }
}