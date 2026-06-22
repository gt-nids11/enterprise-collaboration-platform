package authentication;

import java.util.HashMap;
import java.util.Map;

import models.User;

public class SessionManager {

    private final Map<String, User> activeSessions;

    public SessionManager() {
        activeSessions = new HashMap<>();
    }

    public void login(User user) {
        activeSessions.put(user.getUsername(), user);
    }

    public void logout(String username) {
        activeSessions.remove(username);
    }

    public boolean isLoggedIn(String username) {
        return activeSessions.containsKey(username);
    }

    public User getLoggedInUser(String username) {
        return activeSessions.get(username);
    }

    public void showActiveUsers() {

        if(activeSessions.isEmpty()){

            System.out.println("No active users.");

            return;
        }

        System.out.println("===== Active Users =====");

        for(User user : activeSessions.values()){

            System.out.println(user.getUsername());

        }

    }

}