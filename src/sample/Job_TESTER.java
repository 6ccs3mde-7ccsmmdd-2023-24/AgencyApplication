package sample;

/**
 * Class to test the functionality of the Job class
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
public class Job_TESTER {
    public static void main(String[] args) {

        //Create two objects of the job class
        Job job1 = new Job("a123","Network Technician", "London, UK",
                "Full-time", "Computer Networks",
                19000,0);

        Job job2 = new Job( "u42233","Software Developer Graduate", "London, UK",
                "Placement","Java",24000, 2);

        System.out.println(checkValidity(job1));// It will output as Valid
        System.out.println(checkValidity(job2));// It will output as Invalid

    }

    /**
     * Method to check the validity of the Job ID
     * @param checkable The checkable to add
     * @return Returns true if the ID is valid and false if the ID is invalid
     */
    static String checkValidity(Checkable checkable) {
        if (checkable.check()) {

            return "Valid";
        } else {

            return "Invalid";
        }
    }
}
