package Tp2.Exercice3;
 
import java.io.*;
public class Voiture implements Serializable {
private int carburant;
private String model;
private String type;
private static int capacite = 300;
Voiture(String _type, String _model) {
type = _type;
model = _model;
carburant = 0;
}
public void setCarburant(int c) {
int maxi = capacite - carburant;
if (c < maxi) {
carburant += c;
System.out.println(" Remplissage  effectué sans aucun problème.");
} else {
carburant = capacite;
System.out.println((c - maxi) + " litre(s) de carburant ont débordé.");
}
}
public int getCarburant() {
return carburant;
}
public int getCapacite() {
return capacite;
}
}
