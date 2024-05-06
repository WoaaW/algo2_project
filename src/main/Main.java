/*
Projet Algorithmique 2 - FIAT LUX

Auteurs : Maucq Thibault (000 47 49 22) et Van Sint Jan Kolya (000 57 37 39)

Description : Fichier Main du projet. Créé un array d'objets Lamp en récupérant l'argument d'index 1 qui contient
              les informations du fichier .txt. Appelle ensuite la méthode d'un des deux algorithmes (décision ou optimisation)
              selon l'argumnet d'index 0.

Date : 6 mai 2024.
*/

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Lamp> LampArray = Utils.readFile(args[1]);
        if (args[0].equals("-d")){
            DecisionAlg.exhaustion(LampArray);
        } 
        else if (args[0].equals("-o")){
            OptimisationAlg.exhaustion(LampArray);
        }
    }
}
