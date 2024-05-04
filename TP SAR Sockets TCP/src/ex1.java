import java.io.*;
	import java.net.*;
	import java.util.Scanner;
public class ex1 {

	
//SocketServeur
	

	public class SocketServeur {
	    public static void main(String argv[]) {
	        int port = 0;
	        Scanner keyb = new Scanner(System.in); // Création d'un scanner pour lire depuis le clavier
	        
	        // Demande du port d'écoute à l'utilisateur
	        System.out.print("Port d'écoute : ");
	        try {
	            port = keyb.nextInt(); // Lecture du port d'écoute
	        } catch (NumberFormatException e) {
	            System.err.println("Le paramètre n'est pas un entier.");
	            System.err.println("Usage : java ServeurUDP port-serveur");
	            System.exit(-1); // Sortie du programme en cas d'erreur
	        }
	        
	        try {
	            ServerSocket serverSocket = new ServerSocket(port); // Création du socket serveur sur le port spécifié
	            Socket socket = serverSocket.accept(); // Attente d'une connexion client
	            
	            // Création des flux d'entrée/sortie avec le client
	            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
	            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	            
	            // Lecture du message envoyé par le client
	            String chaine = (String) input.readObject();
	            System.out.println("Recu : " + chaine); // Affichage du message
	            
	            // Information sur la source du message
	            System.out.println("Ça vient de : " + socket.getInetAddress() + ":" + socket.getPort());
	            
	            // Envoi d'une réponse au client
	            output.writeObject(new String("bien recu"));
	        } catch (Exception e) {
	            System.err.println("Erreur : " + e);
	        }
	    }
	}
	
	//SocketClient
	class SocketClient {
	    public static void main(String argv[]) {
	        int port = 0;
	        String host = "";
	        Scanner keyb = new Scanner(System.in); // Scanner pour lire depuis le clavier
	        
	        // Demande de l'adresse du serveur et du port
	        System.out.print("Nom du serveur : ");
	        host = keyb.next(); // Lecture de l'adresse du serveur
	        System.out.print("Port du serveur : ");
	        try {
	            port = keyb.nextInt(); // Lecture du port
	        } catch (NumberFormatException e) {
	            System.err.println("Le second paramètre n'est pas un entier.");
	            System.exit(-1); // Sortie en cas d'erreur
	        }
	        
	        try {
	            InetAddress adr = InetAddress.getByName(host); // Résolution de l'adresse du serveur
	            Socket socket = new Socket(adr, port); // Connexion au serveur
	            
	            // Création des flux d'entrée/sortie
	            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
	            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	            
	            // Envoi d'un message au serveur
	            output.writeObject(new String("ma première socket"));
	            
	            // Réception et affichage de la réponse du serveur
	            String chaine = (String) input.readObject();
	            System.out.println("Reçu du serveur : " + chaine);
	        } catch (Exception e) {
	            System.err.println("Erreur : " + e);
	        }
	    }
	}
}
