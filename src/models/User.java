package models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a registered user of the collaboration platform.
 *
 * Passwords are stored only as hashes.
 *
 * This class follows encapsulation principles and exposes only
 * the required setters.
 *
 * @author Nidhi Patil
 * @version 1.0
 */
public class User {

    private final UUID id;

    private final String username;

    private final String email;

    private String passwordHash;

    private final Role role;

    private UserStatus status;

    private final LocalDateTime createdAt;

    private LocalDateTime lastLogin;

    private boolean online;

    /**
     * Creates a new user.
     */
    public User(String username,
                String email,
                String passwordHash,
                Role role) {

        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.status = UserStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.online = false;
    }

    public UUID getId() {
        return id;
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

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public boolean isOnline() {
        return online;
    }

    /**
     * Updates the user's password hash.
     */
    public void updatePassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Updates account status.
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Marks the user online.
     */
    public void login() {
        this.online = true;
        this.lastLogin = LocalDateTime.now();
    }

    /**
     * Marks the user offline.
     */
    public void logout() {
        this.online = false;
    }

    @Override
    public String toString() {

        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", online=" + online +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof User))
            return false;

        User other = (User) obj;

        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}