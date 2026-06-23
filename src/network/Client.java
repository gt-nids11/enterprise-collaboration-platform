package network;

import protocol.Packet;
import protocol.PacketType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public void connect() {

        try {

            // Connect to the server
            Socket socket = new Socket(HOST, PORT);

            System.out.println("==================================");
            System.out.println(" Connected to Server");
            System.out.println("==================================");

            // Create output stream first
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();

            // Then create input stream
            ObjectInputStream inputStream =
                    new ObjectInputStream(socket.getInputStream());

            // Create a LOGIN packet
            Packet loginPacket = new Packet(
                    PacketType.LOGIN,
                    "Nidhi",
                    "SERVER",
                    "mypassword"
            );

            // Send packet
            outputStream.writeObject(loginPacket);
            outputStream.flush();

            System.out.println("\nPacket sent to server.");
            System.out.println(loginPacket);

            // Receive response
            Packet responsePacket = (Packet) inputStream.readObject();

            System.out.println("\nResponse received:");
            System.out.println(responsePacket);

            // Close resources
            inputStream.close();
            outputStream.close();
            socket.close();

            System.out.println("\nClient disconnected.");

        } catch (Exception e) {

            System.out.println("Client Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Client client = new Client();
        client.connect();

    }
}