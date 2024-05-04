package ex3;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Serveur {
    private static AtomicInteger clientIdGenerator = new AtomicInteger();

    public static void main(String[] args) {
        int port = 12345; // Port sur lequel le serveur écoute

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré, en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté.");

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

                int age = ois.readInt();
                String name = (String) ois.readObject();

                System.out.println("Reçu de " + clientSocket.getInetAddress() + " : " + name + ", " + age + " ans");

                int clientId = clientIdGenerator.incrementAndGet();
                oos.writeInt(clientId);
                System.out.println("ID client envoyé : " + clientId);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

