package ex3;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345; // Doit correspondre au port d'écoute du serveur

        try (Socket socket = new Socket(host, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            int age = 25; // Exemple d'âge
            String name = "Jean Dupont"; // Exemple de nom

            oos.writeInt(age);
            oos.writeObject(name);
            oos.flush();

            int clientId = ois.readInt();
            System.out.println("ID client reçu du serveur : " + clientId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
