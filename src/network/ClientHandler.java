package network;

import protocol.Packet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket clientSocket;

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
                "\nPacket received from "
                        + receivedPacket.getSender()
        );

        System.out.println(receivedPacket);

        // Send response back to client
        Packet responsePacket = new Packet(
                protocol.PacketType.SUCCESS,
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