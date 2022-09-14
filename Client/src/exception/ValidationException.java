package exception;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ValidationException extends Exception {

    public ValidationException(String validationErrorMessage) {
        super(validationErrorMessage);
    }

}
