/*
Projet Algorithmique 2 - FIAT LUX

Auteurs : Maucq Thibault (000 47 49 22) et Van Sint Jan Kolya (000 57 37 39)

Description : Fichier OptimisationAlg du projet. Contient les méthodes de l'algorithme d'optimisation pour obtenir le nombre 
              maximal d'ampoules allumées simultanéments dans une certaine grille donnée.

Date : 6 mai 2024.
*/

import java.util.ArrayList;
import java.util.Arrays;

public class OptimisationAlg {

    private static int max = 0; // Correspond au nombre max d'ampoules qui peuvent être allumées simultanément.

    /** 
     * Initialise les variables utiles pour l'algorithme pour appelle l'exécute. Imprime dans le terminale le résultat une 
     * fois l'algorithme terminé.
     * 
     * @param lamps array contenant des objets Lamp.
     * @return void.
     */
    static void exhaustion(ArrayList<Lamp> lamps){

        int index = 0;
        int[] horizontalSwitches = new int[Utils.findSwitchesNbr(lamps)];
        Arrays.fill(horizontalSwitches, 0);

        _exhaustion(lamps, horizontalSwitches, index);

        System.out.println("There can be at most " + max + " lamps lit at the same time.");

        return;
    }

    /** 
     * Cette méthode va faire des appels récusifs (backtracking) pour trouver le nombre maximal d'ampoules qui peuvent être
     * allumées simultanément. À chaque appel récursif, la méthode checkLamp est appelée pour trouver le nombre maximal d'ampoules 
     * qui sont allumées simultanément pour une composition spécifique des interrupteurs. Si le nombre obtenu est plus grand que la variable max,
     * le max devient ce nombre. 
     * 
     * @param lamps array contenant des objets Lamp.
     * @param horizontalSwitches liste contenant l'état (0 ou 1) de tous les interrupteurs de la grille. Première moitié les interrupteurs des lignes, deuxième moitié des colonnes.
     * @param index index permettant d'accéder à un interrupteur dans horizontalSwitches.
     * @return void.
     */
    static void _exhaustion(ArrayList<Lamp> lamps, int[] horizontalSwitches, int index){
        if (index == horizontalSwitches.length){
            int loopMax = checkLamp(lamps, horizontalSwitches);
            if (loopMax > max){
                max = loopMax;
                System.out.println("New solution found : " + max);
            }
            return;
        }
        horizontalSwitches[index] = 1;
        _exhaustion(lamps, horizontalSwitches, index + 1);

        horizontalSwitches[index] = 0;
        _exhaustion(lamps, horizontalSwitches, index + 1);
        
        return;
    }

    /** 
     * Cette méthode permet de vérifier si pour une certaine combinaison des interrupteurs, l'ampoule sera allumée ou pas.
     * On boucle donc sur toutes les ampoules de la grille et si l'ampoule ne s'allume pas, on décrémente le nombre total d'ampoules
     * jusqu'à obtenir le nombre total d'ampoules allumées.
     * 
     * @param lamps array contenant des objets Lamp.
     * @param horizontalSwitches liste contenant l'état (0 ou 1) de tous les interrupteurs de la grille. Première moitié les interrupteurs des lignes, deuxième moitié des colonnes.
     * @return entier correspondant au nombre d'ampoules allumées pour la configuration des interrupteurs.
     */
    private static int checkLamp(ArrayList<Lamp> lamps, int[] horizontalSwitches){
        boolean  lampState = false; // État de l'ampoule. (false == éteinte, true == allumée)
        int length = horizontalSwitches.length;
        int lampNbr = lamps.size();

        for(Lamp l : lamps){
            int[] lampBool = l.getSwitches();
            int[] lampCoord = l.getCoordinates();
            int x = lampCoord[0];
            int y = lampCoord[1];
            if (lampBool[0] == 1 && horizontalSwitches[x - 1] == 1 && horizontalSwitches[length/2 + y - 1] == 1){ // O - O
                lampState = true;
            }
            else if (lampBool[1] == 1 && horizontalSwitches[x - 1] == 1 && horizontalSwitches[length/2 + y - 1] == 0) { // O - F
                lampState = true;
            }
            else if (lampBool[2] == 1 && horizontalSwitches[x - 1] == 0 && horizontalSwitches[length/2 + y - 1] == 1) { // F - O
                lampState = true;
            }
            else if (lampBool[3] == 1 && horizontalSwitches[x - 1] == 0 && horizontalSwitches[length/2 + y - 1] == 0) { // F - F
                lampState = true;
            }

            if (lampState == false) {   // Si l'ampoule ne s'allume pas.
                lampNbr -= 1;
                if (lampNbr <= max) {   // Optimisation permettant de s'arrêter si le résultat sera de toute façon plus petit que le max.
                    return lampNbr;
                }
            }
            lampState = false;
        }
        return lampNbr;
    }
}


