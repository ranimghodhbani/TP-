package Tp2.Exercice3;



import java.io.*;
import java.net.*;
import java.util.Date;

public class ServeurUDP {
    public static void main(String argv[]) {
        int port = 1250;
        try {
            DatagramSocket socket = new DatagramSocket(port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Peu importe ce qui est reçu, on envoie la date et l'heure courantes au client
                String dateHeure = new Date().toString();
                byte[] reponse = dateHeure.getBytes();

                DatagramPacket replyPacket = new DatagramPacket(reponse, reponse.length, packet.getAddress(), packet.getPort());
                socket.send(replyPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
