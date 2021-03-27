package sample;

/**
 * Class used to record the details of the Applicants
 * @author Alexandro Cipriano da Silva Filho
 */
public class Applicant  implements Checkable{

    private String name;
    private String email;
    private String skill_1;
    private String skill_2;
    private int yourExperience;

    /**
     * Constructor to initialize the the applicant name, email, skills and experience
     * @param name Name of the applicant
     * @param email Email of the applicant
     * @param skill_1 Skill 1 of the applicant
     * @param skill_2 Skill 2 of the applicant
     * @param yourExperience Experience of the applicant
     */
    public Applicant(String name, String email, String skill_1, String skill_2, int yourExperience) {
        this.name = name;
        this.email = email;
        this.skill_1 = skill_1;
        this.skill_2 = skill_2;
        this.yourExperience = yourExperience;
    }

    /**
     * A boolean method that overwrites the check method from Checkable interface.
     * Its aim is to check if the applicant name and the skills does not contain digits
     * @return Returns false if the name, skill 1 or skill 2 contains digits and true otherwise.
     */
    public boolean check() {
        //For loop to check name
        for (int i = 0; i < getName().length(); i++) {
            if (Character.isDigit(getName().charAt(i))) {
                return false;
            }
        }

        //For loop to check skill 1
        for (int i = 0; i < getSkill_1().length(); i++) {
            if (Character.isDigit(getSkill_1().charAt(i))) {
                return false;
            }
        }

        //For loop to check skill 2
        for (int i = 0; i < getSkill_2().length(); i++) {
            if (Character.isDigit(getSkill_2().charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reads the name of the applicant
     * @return Returns the name of the applicant
     */
    public String getName() {
        return name;
    }
    /**
     * Reads the email of the applicant
     * @return Returns the email of the applicant
     */

    public String getEmail() {
        return email;
    }

    /**
     * Reads the skill1 of the applicant
     * @return Returns the skill1 of the applicant
     */
    public String getSkill_1() {
        return skill_1;
    }

    /**
     * Reads the skill 2 of the applicant
     * @return Returns the skill 2 of the applicant
     */
    public String getSkill_2() {
        return skill_2;
    }

    /**
     * Reads the experience of the applicant
     * @return Returns the experience of the applicant
     */
    public int getYourExperience() {
        return yourExperience;
    }

    /**
     * This is an  in-built method that returns the value given to it in string format
     * @return Returns the value given in string format. (The Applicant details)
     */
    @Override
    public String toString() {
        return '\n'+
                "\tName: "+ name.toUpperCase() + '\n'+
                "\tEmail: "+ email.toUpperCase()+ '\n'+
                "\tSkill 1: "+ skill_1.toUpperCase() + '\n'+
                "\tSkill 2: "+ skill_2.toUpperCase()+ '\n'+
                "\tExperience: "+ yourExperience +" years. "+ '\n';

    }
}
