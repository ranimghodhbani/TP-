package Tp2.Exercice2;



import java.io.*;
import java.net.*;

public class ServeurUDP {
    public static void main(String argv[]) {
        int port = 1256;
        try {
            DatagramSocket socket = new DatagramSocket(port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Récupération de l'objet Voiture envoyé par le client
                ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Voiture voiture = (Voiture) ois.readObject();
                System.out.println("Voiture reçue du client : " + voiture);

                // Fixation du carburant de la voiture
                voiture.setCarburant(50); // Exemple : fixe à 50 litres de carburant

                // Conversion de la voiture modifiée en tableau de bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(voiture);
                byte[] data = baos.toByteArray();

                // Envoi de la voiture modifiée au client
                DatagramPacket replyPacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                socket.send(replyPacket);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
