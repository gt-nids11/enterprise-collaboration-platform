package authentication;

import exception.ValidationException;

/**
 * Performs input validation.
 *
 * All application validations must be placed here.
 *
 * @author Nidhi Patil
 * @version 1.0
 */
public final class Validator {

    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 8;

    private Validator() {

    }

    /**
     * Validates username.
     *
     * @param username username
     * @throws ValidationException if invalid
     */
    public static void validateUsername(String username)
            throws ValidationException {

        if (username == null || username.isBlank()) {

            throw new ValidationException("Username cannot be empty.");

        }

        if (username.length() < MIN_USERNAME_LENGTH) {

            throw new ValidationException(
                    "Username must contain at least "
                            + MIN_USERNAME_LENGTH + " characters.");

        }

    }

    /**
     * Validates password.
     *
     * @param password password
     * @throws ValidationException if invalid
     */
    public static void validatePassword(String password)
            throws ValidationException {

        if (password == null || password.isBlank()) {

            throw new ValidationException("Password cannot be empty.");

        }

        if (password.length() < MIN_PASSWORD_LENGTH) {

            throw new ValidationException(
                    "Password must contain at least "
                            + MIN_PASSWORD_LENGTH + " characters.");

        }

        if (!password.matches(".*[A-Z].*")) {

            throw new ValidationException(
                    "Password must contain an uppercase letter.");

        }

        if (!password.matches(".*[a-z].*")) {

            throw new ValidationException(
                    "Password must contain a lowercase letter.");

        }

        if (!password.matches(".*\\d.*")) {

            throw new ValidationException(
                    "Password must contain a digit.");

        }

    }

    /**
     * Validates email.
     *
     * @param email email
     * @throws ValidationException if invalid
     */
    public static void validateEmail(String email)
            throws ValidationException {

        if (email == null || email.isBlank()) {

            throw new ValidationException("Email cannot be empty.");

        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

            throw new ValidationException("Email format is invalid.");

        }

    }

}