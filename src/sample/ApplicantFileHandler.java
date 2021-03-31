package sample;

import java.io.*;

public class ApplicantFileHandler {
    //Method for writing the Applicant file
    static void writeApplicantList(Applicant_List applicantList) {

        //Usage of the try-with-resources to close the file safely
        try (FileWriter applicantFile = new FileWriter("Applicants.txt");
             PrintWriter applicantWriter = new PrintWriter(applicantFile)) {

            //Write each element of the list to the file
            for (Applicant item : applicantList.aList) {

                applicantWriter.println(item.getEmail());
                applicantWriter.println(item.getName());
                applicantWriter.println(item.getSkill_1());
                applicantWriter.println(item.getSkill_2());
                applicantWriter.println(item.getYourExperience());
            }

            //Handle the exception thrown by the FileWriter methods
        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method for reading the  Applicant file
    static void readApplicantList(Applicant_List applicantList) {
        String email,name, skillOne, skillTwo;
        String stringExperience;
        int experience;

        //Usage of the try-with-resources to close the file safely
        try (FileReader applicantFile = new FileReader("Applicants.txt");
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
                Applicant myApplicant = new Applicant( email,name, skillOne, skillTwo, experience);
                applicantList.addApplicant(myApplicant);
                email = applicantStream.readLine();
            }
            //Handle the exception if the file is not found
        } catch (FileNotFoundException e) {
            System.out.println("Applicants.txt  not found!\n");
        } catch (NumberFormatException e) {
            System.out.print("");
        } catch (IOException e) { //Handle the exception thrown by the FileReader methods
            System.out.println("Problem with the file");
        }
    }
}
