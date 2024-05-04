package ex2;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 10000; // Doit correspondre au port du serveur

        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Voiture voiture = new Voiture("TypeExemple", "ModelExemple");
            oos.writeObject(voiture);
            System.out.println("Voiture envoyée au serveur.");

            Voiture voitureMiseAJour = (Voiture) ois.readObject();
            System.out.println("Voiture mise à jour reçue du serveur. Carburant actuel : " + voitureMiseAJour.getCarburant());

        } catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

