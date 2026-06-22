
package authentication;

import java.util.HashMap;
import java.util.Map;

import models.User;
import models.UserStatus;

public class AuthenticationService {

    private final Map<String, User> users;

    private final SessionManager sessionManager;

    public AuthenticationService() {

        users = new HashMap<>();

        sessionManager = new SessionManager();

    }

    public boolean register(String username,
                            String email,
                            String password) {

        if (!Validator.isValidUsername(username)) {

            System.out.println("Invalid Username");

            return false;
        }

        if (!Validator.isValidEmail(email)) {

            System.out.println("Invalid Email");

            return false;
        }

        if (!Validator.isValidPassword(password)) {

            System.out.println("Weak Password");

            return false;
        }

        if (users.containsKey(username)) {

            System.out.println("Username already exists");

            return false;
        }

        String hash = PasswordHasher.hashPassword(password);

        User user = new User(username, email, hash);

        users.put(username, user);

        System.out.println("Registration Successful");

        return true;

    }

    public boolean login(String username,
                         String password) {

        User user = users.get(username);

        if (user == null) {

            System.out.println("User not found");

            return false;
        }

        String hash = PasswordHasher.hashPassword(password);

        if (!user.getPasswordHash().equals(hash)) {

            System.out.println("Incorrect Password");

            return false;
        }

        sessionManager.login(user);

        user.setStatus(UserStatus.ONLINE);

        System.out.println("Login Successful");

        return true;

    }

    public void logout(String username) {

        User user = users.get(username);

        if(user != null){

            user.setStatus(UserStatus.OFFLINE);

            sessionManager.logout(username);

        }

    }

    public User findUser(String username){

        return users.get(username);

    }

    public void showRegisteredUsers(){

        for(User user : users.values()){

            System.out.println(user);

        }

    }

    public SessionManager getSessionManager(){

        return sessionManager;

    }

}