package sample;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * This is the main tester class that will implement both Job_list and Applicant_List.
 * The Job and applicant instructions will be added on the graphical interface.
 *
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
public class MainMenu_TESTER {

    private static Applicant_List applicantList;
    private static Job_List jobList;

    private static Map<String, String> myMatches = new HashMap<>();

    public static void main(String[] args) {

        int choice = 0; //Initialize the choice

        //Initialize the Lists
        jobList = new Job_List();
        applicantList = new Applicant_List();

        System.out.println("\n************************************");
        System.out.println("   WELCOME TO AGENCY APPLICATION   ");
        System.out.println("************************************\n");

        readJobList();
        readApplicantList();
        readMatches();

        do {
            //Menu
            System.out.println("1. Add a Job");
            System.out.println("2. Add applicant");
            System.out.println("3. Check job details");
            System.out.println("4. Display all Jobs");
            System.out.println("5. Display all Applicants ");
            System.out.println("6. Check applicant details");
            System.out.println("7. Remove applicant");
            System.out.println("8. Match a job");
            System.out.println("9. Display matches");
            System.out.println("10. Save and Leave\n");

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
                    case 6 -> option6();
                    case 7 -> option7();
                    case 8 -> option8();
                    case 9 -> option9();
                    case 10 -> {
                        writeJobList();
                        writeApplicantList();
                        writeMatches();
                        System.out.println("Thank you for testing the Agency Menu!");
                    }
                    default -> System.out.println("Enter choices between 1 and 10 only!\n");
                }
                //Catch block that will be executed if the user enters a character instead a number
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(e);
                System.out.println();
            }

        } while (choice != 10);
    }

    //Add Job
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
            if (salary < 0) { // If salary smaller than 1 then execute this code
                System.out.println("Salary cannot have a negative value!\n");
            } else {
                System.out.print("Minimum of years of experience: ");
                int experience = EasyScannerPlus.nextInt();
                if (experience < 0) { // If experience smaller than 0 then execute this code
                    System.out.println("Experience cannot have a negative value!\n");
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
                            System.out.println("Job added\n");
                        } else {
                            System.out.println("Job already on the system\n ");
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println( "\n"+e);
        }
    }

    //Add applicant
    static void option2() {

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
                System.out.print("Enter skill 2: ");
                String skill_2 = EasyScannerPlus.nextString();
                System.out.print("Years of experience: ");
                int appExperience = EasyScannerPlus.nextInt();

                if (appExperience < 0) {
                    System.out.println("\nYour experience cannot be negative!");
                } else {
                    //Declare objects to test
                    Applicant applicant = new Applicant(appName, email, skill_1, skill_2, appExperience);
                    boolean checkApplicant = applicant.check(); // call the check method from the Applicant class
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

            /*
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type.
             */
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        System.out.println();
    }


    //Check job details
    static void option3() {

        if (jobList.isEmpty()) {
            System.out.println("List is empty!\n");
        } else {
            System.out.print("Enter job ID: ");
            String ID = EasyScannerPlus.nextString();
            //Retrieve the job using the Optional class
            Optional<Job> j = jobList.getJob(ID);
            System.out.println();
            if (jobList.getJob(ID).isEmpty()) { //Check if the job is present in the list
                System.out.println("Error:This job isn't registered!");
            } else {
                System.out.println(j);
            }
            System.out.println();
        }
    }

    //Display total and Job list
    static void option4() {

        if (jobList.isEmpty()) {  //Check if the list is empty
            System.out.println("List is empty!\n");
        } else {
            System.out.println("Total of jobs: " + jobList.totalOfJobs());
            System.out.println();
            System.out.println("*** JOBS ****");
            System.out.println(jobList);
            System.out.println();
        }
    }

    //Display all Applicants details
    static void option5() {

        if (applicantList.isEmpty()) {
            System.out.println("Applicant list is empty\n");
        } else {
            System.out.println("**** Applicant Details ****");
            System.out.println(applicantList);
            System.out.println();
        }
    }

    //Check Applicant details
    static void option6() {

        System.out.print("Enter your email: ");
        String position = EasyScannerPlus.nextString();
        System.out.println();

        //Retrieve the applicant using the Optional class
        Optional<Applicant> applicant = applicantList.getApplicant(position);
        if (applicant.isPresent()) {  //Check if the applicant is present in the list
            System.out.println(applicant.toString());
        } else {
            System.out.println("Applicant not found in the list!\n");
        }
    }

    // Remove Applicant
    static void option7() {
        System.out.print("Enter your email: ");
        String email = EasyScannerPlus.nextString();


        if (applicantList.getApplicant(email).isPresent()) {
            System.out.println(applicantList.getApplicant(email).toString());


            //Check if the user wishes to proceed with the deletion
            System.out.print("Remove this applicant? (y/n) ");
            char remove = EasyScannerPlus.nextChar();
            if (remove == 'y' || remove == 'Y') { // if the input is one of formats execute this code
                applicantList.removeApplicant(email);
                //Attempt to remove applicant to the Applicant_List
                System.out.println("\nConfirmation: Applicant successfully removed!\n");
            } else {
                System.out.println("\nApplicant has been kept in the recording!\n");
            }
        } else {
            System.out.println("\nError: Applicant not found!\n");
        }
    }

    //Match an applicant with a job
    static void option8() {

        System.out.print("Enter your Email: ");
        String email = EasyScannerPlus.nextString();
        Optional<Applicant> findApplicant = applicantList.getApplicant(email);
        if (findApplicant.isEmpty()) {
            System.out.println("Applicant not Found!\n");
        } else {
            if (jobList.isEmpty()) {
                System.out.println("No jobs registered!\n");
            } else {
                System.out.println("\t\t\t\t*** Available Jobs ***");
                for (Job job : jobList.jList) {
                    System.out.println("Job ID = " + job.getJobId() + "\t\t Job Name = " + job.getJobTitle() + "\t\tLocation = " + job.getLocation() + "\n");
                }
                //Get the job id from the job list shown above
                System.out.print("Enter job ID: ");
                String jobID = EasyScannerPlus.nextString();

                Optional<Job> findJob = jobList.getJob(jobID);
                if (findJob.isEmpty()) {
                    System.out.println("No such job found! Check the List to see the available jobs\n");
                } else {
                    // Execute this block of code if one of the applicant's skills match with the chosen job
                        if ((applicantList.getApplicant(email).get().getSkill_1().equalsIgnoreCase(jobList.getJob(jobID).get().getPrimarySkill()) ||
                                applicantList.getApplicant(email).get().getSkill_2().equalsIgnoreCase(jobList.getJob(jobID).get().getPrimarySkill())) &&
                                applicantList.getApplicant(email).get().getYourExperience() >= jobList.getJob(jobID).get().getExperience()){

                            myMatches.put(email, jobID);
                            System.out.println("Congratulations: You have the skills necessary for this job!\n");
                        } else {
                            System.out.println("\nI m sorry! This job does not match you!");
                        System.out.println("REASON: You must have the skill required for the job and the minimum experience required!\n");
                        }
                }
            }
        }
    }

    //Output applicants that match with the jobs on the list, checking the skills and the experience
    static void option9() {

        try {
            if (myMatches.isEmpty()) {
                System.out.println("There are no matches recorded!\n");
            } else {
                System.out.println("*** Matches ***\n");
                for (String i : myMatches.keySet()) {
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


    //Method for writing the file
    public static void writeJobList() {

        //Usage of the try-with-resources to close the file safely
        try (FileWriter jobFile = new FileWriter("JobsTester.txt");
             PrintWriter jobWriter = new PrintWriter(jobFile)) {

            //To change the salary format
            DecimalFormat decimalFormat = new DecimalFormat("###,##0.0#");

            //Write each element of the list to the file
            for (Job item : jobList.jList) {
                jobWriter.print("ID: ");
                jobWriter.println(item.getJobId());
                jobWriter.print("Title: ");
                jobWriter.println(item.getJobTitle());
                jobWriter.print("Location: ");
                jobWriter.println(item.getLocation());
                jobWriter.print("Job Type: ");
                jobWriter.println(item.getType());
                jobWriter.print("Skill: ");
                jobWriter.println(item.getPrimarySkill());
                jobWriter.print("Salary: ");
                jobWriter.println(decimalFormat.format(item.getSalary()));
                jobWriter.print("Experience: ");
                jobWriter.println(item.getExperience());
                jobWriter.println();
            }
            //Handle the exception thrown by the FileWriter methods
        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method for reading the Job file
    public static void readJobList() {
        String ID, jobName, location, description, primarySkill, strSalary, strExperience;
        int experience;
        double salary;

        //Usage of the try-with-resources to close the file safely
        try (FileReader jobFile = new FileReader("JobsTester.txt");
             BufferedReader jobStream = new BufferedReader(jobFile)
        ) {
            ID = jobStream.readLine(); //To read the first line of the file
            while (ID != null) {

                //Read the remaining of the first record, then all the rest of records until the end of the file
                jobName = jobStream.readLine();
                location = jobStream.readLine();
                description = jobStream.readLine();
                primarySkill = jobStream.readLine();
                strSalary = jobStream.readLine();

                //Convert the salary from String to Integer
                salary = Double.parseDouble(strSalary);
                strExperience = jobStream.readLine();
                experience = Integer.parseInt(strExperience);
                Job myJob = new Job(ID, jobName, location, description, primarySkill, salary, experience);
                jobList.addJob(myJob);
                ID = jobStream.readLine();
            }
        }
        //Handle the exception if the file is not found
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (NumberFormatException e) {
            System.out.print("");
        }
        //Handle the exception thrown by the FileReader methods
        catch (IOException e) {
            System.out.println("Problem with the file\n");
        }
    }


    //Method for writing the Applicant file
    static void writeApplicantList() {

        //Usage of the try-with-resources to close the file safely
        try (FileWriter applicantFile = new FileWriter("ApplicantsTester.txt");
             PrintWriter applicantWriter = new PrintWriter(applicantFile)) {

            //Write each element of the list to the file
            for (Applicant item : applicantList.aList) {
                applicantWriter.print("Name: ");
                applicantWriter.println(item.getName());
                applicantWriter.print("Email: ");
                applicantWriter.println(item.getEmail());
                applicantWriter.print("Skill 1: ");
                applicantWriter.println(item.getSkill_1());
                applicantWriter.print("Skill 2: ");
                applicantWriter.println(item.getSkill_2());
                applicantWriter.print("Experience: ");
                applicantWriter.println(item.getYourExperience());
                applicantWriter.println();
            }

            //Handle the exception thrown by the FileWriter methods
        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method for reading the  Applicant file
    static void readApplicantList() {
        String name, email, skillOne, skillTwo;
        String stringExperience;
        int experience;

        //Usage of the try-with-resources to close the file safely
        try (FileReader applicantFile = new FileReader("ApplicantsTester.txt");
             BufferedReader applicantStream = new BufferedReader(applicantFile)) {
            email = applicantStream.readLine(); //To read the first line of the file
            while (email != null) {

                //Read the remaining of the first record, then all the rest of records until the end of the file
                name = applicantStream.readLine();
                skillOne = applicantStream.readLine();
                skillTwo = applicantStream.readLine();
                stringExperience = applicantStream.readLine();

                //Convert the salary from String to Integer
                experience = Integer.parseInt(stringExperience);
                Applicant myApplicant = new Applicant(name, email, skillOne, skillTwo, experience);
                applicantList.addApplicant(myApplicant);
                email = applicantStream.readLine();

            }

            //Handle the exception if the file is not found
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (NumberFormatException e) {
            System.out.print("");
        } catch (IOException e) { //Handle the exception thrown by the FileReader methods
            System.out.println("Problem with the file");
        }
    }

    //Method for writing the Matches file
    static void writeMatches() {
        try (FileWriter matchFile = new FileWriter("MatchesTester.txt");
             PrintWriter matchWriter = new PrintWriter(matchFile)) {

            for (String matches : myMatches.keySet()) {

                if (applicantList.getApplicant(matches).isPresent() && jobList.getJob(myMatches.get(matches)).isPresent()) {
                    matchWriter.print("Applicant Name: ");
                    matchWriter.println(applicantList.getApplicant(matches).get().getName());
                    matchWriter.print("Job matched: ");
                    matchWriter.println(jobList.getJob(myMatches.get(matches)).get().getJobTitle());
                    matchWriter.println();
                }

            }
        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method fo reading the Matches file
    static void readMatches() {
        String applicantName, jobName;

        try (FileReader matchFile = new FileReader("MatchesTester.Txt");
            BufferedReader matchStream = new BufferedReader(matchFile)
        ){
            applicantName = matchStream.readLine();
            while (applicantName != null) {
                jobName = matchStream.readLine();
                myMatches.put(applicantName, jobName);
                applicantName = matchStream.readLine();
            }
        } //Handle the exception if the file is not found
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (NumberFormatException e) {
            System.out.print("");
        }
        //Handle the exception thrown by the FileReader methods
        catch (IOException e) {
            System.out.println("Problem with the file\n");
        }
    }
}
