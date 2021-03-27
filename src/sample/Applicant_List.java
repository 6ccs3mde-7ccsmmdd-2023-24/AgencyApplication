package sample;

/**
 * Collection class to hold a list of Applicants
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
import java.util.ArrayList;
import java.util.Optional;

public class Applicant_List {

    //Declare two lists
    ArrayList<Applicant> aList;
    Job_List allJobs;

    /**
     * Constructor to initialize the Applicant List
     */
    public Applicant_List() {
        aList = new ArrayList<>();
        allJobs = new Job_List();
    }

    /**
     * Method to search an applicant by its email
     * @param email The applicant email
     * @return return the applicant that matches with the param, otherwise a dummy value
     */
    public int search(String email) {
        for (int i = 0; i <= aList.size() - 1; i++) {
            Applicant tempApplicant = aList.get(i);
            String tempNumber = tempApplicant.getEmail();
            if (tempNumber.equals(email)) {
                return i;
            }
        }
        return -999;
    }

    /**
     *  A boolean method to add an applicant to the end list
     * @param applicantIn The applicant to  be added to the list
     * @return Returns true if the applicant was added without problems and false if a problem
     * has occurred
     */
    public  boolean addApplicant(Applicant applicantIn) {
        if (search(applicantIn.getEmail()) == -999) {
            aList.add(applicantIn);
            return true;
        }
        return false;
    }

    /**
     *  A boolean method to remove an applicant from the list
     * @param email The applicant to add
     * @return Returns true if the applicant was removed without problems and false otherwise
     */
    public boolean removeApplicant(String email) {
        int index = search(email);

        if (index != -999) {
            aList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method that will help match applicants and registered jobs
     * @param jobName The job name
     */
    public void matchJob(String jobName) {

        allJobs.getJob(jobName);
    }

    /**
     * A Optional method to retrieve an applicant by the parameter emailIn
     * @param emailIn The email of the job
     * @return Returns the applicant or returns empty(null) Optional value
     */
    public Optional<Applicant> getApplicant(String emailIn) {
        int index = search(emailIn);
        if (index != -999) {
            return Optional.of(aList.get(index));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Gets the total of applicants in the list
     * @return Returns the total number of applicants currently in the list
     */
    public int getTotal() {
        return aList.size();
    }

    /**
     * Method to check if the list is empty
     * @return Returns true if the list is empty and false otherwise
     */
    public boolean isEmpty() {
        return aList.isEmpty();
    }

    /**
     * toString method that overwrites its original format from the Applicant class
     * @return Returns the alist as a toString
     */
    @Override
    public String toString() {
        return aList.toString();
    }
}
