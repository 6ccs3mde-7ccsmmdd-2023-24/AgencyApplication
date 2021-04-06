package sample;

/**
 * AgencyException class created to extend the Exception class.
 * This class is used when we wish to display a list of jobs or
 * applicants by a position.
 * @author Alexandro Cipriano da Silva Filho
 * ID : u1818267
 */
public class AgencyException extends Exception {

    public AgencyException( String message) {
        super(message);
    }
}
