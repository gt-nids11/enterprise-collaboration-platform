package protocol;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Packet implements Serializable {

    private static final long serialVersionUID = 1L;

    private PacketType type;
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime timestamp;

    public Packet(PacketType type,
                  String sender,
                  String receiver,
                  String content) {

        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public PacketType getType() {
        return type;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "type=" + type +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}