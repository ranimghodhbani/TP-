package Tp2.Exercice3;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String argv[]) {
        Scanner keyb = new Scanner(System.in);
        try {
            System.out.println("Adresse du serveur : ");
            String host = keyb.next();
            System.out.println("Port du serveur : ");
            int port = keyb.nextInt();

            InetAddress adr = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket();

            // Création de l'objet Voiture et réglage de la quantité de carburant
            Voiture voiture = new Voiture("SUV", "Toyota");
            voiture.setCarburant(50);

            // Conversion de l'objet Voiture en tableau de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(voiture);
            byte[] data = baos.toByteArray();

            // Envoi du paquet au serveur
            DatagramPacket packet = new DatagramPacket(data, data.length, adr, port);
            socket.send(packet);

            // Réception de la réponse du serveur (date et heure courantes)
            byte[] buffer = new byte[1024];
            DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(replyPacket);

            // Affichage de la réponse
            String reponse = new String(replyPacket.getData(), 0, replyPacket.getLength());
            System.out.println("Réponse du serveur : " + reponse);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
