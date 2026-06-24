package network;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 5000;

    private ServerSocket serverSocket;

    public void start() {

        try {

            serverSocket = new ServerSocket(PORT);

            System.out.println("==================================");
            System.out.println(" Enterprise Collaboration Server");
            System.out.println(" Server started on port " + PORT);
            System.out.println("==================================");

            while (true) {

                System.out.println("\nWaiting for client...");

                Socket clientSocket = serverSocket.accept();

                System.out.println(
                        "Client Connected: "
                                + clientSocket.getInetAddress()
                );

                ClientHandler clientHandler =
                        new ClientHandler(clientSocket);

                clientHandler.start();
            }

        } catch (Exception e) {

            System.out.println(
                    "Server Error: "
                            + e.getMessage()
            );
        }
    }

    public void stop() {

        try {

            if (serverSocket != null
                    && !serverSocket.isClosed()) {

                serverSocket.close();

                System.out.println(
                        "Server stopped successfully."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error stopping server: "
                            + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        Server server = new Server();

        server.start();
    }
}