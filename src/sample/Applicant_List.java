package sample;

/**
 * Collection class to hold a list of Applicants
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
import java.util.ArrayList;
import java.util.Optional;

public class Applicant_List {

    //Declare a list
    ArrayList<Applicant> aList;


    /**
     * Constructor to initialize the Applicant List
     */
    public Applicant_List() {

        //Initialize the list
        aList = new ArrayList<>();
    }

    /**
     * Method  helper to search an applicant by its email
     * @param email The applicant email
     * @return return the applicant that matches with the param, otherwise a dummy value
     */
    private int search(String email) {
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
     * Method to display 5 records from a position. Instead of showing the whole list,
     * the user chooses the position he/she wishes to start seeing the records.
     * @param startPosition
     */
    public  void displayApplicantRecords( int  startPosition) throws AgencyException {
        if (aList.isEmpty()) {  //Check if the list is empty
            System.out.println("\nList is empty!\n");
        } else {
            //Throw AgencyException when the user wants to start seeing applicants from an invalid position
            if (startPosition < 1 || startPosition > aList.size()) {
                throw new AgencyException("Error: Nothing to retrieve in this position");
            } else {
                System.out.println("\nTotal of Applicants: " + aList.size());
                System.out.println();
                for (int count = 0; count < 5; count++) {
                    int i = count+ startPosition - 1;
                    if (i >= aList.size()) {
                        break;
                    }
                    System.out.print((count + 1)+ ")");

                    System.out.println(aList.get(i).toString());
                    System.out.println("\nThis applicant is currently at position "+ i+ " in the list.\n");
                    System.out.println("------------------------------------------------");
                }
            }
        }
    }


    /**
     * Gets the total of applicants in the list
     * @return Returns the total number of applicants currently in the list
     */
    public int getTotalOfApplicants() {
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
