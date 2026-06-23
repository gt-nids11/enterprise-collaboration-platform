package network;

import protocol.Packet;
import protocol.PacketType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 5000;

    private ServerSocket serverSocket;

    public void start() {

        try {

            // Create Server Socket
            serverSocket = new ServerSocket(PORT);

            System.out.println("==================================");
            System.out.println(" Enterprise Collaboration Server");
            System.out.println(" Server started on port " + PORT);
            System.out.println(" Waiting for client...");
            System.out.println("==================================");

            // Wait for a client
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected: "
                    + clientSocket.getInetAddress());

            // Create output stream FIRST
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(clientSocket.getOutputStream());

            outputStream.flush();

            // Then create input stream
            ObjectInputStream inputStream =
                    new ObjectInputStream(clientSocket.getInputStream());

            // Receive Packet
            Packet receivedPacket = (Packet) inputStream.readObject();

            System.out.println("\nPacket Received:");
            System.out.println(receivedPacket);

            // Create Response Packet
            Packet responsePacket = new Packet(
                    PacketType.SUCCESS,
                    "SERVER",
                    receivedPacket.getSender(),
                    "Packet received successfully."
            );

            // Send Response
            outputStream.writeObject(responsePacket);
            outputStream.flush();

            System.out.println("\nResponse sent to client.");

            // Close resources
            inputStream.close();
            outputStream.close();
            clientSocket.close();
            serverSocket.close();

            System.out.println("\nServer stopped.");

        }

        catch (IOException | ClassNotFoundException e) {

            System.out.println("Server Error: " + e.getMessage());

        }

    }

    public static void main(String[] args) {

        Server server = new Server();

        server.start();

    }

}