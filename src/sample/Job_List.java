package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Collection class to hold a list of Jobs
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
public class Job_List {

    //Declare a list
    ArrayList<Job> jList ;

    /**
     * Constructor to initialize the the Job List
     */
    public Job_List() {

        //Initialize the list
        jList = new ArrayList<>();
    }

    /**
     * Method to search a job by its ID
     * @param ID The job ID
     * @return return the job that match with the param, otherwise a dummy value
     */
    private int search(String ID) {
        for (int i = 0; i <= jList.size() - 1; i++) {
            Job tempJobID =  jList.get(i);
            String tempID = tempJobID.getJobId();
            if (tempID.equals(ID)) {
                return i ;
            }
        }
        return -999;
    }

    /**
     *  A boolean method to add a job to the  end list
     * @param jobIn The job to add
     * @return Returns true if the object was added without problems and false if a problem
     * has occurred
     */
    public boolean addJob(Job jobIn) {
        if (search(jobIn.getJobId()) == -999) {
            jList.add(jobIn);
            return true;
        }
        return false;
    }

    /**
     * A boolean method to delete a job from the list
     * @param ID The job ID
     * @return Returns true is the the job is found and false otherwise
     */
    public boolean removeJob(String ID) {
        int index = search(ID);
        if (index != -1) {
            jList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * A Optional method to retrieve a job by the parameter ID
     * @param ID The ID of the job
     * @return Returns the job or returns empty(null) Optional value
     */
    public Optional<Job> getJob(String ID) {
        int index = search(ID);
        if (index != -999) {
            return Optional.of(jList.get(index));

        } else {
            return Optional.empty();
        }
    }

    /**
     * Gets the total of jobs in the list
     * @return Returns the total number of jobs currently in the list
     */
    public int getTotalOfJobs() {

        return jList.size();
    }

    /**
     * Method to check if the list is empty
     * @return Returns true if the list is empty and false otherwise
     */
    public boolean isEmpty() {
        return jList.isEmpty();
    }

    /**
     * Method to display 5 records from a position. Instead of showing the whole list,
     * the user chooses the position he/she wishes to start seeing the records.
     * @param startPosition
     */
    public  void displayJobRecords( int  startPosition) throws AgencyException {
        if (jList.isEmpty()) {  //Check if the list is empty
            System.out.println("\nList is empty!\n");
        } else {
            //Throw AgencyException when the user wants to start seeing jobs from an invalid position
            if (startPosition < 1 || startPosition > jList.size()) {
                throw new AgencyException("Error: Nothing to retrieve in this position");
            } else {
                System.out.println("\nTotal of jobs: " + jList.size());
                System.out.println();
                for (int count = 0; count < 5; count++) {
                    int i = count+ startPosition - 1;
                    if (i >= jList.size()) {
                        break;
                    }
                    System.out.print((count + 1)+ ")");

                    System.out.println(jList.get(i).toString());
                    System.out.println("\nThis job is currently at position "+ i+ " in the list.\n");
                    System.out.println("----------------------------------------------");
                }
            }
        }
    }

    /**
     * toString method that overwrites its original format from the Job class
     * @return Returns the jList as a toString
     */
    @Override
    public String toString() {
        return jList.toString();
    }
}
