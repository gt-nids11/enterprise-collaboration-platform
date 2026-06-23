package protocol;

public enum PacketType {

    // Authentication
    LOGIN,
    REGISTER,
    LOGOUT,

    // Messaging
    MESSAGE,

    // File Sharing
    FILE_UPLOAD,
    FILE_DOWNLOAD,

    // User Management
    USER_LIST,

    // Server Responses
    SUCCESS,
    ERROR
}