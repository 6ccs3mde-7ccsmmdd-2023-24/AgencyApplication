package sample;

import java.util.*;

/**
 * This is the main tester class that will implement both Job_list and Applicant_List.
 * The Job and applicant instructions will be added on the graphical interface.
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */

public class MainMenu_TESTER {
    private static Job_List jobList;
    private static Applicant_List applicantList;
    private static final Map<String, String> myMatches = new HashMap<>();

    public static void main(String[] args) {

        //Initialize choice
        int choice = 0;

        //Initialize the Lists
        jobList = new Job_List();
        applicantList = new Applicant_List();

        //Read the Job and Applicant lists. These files can be read in a text editor such as Notepad or an IDE
        JobFileHandler.readJobList(jobList);
        ApplicantFileHandler.readApplicantList(applicantList);

        System.out.println("Student ID: u1818267");
        System.out.println("\n************************************");
        System.out.println("   WELCOME TO AGENCY APPLICATION   ");
        System.out.println("************************************\n");

        do {
            //This the Main menu of the application
            System.out.println("-------- MAIN MENU ---------\n");
            System.out.println("1. Go to Job Section");
            System.out.println("2. Go to Applicant Section");
            System.out.println("3. Display Matches");
            System.out.println("4. Exit the Application\n");
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
                    case 4 -> {
                        //The files will be written and saved when the user chooses option 4
                        JobFileHandler.writeJobList(jobList);
                        ApplicantFileHandler.writeApplicantList(applicantList);
                        System.out.println("Thank you for testing the Agency Menu!");
                    }
                    // The default will be called if the choice number is not between 1 and 4
                    default -> System.out.println("Enter numbers 1 to 4 only!\n");
                }

                //Catch block is executed if the user enters a value that is not a digit
            } catch (InputMismatchException e) {
                System.out.println(e);
            }

        } while (choice != 4); //Execute this loop as long as the choice is not equal to 4
    }

    /**
     * The following methods are tailored for the Main Menu
     */

    //----------------------------------------- BEGINNING OF METHODS FROM MAIN MENU ---------------------------------------------------


    // Option 1 - Go to Job Section
    static void option1() {

        int choice = 0; //Initialize the choice
        jobList = new Job_List();

        //Menu
        do {
            System.out.println("\n--------- Job Menu ---------\n");
            System.out.println("\t1. Add Job");
            System.out.println("\t2. Display a job");
            System.out.println("\t3. Update job");
            System.out.println("\t4. Remove a job");
            System.out.println("\t5. Display all jobs");
            System.out.println("\t6. Go Back to Main Menu\n");

            try {
                //Get a choice
                System.out.print("Enter choice: ");
                choice = EasyScannerPlus.nextInt();
                System.out.println();

                //Process the user choice
                switch (choice) {
                    case 1 -> jobOption1();
                    case 2 -> jobOption2();
                    case 3 -> jobOption3();
                    case 4 -> jobOption4();
                    case 5 -> jobOption5();
                    case 6 -> { System.out.println("Going to back Main Menu...\n");
                    Thread.sleep(2000);
                    }
                    default -> System.out.println("Enter options 1 to 6 only!\n");
                }
                //Catch block is executed if the user enters a value that is not a digit
                //Also this block executes the AgencyException
            } catch (InputMismatchException | AgencyException | InterruptedException e) {
                System.out.println("\n" + e + "\n");
            }
        } while (choice != 6); //Execute this loop as long as the choice is not equal to 6
    }

    // Option 2 - Go to Applicant Section
    static void option2() {
        int choice = 0;
        do {
            //Menu
            System.out.println("\n--------- Applicant Menu ---------\n");
            System.out.println("\t1. Add applicant");
            System.out.println("\t2. Remove applicant");
            System.out.println("\t3. Update applicant");
            System.out.println("\t4. Get applicant details");
            System.out.println("\t5. Display  Applicants ");
            System.out.println("\t6. Match a job");
            System.out.println("\t7. Go back to Main Menu\n");

            try {
                //Get choice
                System.out.print("Enter a choice: ");
                choice = EasyScannerPlus.nextInt();
                System.out.println();
                //Process the user choice
                switch (choice) {
                    case 1 -> applicantOption1();
                    case 2 -> applicantOption2();
                    case 3 -> applicantOption3();
                    case 4 -> applicantOption4();
                    case 5 -> applicantOption5();
                    case 6 -> applicantOption6();
                    case 7 -> {
                        System.out.println("Going to back Main Menu...\n");
                        Thread.sleep(2000); //Sleep the application for 2 seconds
                    }
                    default -> System.out.println("Enter choices between 1 and 7 only!\n");
                }
                //Catch block that will be executed if the user enters a character instead a number
                // This catch block executes the Agency Exception
                // We catch the InterruptedException when a thread waits or sleeps, and other threads are interrupted and cannot proceed further

            } catch (InputMismatchException | AgencyException | InterruptedException e) {
                System.out.println("\n"+e +"\n");
            }

        } while (choice != 7); //Execute this loop as long as the choice is not equal to 7
    }

    //Option 3 - Output applicants that match with the jobs on the list by checking the skills and the experience
    static void option3() {

        try {
            if (myMatches.isEmpty()) {
                System.out.println("There are no matches recorded!\n");
            } else {
                System.out.println("---------- Matches ----------\n");
                // For loop that returns a Collection view of the values contained in the map.
                for (String i : myMatches.keySet()) {

                    //Check if the applicant is present and the job is present
                    //Instead of output the job's ID from the map, this block outputs the job's name.
                    if (applicantList.getApplicant(i).isPresent() && jobList.getJob(myMatches.get(i)).isPresent()) {
                        System.out.println("Applicant's name: "+ applicantList.getApplicant(i).get().getName().toUpperCase()+"\n" +
                                "Job matched: "+ jobList.getJob(myMatches.get(i)).get().getJobTitle().toUpperCase()+"\n\n");
                    }
                }
            }
        } catch (NoSuchElementException e){
            System.out.println(e);
        }
    }

    //----------------------------------------- END OF METHODS FROM MAIN MENU ---------------------------------------------------

    /**
     * The following methods will be called in the method option1() - Job section
     */

    // --------------------------------------- BEGINNING OF METHODS FOR JOBS SECTION --------------------------------------------

    //Add a job
    static void jobOption1() {
        try {

            String type;
            System.out.print("Choose a ID for a job of length 4: ");
            String ID = EasyScannerPlus.nextString();
            System.out.print("Enter job name: ");
            String jobName = EasyScannerPlus.nextString();
            System.out.print("Enter job location: ");
            String location = EasyScannerPlus.nextString();
            System.out.println("Contract Types: ");
            System.out.println("\t1. Full-Time");
            System.out.println("\t2. Part-Time" );
            System.out.println("\t3. Placement");
            System.out.println("\t4. Internship");
            System.out.print("Enter type of contract: ");
            type = EasyScannerPlus.nextString();
            while (Integer.parseInt(type) < 1 || Integer.parseInt(type) > 4) {
                System.out.print("\nNo such option!Enter type of contract 1 - 4: ");
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

            /*
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type.
             */
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    //Get a job
    static void jobOption2() {
        System.out.print("Enter job ID: ");
        String ID = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        Optional<Job> job = jobList.getJob(ID);
        System.out.println();
        if (job.isPresent()) { //Check if the job is present in the list
            System.out.println(job);
        } else {
            System.out.println("Job not found in the List!\n");
        }
    }

    //Update Job
    static void jobOption3() {

        String ID;
        System.out.print("Enter job ID: ");
        ID = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        Optional<Job> job = jobList.getJob(ID);
        System.out.println();
        if (!job.isPresent()) { //If the job is NOT present in the list execute this block of code
            System.out.println("This job isn't registered!\n");
        } else {
            char answer;
            int choice = 0;

            //Update Menu.The user chooses what part of the job has to be updated.
            System.out.print("Would you like to update the job details? (y/n) ");
            answer = EasyScannerPlus.nextChar();
            do {
                if (answer == 'y') {
                    System.out.println("\n\t\t1. Update Title");
                    System.out.println("\t\t2. Update Location");
                    System.out.println("\t\t3. Update Type of Contract");
                    System.out.println("\t\t4. Update Skill");
                    System.out.println("\t\t5. Update Salary");
                    System.out.println("\t\t6. Update Experience");
                    System.out.println("\t\t7. Go back to Applicant Menu\n");
                    System.out.print("\t\tWhat would you like to update? (1-7) ");
                    choice = EasyScannerPlus.nextInt();
                    try {
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
                                System.out.println("\t\tCurrent Type: " + jobList.getJob(ID).get().getType());
                                System.out.println("\t\tContract Types: ");
                                System.out.println("\t\t\t1. Full-Time");
                                System.out.println("\t\t\t2. Part-Time" );
                                System.out.println("\t\t\t3. Placement");
                                System.out.println("\t\t\t4. Internship");
                                System.out.println("\t\t\t5. Keep Contract");
                                System.out.print("\t\tEnter  new type of contract: ");
                                type = EasyScannerPlus.nextString();
                                while (Integer.parseInt(type) < 1 || Integer.parseInt(type) > 5) {
                                    System.out.print("\t\tEnter values 1 to 4 only: ");
                                    type = EasyScannerPlus.nextString();
                                }
                                // An enhanced switch to update the contract of the job
                                switch (type) {
                                    case "1" -> {
                                        type = String.valueOf(JobTypeOfContract.FULL_TIME);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("\t\tNew Contract: " + JobTypeOfContract.FULL_TIME);
                                    }
                                    case "2" -> {
                                        type = String.valueOf(JobTypeOfContract.PART_TIME);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("\t\tNew Contract: " + JobTypeOfContract.PART_TIME +'\n');
                                    }
                                    case "3" -> {
                                        type = String.valueOf(JobTypeOfContract.WORK_PLACEMENT);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("\t\tNew Contract: " + JobTypeOfContract.WORK_PLACEMENT +'\n');
                                    }
                                    case "4" -> {
                                        type = String.valueOf(JobTypeOfContract.INTERNSHIP);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("\t\tNew Contract: " + JobTypeOfContract.INTERNSHIP +'\n');
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
                                System.out.println("Current Experience: " + jobList.getJob(ID).get().getType());
                                System.out.print("Enter new Experience: ");
                                int newExperience = EasyScannerPlus.nextInt();
                                int newJobExperience = jobList.getJob(ID).get().setExperience(newExperience);
                                System.out.println("New Experience: " + newJobExperience);
                            }
                            case 7 ->  {
                                System.out.println("Going to back Job Menu...\n");
                                Thread.sleep(2000); // Make the application to sleep for 2 seconds
                            }
                            default -> System.out.println("\t\tChoices 1 and 7 only!");
                        }

                        /*
                          Catch block to be executed if choice is not a digit. Also, we catch the InterruptedException
                          when a thread waits or sleeps, and other threads are interrupted and cannot proceed further.
                          The block also catches the user choice is not a digit.
                         */
                    } catch (InputMismatchException | InterruptedException e) {
                        System.out.println(e);
                    }
                }
            } while (choice != 7); //Execute this loop as long as the choice is not equal to 7
        }
    }

    //Method to remove a job
    static void jobOption4() {
        System.out.print("Enter job ID: "); //Retrieve the Job ID
        String jobID = EasyScannerPlus.nextString();
        Optional<Job> job = jobList.getJob(jobID);
        if (job.isPresent()) {

            System.out.print("WARNING: Deleting this job may affect the Applicants that match with this job.\nDelete this job? (y/n) ");
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

    //Method to display 5 records from the list. Throws an AgencyException
    static void jobOption5() throws AgencyException {
        int fromWhere;
        System.out.print("Where would you like to start seeing the records? ");
        fromWhere = EasyScannerPlus.nextInt();
        jobList.displayJobRecords(fromWhere);
    }

    // -------------------------------------------- END OF METHODS FOR JOBS SECTION ------------------------------------------------------



    /**
     * The following methods will be called in the method option2() - Applicant Section
     */

    //----------------------------------------- BEGINNING OF METHODS FOR APPLICANTS SECTION ---------------------------------------------------

    //Add applicant
    static void applicantOption1() {

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
                    Applicant applicant = new Applicant(appName, email, skill_1, skill_2, appExperience);
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
    static void applicantOption2()  {

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
    static void applicantOption3() {
        String email;
        System.out.print("Enter your email: ");
        email = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        System.out.println();
        if (!applicantList.getApplicant(email).isPresent()) { //Check if the job is present in the list
            System.out.println("This applicant is not registered\n");
        } else {
            char answer;
            int choice = 0;
            System.out.print("Would you like to update the job details? (y/n) ");
            answer = EasyScannerPlus.nextChar();
            do {
                if (answer == 'y') {
                    System.out.println("\t\t1. Update Name.");
                    System.out.println("\t\t2. Update Skill 1.");
                    System.out.println("\t\t3. Update Skill 2.");
                    System.out.println("\t\t4. Update Experience.");
                    System.out.println("\t\t5. Go back to Applicant Menu.\n");
                    System.out.print("What would you like to update? (1-4) ");
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

                        /*
                          Catch block to be executed if choice is not a digit. Also, we catch the InterruptedException
                          when a thread waits or sleeps, and other threads are interrupted and cannot proceed further.
                          The block also catches the user choice is not a digit.
                         */
                    } catch (InputMismatchException | InterruptedException e) {
                        System.out.println(e);
                    }
                }
            } while (choice != 5);
        }
    }

    //Get applicant details
    static void applicantOption4() {
        System.out.print("Enter your email: ");
        String position = EasyScannerPlus.nextString();

        //Retrieve the applicant using the Optional class
        Optional<Applicant> applicant = applicantList.getApplicant(position);

        if (applicant.isPresent()) {  //Check if the applicant is present in the list
            System.out.println(applicant.toString());
        } else {
            System.out.println("Applicant not found in the list!\n");
        }
    }

    //Method to display 5 records from the list. Throws an AgencyException
    static void applicantOption5() throws AgencyException {

        int fromWhere;
        System.out.print("Where would you like to start seeing the records? ");
        fromWhere = EasyScannerPlus.nextInt();

        applicantList.displayApplicantRecords(fromWhere);
    }

    //Match an applicant with a job
    static void applicantOption6() {

        if (jobList.isEmpty()) {
            System.out.println("Job Match not available as there are no jobs registered!");
        } else {
            System.out.print("Enter your Email: ");
            String email = EasyScannerPlus.nextString();
            Optional<Applicant> findApplicant = applicantList.getApplicant(email);

            if (findApplicant.isEmpty()) {
                System.out.println("Applicant not Found!\n");
            } else {
                //For Loop to show the user the available jobs
                System.out.println("\t\t\t\t*** Available Jobs ***");
                for (Job job : jobList.jList) {
                    System.out.println("Job ID = " + job.getJobId() + "\t\t Job Title = " + job.getJobTitle() + "\t\tLocation = " + job.getLocation() + "\n");
                }

                //Get the job id from the job list shown above
                System.out.print("Enter job ID: ");
                String jobID = EasyScannerPlus.nextString();

                //Check if the user enters a valid job ID from the list above
                Optional<Job> findJob = jobList.getJob(jobID);
                if (findJob.isEmpty()) {
                    System.out.println("Error: Check the List above to see the available jobs.\n");
                } else {
                    // Execute this block of code if one of the applicant's skills match with the chosen job
                    if ((findApplicant.get().getSkill_1().equalsIgnoreCase(findJob.get().getPrimarySkill()) ||
                            findApplicant.get().getSkill_2().equalsIgnoreCase(findJob.get().getPrimarySkill())) &&
                            findApplicant.get().getYourExperience() >= findJob.get().getExperience()){

                        myMatches.put(email, jobID);
                        System.out.println("Congratulations: You have the skills necessary for this job!\n");

                    } else {
                        System.out.println("\nI m sorry! This job does not match you!");
                        System.out.println("REASON: You must have the skill required for the job AND the minimum experience required!\n");
                    }
                }
            }
        }
    }
    //----------------------------------------- END OF METHODS FOR APPLICANTS SECTION ---------------------------------------------------
}