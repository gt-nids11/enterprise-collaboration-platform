package database;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import models.User;

public class Database {

    private final Map<String, User> users;

    public Database() {

        users = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {

        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {

        return users.get(username);
    }

    public boolean userExists(String username) {

        return users.containsKey(username);
    }

    public boolean emailExists(String email) {

        return users.values().stream()
                .anyMatch(user -> user.getEmail() != null && user.getEmail().equals(email));
    }

    public void removeUser(String username) {

        users.remove(username);
    }

    public int totalUsers() {

        return users.size();
    }

    public void saveUser(User user) {

        addUser(user);
    }

}