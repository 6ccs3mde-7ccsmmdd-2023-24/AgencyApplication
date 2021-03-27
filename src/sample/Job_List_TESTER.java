package sample;

import java.util.InputMismatchException;
import java.util.Optional;

/**
 * A tester class to test the Job list class
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
public class Job_List_TESTER {

    private static Job_List jobList ; //Declare Job_List object to test

    public static void main(String[] args) {

        int choice = 0; //Initialize the choice
        jobList = new Job_List();

        System.out.println("\n**** Job List Tester ****");
        //Menu
        do {
            System.out.println("1. Add Job");
            System.out.println("2. Display a job");
            System.out.println("3. Display all jobs");
            System.out.println("4. Quit\n");

            try {
                //Get a choice
                System.out.print("Enter choice: ");
                choice = EasyScannerPlus.nextInt();
                System.out.println();

                //Process the user choice
                switch (choice) {
                    case 1 -> option1();
                    case 2 -> option2();
                    case 3 -> option3();
                    case 4 -> System.out.println("Thank you for testing the Job List!");
                    default -> System.out.println("Enter options 1 to 4\n");
                }
                //Catch block that will be executed if the user enters a character instead a number
            } catch (InputMismatchException e) {
                System.out.println("\n"+e +"\n");
            }
        } while (choice != 4);
    }

    //Add a job
    static void option1() {
        try {

            System.out.print("Choose a ID for a job of length 4: ");
            String ID = EasyScannerPlus.nextString();
            System.out.print("Enter job name: ");
            String jobName = EasyScannerPlus.nextString();
            System.out.print("Enter job location: ");
            String location = EasyScannerPlus.nextString();
            System.out.print("Enter type of the Job: ");
            String type = EasyScannerPlus.nextString();
            System.out.print("Enter primary Skill: ");
            String primarySkill = EasyScannerPlus.nextString();
            System.out.print("Enter Salary £: ");
            double salary = EasyScannerPlus.nextDouble();
            if (salary < 1) {// If salary smaller than 1 then execute this code
                System.out.println("\nSalary cannot have a negative value!\n");
            } else {
                System.out.print("Minimum of years of experience: ");
                int experience = EasyScannerPlus.nextInt();
                if (experience < 0) { // If experience smaller than 0 then execute this code
                    System.out.println("\nExperience cannot have a negative value!\n");
                } else {
                    Job myJob = new Job(ID, jobName, location,type,primarySkill,salary, experience);
                    System.out.println();
                    boolean check = myJob.check();
                    if (!check) { // This block is executed if the ID length is not equal to 4
                        System.out.println("Job failed! ID must have 4 and only characters.Try Again!\n");
                    } else {
                        //Attempt to add job to the Job_List
                        boolean fine = jobList.addJob(myJob);
                        if (fine) {
                            System.out.println("Job added");
                        } else {
                            System.out.println("Job already on the system ");
                        }
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
    }

    //Get a job
    static void option2() {
        try {
            if (jobList.isEmpty()) {
                System.out.println("List is empty!\n");
            } else {
                System.out.print("Enter job ID: ");
                String ID = EasyScannerPlus.nextString();

                //Retrieve the job using the Optional class
                Optional<Job> j = jobList.getJob(ID);
                System.out.println();
                if (!jobList.getJob(ID).isPresent()) { //Check if the job is present in the list
                    System.out.println("This job isn't registered!");
                } else {
                    System.out.println(j);
                }
                System.out.println();
            }
            /**
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type.
             */
        }catch(InputMismatchException e){
                System.out.println(e);
        }
    }

    //Display total and Job list
    static void option3() {

        if (jobList.isEmpty()) { //Check if the list is empty
            System.out.println("List is empty!\n");
        } else {
            System.out.println("Total of jobs: " + jobList.totalOfJobs());
            System.out.println();
            System.out.println("*** JOBS ****");
            System.out.println(jobList);
            System.out.println();
        }
    }
}


