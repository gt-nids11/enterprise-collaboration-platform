package network;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class SessionManager {

    private static final Map<String, ClientHandler> activeUsers =
            new ConcurrentHashMap<>();

    private SessionManager() {
    }

    public static void addUser(
            String username,
            ClientHandler clientHandler) {

        activeUsers.put(username, clientHandler);

        System.out.println(
                username + " logged in."
        );
    }

    public static void removeUser(
            String username) {

        activeUsers.remove(username);

        System.out.println(
                username + " logged out."
        );
    }

    public static ClientHandler getUser(
            String username) {

        return activeUsers.get(username);
    }

    public static boolean isUserOnline(
            String username) {

        return activeUsers.containsKey(username);
    }

    public static void displayActiveUsers() {

        System.out.println("\n===== Active Users =====");

        activeUsers.keySet()
                .forEach(System.out::println);

        System.out.println("========================\n");
    }
}