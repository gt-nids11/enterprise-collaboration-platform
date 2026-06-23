package authentication;

import database.Database;
import exception.AuthenticationException;
import exception.UserAlreadyExistsException;
import exception.ValidationException;
import models.Role;
import models.Session;
import models.User;
import models.UserStatus;

public class AuthenticationService {

    private final Database database;
    private final SessionManager sessionManager;

    public AuthenticationService(Database database) {
        this.database = database;
        this.sessionManager = new SessionManager();
    }

    // -------------------- REGISTER --------------------

    public User register(String username,
                         String email,
                         String password,
                         Role role)
            throws ValidationException, UserAlreadyExistsException {

        Validator.validateUsername(username);
        Validator.validatePassword(password);

        if (database.userExists(username)) {
            throw new UserAlreadyExistsException("Username already exists.");
        }

        if (database.emailExists(email)) {
            throw new UserAlreadyExistsException("Email already registered.");
        }

        String hashedPassword = PasswordHasher.hashPassword(password);

        User user = new User(
                username,
                email,
                hashedPassword,
                role
        );

        database.saveUser(user);

        return user;
    }

    // -------------------- LOGIN --------------------

    public Session login(String username, String password)
            throws AuthenticationException {

        User user = database.getUser(username);

        if (user == null) {
            throw new AuthenticationException("User does not exist.");
        }

        boolean valid = PasswordHasher.verify(password, user.getPasswordHash());

        if (!valid) {
            throw new AuthenticationException("Invalid password.");
        }

        Session session = new Session(user);
        sessionManager.createSession(user);
        return session;
    }

    // -------------------- LOGOUT --------------------

    public void logout(Session session) {

        if (session == null) {
            return;
        }

        User user = session.getUser();

        user.logout();

        sessionManager.invalidateSession(session.getSessionId());
    }

    // -------------------- CHANGE PASSWORD --------------------

    public void changePassword(User user,
                               String oldPassword,
                               String newPassword)
            throws AuthenticationException, ValidationException {

        if (!PasswordHasher.verify(oldPassword, user.getPasswordHash())) {
            throw new AuthenticationException("Old password is incorrect.");
        }

        Validator.validatePassword(newPassword);

        user.updatePassword(
                PasswordHasher.hashPassword(newPassword)
        );
    }

    // -------------------- GET USER --------------------

    public User getUser(String username) {
        return database.getUser(username);
    }

    // -------------------- SESSION --------------------

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}