package authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class responsible for hashing passwords.
 *
 * Uses SHA-256 hashing algorithm.
 *
 * @author Nidhi Patil
 * @version 1.0
 */
public final class PasswordHasher {

    private static final String HASH_ALGORITHM = "SHA-256";

    private PasswordHasher() {

    }

    /**
     * Generates SHA-256 hash of a password.
     *
     * @param password plain text password
     * @return hashed password
     */
    public static String hashPassword(String password) {

        try {

            MessageDigest digest =
                    MessageDigest.getInstance(HASH_ALGORITHM);

            byte[] hash =
                    digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder builder = new StringBuilder();

            for (byte value : hash) {

                builder.append(String.format("%02x", value));

            }

            return builder.toString();

        } catch (NoSuchAlgorithmException exception) {

            throw new RuntimeException("Unable to hash password.", exception);

        }

    }

    /**
     * Verifies a password against its hash.
     *
     * @param password plain text password
     * @param hash previously hashed password
     * @return true if password matches hash, false otherwise
     */
    public static boolean verify(String password, String hash) {

        String passwordHash = hashPassword(password);

        return passwordHash.equals(hash);

    }

    /**
     * Static method alias for backward compatibility.
     *
     * @param password plain text password
     * @return hashed password
     */
    public static String hash(String password) {

        return hashPassword(password);

    }

}