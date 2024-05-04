package Tp2.Exercice2;


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

            // Création de l'objet Voiture
            Voiture voiture = new Voiture("SUV", "Toyota");

            // Conversion de l'objet Voiture en tableau de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(voiture);
            byte[] data = baos.toByteArray();

            // Envoi de la voiture au serveur
            DatagramPacket packet = new DatagramPacket(data, data.length, adr, port);
            socket.send(packet);

            // Réception de la voiture avec le carburant fixé depuis le serveur
            byte[] buffer = new byte[1024];
            DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(replyPacket);

            // Conversion du tableau de bytes en objet Voiture
            ByteArrayInputStream bais = new ByteArrayInputStream(replyPacket.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Voiture voitureWithCarburant = (Voiture) ois.readObject();
            System.out.println("Voiture avec carburant reçue du serveur : " + voitureWithCarburant.getCarburant());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
