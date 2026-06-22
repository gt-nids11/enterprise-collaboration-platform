package exception;

/**
 * Base exception for the entire application.
 *
 * Every custom exception extends this class.
 *
 * @author Nidhi Patil
 * @version 1.0
 */
public class ApplicationException extends Exception {

    /**
     * Creates an exception with a message.
     *
     * @param message error message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and cause.
     *
     * @param message error message
     * @param cause original exception
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

}