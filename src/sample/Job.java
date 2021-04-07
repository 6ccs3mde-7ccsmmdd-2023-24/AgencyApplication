package sample;

import java.text.DecimalFormat;

/**
 * This class is used to register details of a job in the Agency
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
public class Job  implements Checkable {

    private String jobId;
    private String jobTitle;
    private String location;
    private JobTypeOfContract type;
    private String primarySkill;
    private double salary;
    private int experience;


    /**
     * Constructor to initialize the Job title, its main skill and the minimum of
     * experience required for the job
     * @param jobId : Job ID
     * @param jobTitle : Job name
     * @param location : Job Location
     * @param type : Job description
     * @param primarySkill : Job primary Skill
     * @param salary : Job salary
     * @param experience :  of years of experience required
     */
    public Job(String jobId, String jobTitle, String location, JobTypeOfContract type, String primarySkill, double salary, int experience) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.location = location;
        this.type = type;
        this.primarySkill = primarySkill;
        this.salary = salary;
        this.experience = experience;
    }

    /**
     * A boolean method that overwrites the check method from Checkable interface.
     * Its aim is to check if the  job ID contains 4 characters
     * @return Returns false if the ID length is not equal to 4 and true otherwise
     */
    public boolean check() {

        //Check if the Job length is equal to 4
        return getJobId().length() == 4;

    }

    /**
     * Reads the Job ID
     * @return the Job ID
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * Reads the Job title
     * @return the Job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Reads the Job Location
     * @return the Job Location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Reads the Job Description
     * @return the Job Description
     */
    public JobTypeOfContract getType() {
        return type;
    }
    /**
     * Reads the Job main skill required
     * @return Returns the main skill required
     */
    public String getPrimarySkill() {
        return primarySkill;
    }

    /**
     * Reads the Job Salary
     * @return the Job Salary
     */
    public double getSalary() {

        return salary;
    }

    /**
     * Reads the minimum of years of experience required for the job
     * @return Returns the minimum of years of experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets the job Title
     * @param jobTitle The new job Title
     * @return Returns the new job Title
     */
    public String setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return jobTitle;
    }

    /**
     * Sets the job Location
     * @param location The new job Location
     * @return Returns the new job Location
     */
    public String setLocation(String location) {
        this.location = location;
        return location;
    }

    /**
     * Sets the job type of contract
     * @param type The new job type of contract
     * @return Returns the new job type of contract
     */
    public JobTypeOfContract setType(JobTypeOfContract type) {
        this.type = type;
        return type;
    }

    /**
     * Sets the job main skill required
     * @param primarySkill The new job main skill required
     * @return Returns the new job main skill required
     */
    public String setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
        return primarySkill;
    }

    /**
     * Sets the job salary
     * @param salary The new job salary
     * @return Returns the new job salary
     */
    public Double setSalary(double salary) {
        this.salary = salary;
        return salary;
    }

    /**
     * Sets the job experience
     * @param experience The new job experience
     * @return Returns the new job experience
     */
    public int setExperience(int experience) {
        this.experience = experience;
        return experience;
    }

    /**
     * This is an  in-built method that returns the value given to it in string format
     * @return Returns the value given in string format. (The job details)
     */
    @Override
    public String toString() {

        //Format the salary
        DecimalFormat decimalFormat = new DecimalFormat("###,##0");
        return "\n" +
                "\tJob ID = " + jobId.toUpperCase() + '\n' +
                "\tJob Title = " + jobTitle.toUpperCase() + '\n' +
                "\tLocation = " + location.toUpperCase() + '\n' +
                "\tType = " + type + '\n' +
                "\tPrimary Skill = " + primarySkill.toUpperCase() + '\n' +
                "\tSalary £ = " + decimalFormat.format(salary) + " a year\n" +
                "\tExperience = " + experience + " years.\n";
    }
}
