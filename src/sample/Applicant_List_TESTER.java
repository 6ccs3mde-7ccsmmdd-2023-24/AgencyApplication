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
            System.out.print("Enter your email: ");
            String email = EasyScannerPlus.nextString();
            if (applicantList.getApplicant(email).isPresent()) {
                System.out.println("This email is already registered!");
            } else {
                System.out.print("Enter your name:");
                String appName = EasyScannerPlus.nextString();
                System.out.print("Enter skill 1: ");
                String skill_1 = EasyScannerPlus.nextString();
                System.out.print("Enter skill_2: ");
                String skill_2 = EasyScannerPlus.nextString();
                System.out.print("Years of experience: ");
                int appExperience = EasyScannerPlus.nextInt();
                if (appExperience < 0) {
                    System.out.println("\nYour experience cannot smaller than 0");
                } else {
                    Applicant applicant = new Applicant( email,appName, skill_1, skill_2, appExperience);
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

            System.out.print("WARNING: Deleting this job will affect the Applicants that match with this job.\nDelete this job? (y/n) ");
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
        System.out.println();
        Optional <Applicant> applicant = applicantList.getApplicant(email);
        if (!applicant.isPresent()) { //Check if the job is present in the list
            System.out.println("This applicant is not registered\n");
        } else {
            char answer;
            int choice = 0;
            System.out.print("Would you like to update the job details? (y/n) ");
            answer = EasyScannerPlus.nextChar();
            do {
                if (answer == 'y') {
                    System.out.println("1. Update Name.");
                    System.out.println("2. Update Skill 1.");
                    System.out.println("3. Update Skill 2.");
                    System.out.println("4. Update Experience.");
                    System.out.println("5. See the Applicant Details and go back to Applicant Menu.\n");
                    System.out.print("What would you like to update? (1-5) ");
                    choice = EasyScannerPlus.nextInt();

                    try {
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
                                System.out.println("Current Skill 2: " + applicantList.getApplicant(email).get().getSkill_2());
                                System.out.print("Enter new Skill 2: ");
                                String newSkill2 = EasyScannerPlus.nextString();
                                String newApplicantSkill2 = applicantList.getApplicant(email).get().setSkill_2(newSkill2);
                                System.out.println("New Skill 1: " + newApplicantSkill2);
                            }
                            case 4 -> {
                                System.out.println("Current Experience: " + applicantList.getApplicant(email).get().getYourExperience());
                                System.out.print("Enter new Experience: ");
                                int experience = EasyScannerPlus.nextInt();
                                int newJobSkill = applicantList.getApplicant(email).get().setYourExperience(experience);
                                System.out.println("New Experience: " + newJobSkill);
                            }

                            case 5 -> System.out.println("----- Applicant Details -----"+
                                    applicantList.getApplicant(email).toString());

                            default -> System.out.println("\nChoices 1 and 5 only!\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(e);
                    }
                }
            } while (choice != 5);
        }
    }

    //Get applicant details
    static void option4() {
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

    //Method to display 5 records from the list. Throws AgencyException
    static void option5() throws AgencyException {

        int fromWhere;
        System.out.print("Where would you like to start seeing the records? ");
        fromWhere = EasyScannerPlus.nextInt();
        applicantList.displayApplicantRecords(fromWhere);
    }
}