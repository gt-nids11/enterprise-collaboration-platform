package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private static int idCounter = 1000;

    private final int userId;
    private String username;
    private String email;
    private String passwordHash;
    private final LocalDateTime createdAt;
    private Role role;
    private UserStatus status;

    /**
     * Constructor for creating a new user.
     * User ID and creation time are generated automatically.
     */
    public User(String username, String email, String passwordHash) {
        this.userId = ++idCounter;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = LocalDateTime.now();
        this.role = Role.MEMBER;
        this.status = UserStatus.OFFLINE;
    }

    // ===========================
    // Getters
    // ===========================

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    // ===========================
    // Setters
    // ===========================

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Used internally after password hashing.
     * Never store plain text passwords.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    // ===========================
    // Utility Methods
    // ===========================

    @Override
    public String toString() {
        return "User {" +
                "\n userId = " + userId +
                ",\n username = '" + username + '\'' +
                ",\n email = '" + email + '\'' +
                ",\n role = " + role +
                ",\n status = " + status +
                ",\n createdAt = " + createdAt +
                "\n}";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof User))
            return false;

        User other = (User) obj;

        return userId == other.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}