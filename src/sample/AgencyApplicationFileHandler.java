package sample;

import java.io.*;
/**
 * Class used to record the details of the  jobs and Applicants
 * @author Alexandro Cipriano da Silva Filho
 */
public class AgencyApplicationFileHandler {

    //Method to write Job File
     public static void writeJobList(Job_List jobList) {
         try (FileWriter jobFile = new FileWriter("JobApplication.txt");
              PrintWriter jobWriter = new PrintWriter(jobFile)) {

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
                 jobWriter.println(item.getSalary());
                 jobWriter.print("Experience: ");
                 jobWriter.println(item.getExperience());
                 jobWriter.println();
             }
         } catch (IOException e) {
             System.out.println("There is a problem with the file!");
         }
    }

    //Method to read the Job File
    public static void readJobList(Job_List jobList) {
        String ID, jobName, location,description, primarySkill, strSalary, strExperience;
        int experience;
        double salary;

        try (FileReader jobFile = new FileReader("JobApplication.txt");
             BufferedReader jobStream = new BufferedReader(jobFile);
        ) {
            ID = jobStream.readLine();
            while (ID != null) {

                jobName = jobStream.readLine();
                location = jobStream.readLine();
                description = jobStream.readLine();
                primarySkill = jobStream.readLine();
                strSalary = jobStream.readLine();
                salary = Double.parseDouble(strSalary);
                strExperience = jobStream.readLine();
                experience = Integer.parseInt(strExperience);
                Job  myJob = new Job(ID,jobName,location,description,primarySkill,salary,experience);
                jobList.addJob(myJob);
                ID = jobStream.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (NumberFormatException e) {
            System.out.print("");
        } catch (IOException e) {
            System.out.println("Problem with the file");
        }
    }

    //Method to write the applicant File
    public static void writeApplicantList(Applicant_List applicantList) {
        try (FileWriter applicantFile = new FileWriter("ApplicantApplication.txt");
             PrintWriter applicantWriter = new PrintWriter(applicantFile)) {
            for (Applicant item : applicantList.aList) {
                applicantWriter.println(item.getName());
                applicantWriter.println(item.getEmail());
                applicantWriter.println(item.getSkill_1());
                applicantWriter.println(item.getSkill_2());
                applicantWriter.println(item.getYourExperience());
            }

        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method to read the Applicant File
    public static void readApplicantList(Applicant_List applicantList) {
        String name, email, skillOne, skillTwo;
        String stringExperience ;
        int experience;

        try (FileReader applicantFile = new FileReader("ApplicantApplication.txt");
             BufferedReader applicantStream = new BufferedReader(applicantFile)) {
            email = applicantStream.readLine();
            while (email != null) {
                name =  applicantStream.readLine();
                skillOne = applicantStream.readLine();
                skillTwo = applicantStream.readLine();
                stringExperience = applicantStream.readLine();
                experience = Integer.parseInt(stringExperience);
                Applicant myApplicant = new Applicant(name,email,skillOne,skillTwo,experience);
                applicantList.addApplicant(myApplicant);
                email = applicantStream.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (NumberFormatException e) {
            System.out.print("");
        } catch (IOException e) {
            System.out.println("Problem with the file");
        }
    }
}
