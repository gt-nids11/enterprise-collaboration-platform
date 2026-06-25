package network;

import protocol.Packet;
import protocol.PacketType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket clientSocket;
    private String username;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    
@Override
public void run() {

    try {

        System.out.println(
                "Handling client: "
                        + clientSocket.getInetAddress()
        );

        ObjectOutputStream outputStream =
                new ObjectOutputStream(
                        clientSocket.getOutputStream()
                );

        outputStream.flush();

        ObjectInputStream inputStream =
                new ObjectInputStream(
                        clientSocket.getInputStream()
                );

        Packet receivedPacket =
        (Packet) inputStream.readObject();

System.out.println(
        "DEBUG: Packet Type = "
                + receivedPacket.getType()
);

if (receivedPacket.getType() == PacketType.LOGIN) {

    System.out.println(
            "DEBUG: LOGIN block entered"
    );

    username = receivedPacket.getSender();

    SessionManager.addUser(
            username,
            this
    );

    SessionManager.displayActiveUsers();
}

        System.out.println(
                "\nPacket received from "
                        + receivedPacket.getSender()
        );

        System.out.println(receivedPacket);

        Packet responsePacket = new Packet(
                PacketType.SUCCESS,
                "SERVER",
                receivedPacket.getSender(),
                "Packet received successfully."
        );

        outputStream.writeObject(responsePacket);
        outputStream.flush();

        System.out.println(
                "Response sent to "
                        + receivedPacket.getSender()
        );

        // Remove user when connection closes
        if (username != null) {

            SessionManager.removeUser(username);

            SessionManager.displayActiveUsers();
        }

        inputStream.close();
        outputStream.close();
        clientSocket.close();

    } catch (Exception e) {

        System.out.println(
                "Client Handler Error: "
                        + e.getMessage()
        );
    }
}
}