package models;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a logged-in session.
 *
 * Every login creates a unique session.
 *
 * @author Nidhi Patil
 * @version 1.0
 */
public class Session {

    /**
     * Session validity in minutes.
     */
    private static final int SESSION_DURATION = 30;

    private final UUID sessionId;

    private final UUID userId;

    private final User user;

    private final LocalDateTime loginTime;

    private final LocalDateTime expiryTime;

    private boolean active;

    /**
     * Creates a new user session.
     */
    public Session(UUID userId) {

        this.sessionId = UUID.randomUUID();
        this.userId = userId;
        this.user = null;
        this.loginTime = LocalDateTime.now();
        this.expiryTime = loginTime.plusMinutes(SESSION_DURATION);
        this.active = true;
    }

    /**
     * Creates a new user session with user object.
     */
    public Session(User user) {

        this.sessionId = UUID.randomUUID();
        this.userId = user.getId();
        this.user = user;
        this.loginTime = LocalDateTime.now();
        this.expiryTime = loginTime.plusMinutes(SESSION_DURATION);
        this.active = true;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public boolean isActive() {
        return active;
    }

    /**
     * Invalidates the current session.
     */
    public void invalidate() {
        active = false;
    }

    /**
     * Checks if the session has expired.
     */
    public boolean isExpired() {

        return !active ||
                LocalDateTime.now().isAfter(expiryTime);
    }

    @Override
    public String toString() {

        return "Session{" +
                "sessionId=" + sessionId +
                ", userId=" + userId +
                ", loginTime=" + loginTime +
                ", expiryTime=" + expiryTime +
                ", active=" + active +
                '}';
    }

}