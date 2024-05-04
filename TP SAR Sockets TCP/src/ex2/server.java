package ex2;

import java.io.*;
import java.net.*;

public class server{
    public static void main(String[] args) {
        int port = 10000; // Port d'écoute
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré, en attente de connexion...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connecté.");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            Voiture voiture = (Voiture) ois.readObject();
            System.out.println("Voiture reçue. Mise à jour du carburant.");
            voiture.setCarburant(100); // Exemple de modification

            oos.writeObject(voiture);
            System.out.println("Voiture mise à jour envoyée au client.");

        } catch (Exception e) {
            System.err.println("Erreur serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
