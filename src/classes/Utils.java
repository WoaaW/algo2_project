/*
Projet Algorithmique 2 - FIAT LUX

Auteurs : Maucq Thibault (000 47 49 22) et Van Sint Jan Kolya (000 57 37 39)

Description : Fichier Utils du projet. Ce fichier contient deux méthodes utiles pour le projet. La première
              renvoie une array d'objets Lamp qui a été créée en en lisant les informations dans le fichier .txt 
              donné en paramètre. La deuxième renvoie le nombre total d'interrupteurs présents dans la grille 
              grâce aux informations stockées dans l'array de Lamp.

Date : 6 mai 2024.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    /**
     * Ouvre le fichier .txt en paramètre et créé tous les objets Lamp qui repésente les ampoules et 
     * les stockes dans un array.
     * 
     * @param path string contenant le nom du fichier .txt à ouvrir.
     * @return un array contenant tous les objets Lamp.
     **/
    static ArrayList<Lamp> readFile(String path){
        ArrayList<Lamp> lampArrayList = new ArrayList<Lamp>();

        try {
            Integer i = 0;

            File myFile = new File(path);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextInt()){
                int[] LampList = new int[6]; 
                for (int j = 0; j < 6; j++){
                    if (!myReader.hasNextInt()){
                        break;
                    }
                    int data = myReader.nextInt();
                    
                    LampList[j] = data;
                }
                lampArrayList.add(new Lamp(LampList));
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error has occured.");
            e.printStackTrace();
        }

        return lampArrayList;
    }

    /**
     * Compte le nombre total d'interrupteurs sur la grille. En gros le nombre de lignes + le nombre de colonnes.
     * 
     * @param lamps array contenant des objets Lamp.
     * @return un entier correspondant à la somme du nombre de lignes et de colonnes.
     **/
    static int findSwitchesNbr(ArrayList<Lamp> lamps){
        int[] res = new int[2];
        int[] coord = new int[2];
        res[0] = 0; res[1] = 0;
        
        for (Lamp l : lamps){
            coord = l.getCoordinates();
            res[0] = Math.max(res[0], coord[0]);
            res[1] = Math.max(res[1], coord[1]);
        }

        return res[0] + res[1];
    }
    
}
