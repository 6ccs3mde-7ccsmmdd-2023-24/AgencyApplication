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
            System.out.println("2. Update Job");
            System.out.println("3. Remove Job");
            System.out.println("4. Display a job");
            System.out.println("5. Display jobs");
            System.out.println("6. Quit\n");

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
                    case 4 -> option4();
                    case 5 -> option5();
                    case 6 -> System.out.println("Thank you for testing the Job List!");
                    default -> System.out.println("Enter options 1 to 6\n");
                }
                //Catch block that will be executed if the user enters a character instead a number
            } catch (InputMismatchException | AgencyException e) {
                System.out.println("\n"+e +"\n");
            }
        } while (choice != 6);
    }

    //Add a job
    static void option1() {
        try {

            String type;
            System.out.print("Choose a ID for  the Job of length 4: ");
            String ID = EasyScannerPlus.nextString();
            System.out.print("Job Title: ");
            String jobName = EasyScannerPlus.nextString();
            System.out.print("Location of the Job vacancy: ");
            String location = EasyScannerPlus.nextString();
            System.out.println("Contract Types: ");
            System.out.println("\t1. Full-Time");
            System.out.println("\t2. Part-Time" );
            System.out.println("\t3. Placement");
            System.out.println("\t4. Internship");
            System.out.print("Type of contract: ");
            type = EasyScannerPlus.nextString();

            /*
                 While loop to make sure the method does not terminate when the user enters a value between 1 and 4 and
                 all previous information already entered must be entered from the beginning.This while may throw an
                 NumberFormatException if the number is not a digit.At the end of this method there is a catch block
                 to handle this exception.
             */
            while (Integer.parseInt(type) < 1 || Integer.parseInt(type) > 4) {
                System.out.println("No such option!Try again:");
                type = EasyScannerPlus.nextString();
            }

            //Switch statement for the type of contract
            switch (type) {
                case "1" -> {
                    type = String.valueOf(JobTypeOfContract.FULL_TIME);
                    System.out.println("\nContract: " + JobTypeOfContract.FULL_TIME+ '\n');
                }
                case "2" -> {
                    type = String.valueOf(JobTypeOfContract.PART_TIME);
                    System.out.println("\nContract: " + JobTypeOfContract.PART_TIME +'\n');
                }
                case "3" -> {
                    type = String.valueOf(JobTypeOfContract.WORK_PLACEMENT);
                    System.out.println("\nContract: " + JobTypeOfContract.WORK_PLACEMENT +'\n');
                }
                case "4" -> {
                    type = String.valueOf(JobTypeOfContract.INTERNSHIP);
                    System.out.println("\nContract: " + JobTypeOfContract.INTERNSHIP +'\n');
                }
            }

            System.out.print("What is the Main Skill required: ");
            String primarySkill = EasyScannerPlus.nextString();
            System.out.print("Job Salary £: ");
            double salary = EasyScannerPlus.nextDouble();
            if (salary < 1) {// If salary smaller than 1 then execute this code
                System.out.println("\nSalary cannot have a negative value!\n");
            } else {
                System.out.print("What is the minimum of years of experience for this Job: ");
                int experience = EasyScannerPlus.nextInt();
                if (experience < 0) { // If experience smaller than 0 then execute this code
                    System.out.println("\nExperience cannot have a negative value!\n");
                } else {
                    if (ID.isEmpty() || jobName.isEmpty()|| location.isEmpty()|| type.isEmpty()|| primarySkill.isEmpty()) {
                        System.out.println("\nError: Make sure all field are entered!\n");
                    } else {
                        Job myJob = new Job(ID, jobName, location, JobTypeOfContract.valueOf(type), primarySkill, salary, experience);
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
            }
            /*
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type.
             */
        } catch (  NumberFormatException e) {
            System.out.println(e);
        }
    }

    //Update Job
    static void option2() {


        String ID;
        System.out.print("Enter job ID: ");
        ID = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        Optional<Job> job = jobList.getJob(ID);
        System.out.println();
        if (job.isEmpty()) { //If the job is not found in the list execute this block of code
            System.out.println("This job isn't registered!\n");
        } else {
            char answer;
            int choice ;

            try {
                //Update Menu.The user chooses what part of the job has to be updated.
                System.out.print("WARNING: Updating this job may affect the matches for this job.\nProceed? (y/n) ");
                answer = EasyScannerPlus.nextChar();
                if (answer == 'y' || answer == 'Y') {
                    do {
                        System.out.println("1. Update Title");
                        System.out.println("2. Update Location");
                        System.out.println("3. Update Type of Contract");
                        System.out.println("4. Update Skill");
                        System.out.println("5. Update Salary");
                        System.out.println("6. Update Experience");
                        System.out.println("7. Go back to Job Menu\n");
                        System.out.print("Enter option: (1-7) ");
                        choice = EasyScannerPlus.nextInt();
                        //An enhanced switch statement
                        switch (choice) {
                            case 1 -> {
                                System.out.println("Current Title: " + jobList.getJob(ID).get().getJobTitle());
                                System.out.print("Enter new Title: ");
                                String newTitle = EasyScannerPlus.nextString();
                                String newJobTitle = jobList.getJob(ID).get().setJobTitle(newTitle);
                                System.out.println("New Title: " + newJobTitle);
                            }
                            case 2 -> {
                                System.out.println("Current Location: " + jobList.getJob(ID).get().getLocation());
                                System.out.print("Enter new Location: ");
                                String newLocation = EasyScannerPlus.nextString();
                                String newJobLocation = jobList.getJob(ID).get().setLocation(newLocation);
                                System.out.println("New Location: " + newJobLocation);
                            }
                            case 3 -> {
                                String type;
                                System.out.println("Current Contract : " + jobList.getJob(ID).get().getType());
                                System.out.println("Contract Types: ");
                                System.out.println("\t\t\t1. Full-Time");
                                System.out.println("\t\t\t2. Part-Time");
                                System.out.println("\t\t\t3. Placement");
                                System.out.println("\t\t\t4. Internship");
                                System.out.println("\t\t\t5. Keep Contract");
                                System.out.print("Enter  new type of contract: ");
                                type = EasyScannerPlus.nextString();
                                while (Integer.parseInt(type) < 1 || Integer.parseInt(type) > 5) {
                                    System.out.print("Enter values 1 to 5 only: ");
                                    type = EasyScannerPlus.nextString();
                                }

                                // An enhanced switch to update the contract of the job
                                switch (type) {
                                    case "1" -> {
                                        type = String.valueOf(JobTypeOfContract.FULL_TIME);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("New Contract: " + JobTypeOfContract.FULL_TIME);
                                    }
                                    case "2" -> {
                                        type = String.valueOf(JobTypeOfContract.PART_TIME);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("New Contract: " + JobTypeOfContract.PART_TIME + '\n');
                                    }
                                    case "3" -> {
                                        type = String.valueOf(JobTypeOfContract.WORK_PLACEMENT);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("New Contract: " + JobTypeOfContract.WORK_PLACEMENT + '\n');
                                    }
                                    case "4" -> {
                                        type = String.valueOf(JobTypeOfContract.INTERNSHIP);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("New Contract: " + JobTypeOfContract.INTERNSHIP + '\n');
                                    }
                                    case "5" -> System.out.println("Job contract kept!");
                                }

                            }
                            case 4 -> {
                                System.out.println("Current Skill: " + jobList.getJob(ID).get().getPrimarySkill());
                                System.out.print("Enter new Skill: ");
                                String newSkill = EasyScannerPlus.nextString();
                                String newJobSkill = jobList.getJob(ID).get().setPrimarySkill(newSkill);
                                System.out.println("New Type: " + newJobSkill);
                            }
                            case 5 -> {
                                System.out.println("Current Salary: " + jobList.getJob(ID).get().getSalary());
                                System.out.print("Enter new Salary: ");
                                double newSalary = EasyScannerPlus.nextDouble();
                                double newJobSalary = jobList.getJob(ID).get().setSalary(newSalary);
                                System.out.println("New Salary: " + newJobSalary);
                            }
                            case 6 -> {
                                System.out.println("Current Experience: " + jobList.getJob(ID).get().getExperience());
                                System.out.print("Enter new Experience: ");
                                int newExperience = EasyScannerPlus.nextInt();
                                int newJobExperience = jobList.getJob(ID).get().setExperience(newExperience);
                                System.out.println("New Experience: " + newJobExperience);
                            }
                            case 7 -> {
                                System.out.println("Going to back Job Menu...\n");
                                Thread.sleep(2000); // Make the application to sleep for 2 seconds
                            }
                            default -> System.out.println("Choices 1 and 7 only!");
                        }


                    } while (choice != 7);
                } else {
                    System.out.println("Going back to Job Menu...\n");
                    Thread.sleep(2000); // Make the application to sleep for 2 seconds
                }
                /*
                 * Catch block to be executed if choice is not a digit. Also, we catch the InterruptedException
                 * when a thread waits or sleeps, and other threads are interrupted and cannot proceed further.
                 * The block also catches the user choice is not a digit.
                 */
            } catch (InputMismatchException | NumberFormatException| InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    //Remove a job
    static void option3() {
        System.out.print("Enter job ID: "); //Retrieve the Job ID
        String jobID = EasyScannerPlus.nextString();
        Optional<Job> job = jobList.getJob(jobID);
        if (job.isPresent()) {

            System.out.print("WARNING: Deleting this job may affect the matches with this job.\nProceed? (y/n) ");
            char choice = EasyScannerPlus.nextChar();
            //Execute this block of code if the user enters 'y' or 'Y'
            if (choice == 'y'|| choice =='Y') {
                jobList.removeJob(jobID);
                System.out.println("\nJob removed!\n");
                if (!jobList.isEmpty()) {
                    System.out.println("\nJobs left: " + jobList.getTotalOfJobs() + '\n');
                } else {
                    System.out.println("\nList is empty\n");
                }
            } else {
                System.out.println("\nJob has been kept in the recordings!\n");
            }
        } else {
            System.out.println("\nJob not found!\n");
        }
    }

    //Get a job
    static void option4() {
        System.out.print("Enter job ID: ");
        String ID = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        Optional<Job> job = jobList.getJob(ID);
        System.out.println();
        if (job.isPresent()) { //Check if the job is present in the list
            System.out.println("*** Job Details ***");
            System.out.println(job.get().toString());
        } else {
            System.out.println("Job not found in the List!\n");
        }
    }

    //Method to display 5 records from the list
    static void option5() throws AgencyException {

        int fromWhere;
        System.out.print("Where would you like to start seeing the records? ");
        fromWhere = EasyScannerPlus.nextInt();
        jobList.displayJobRecords(fromWhere);
    }
}


