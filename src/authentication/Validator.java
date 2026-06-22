package authentication;

import java.util.regex.Pattern;

public class Validator {

    private static final String USERNAME_REGEX = "^[A-Za-z0-9_]{4,20}$";

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    public static boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_REGEX, username);
    }

    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
