/*
Projet Algorithmique 2 - FIAT LUX

Auteurs : Maucq Thibault (000 47 49 22) et Van Sint Jan Kolya (000 57 37 39)

Description : Fichier DecisionAlg du projet. Contient les méthodes de l'algorithme de décision pour déterminer si  
              toutes les ampoules peuvent être allumées simultanéments dans une certaine grille donnée.

Date : 6 mai 2024.
*/

import java.util.ArrayList;
import java.util.Arrays;

public class DecisionAlg {

    public static boolean finished = false; // Bool permettant de savoir si la recherche de la solution est finie ou non.
    
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

        if (finished){
            System.out.println("There is a solution !");
        }
        else {
            System.out.println("No solution found.");
        }

        return;
    }

    /** 
     * Cette méthode va faire des appels récusifs (backtracking) pour trouver si toutes ampoules peuvent être allumées simultanément.
     * Une fois que l'index est égal au nombre d'interrupteurs, la méthode checkLamp est appelée pour savoir si toutes les lumières sont allumées
     * pour la combinaison d'interrupteurs obtenu. Si c'est le cas la variable finished est mise à true et on termine tous les appels récursifs.
     * 
     * @param lamps array contenant des objets Lamp.
     * @param horizontalSwitches liste contenant l'état (0 ou 1) de tous les interrupteurs de la grille. Première moitié les interrupteurs des lignes, deuxième moitié des colonnes.
     * @param index index permettant d'accéder à un interrupteur dans horizontalSwitches.
     * @return void.
     */
    static void _exhaustion(ArrayList<Lamp> lamps, int[] horizontalSwitches, int index){
        if (finished == true) {
            return;
        }
        if (index == horizontalSwitches.length){
            if (checkLamp(lamps, horizontalSwitches)){ 
                finished = true;
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
     * On boucle donc sur toutes les ampoules de la grille et si une des ampoules ne s'allume pas, on renvoie false directement.
     * 
     * @param lamps array contenant des objets Lamp.
     * @param horizontalSwitches liste contenant l'état (0 ou 1) de tous les interrupteurs de la grille. Première moitié les interrupteurs des lignes, deuxième moitié des colonnes.
     * @return boolean pour savoir si toutes les lampes peuvent être allumées simultanément ou non.
     */
    private static boolean checkLamp(ArrayList<Lamp> lamps, int[] horizontalSwitches){
        boolean  lampState = false; // État de l'ampoule. (false == éteinte, true == allumée)
        int length = horizontalSwitches.length;

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

            if (lampState == false) {
                return false;
            }
            lampState = false;
        }
        return true;
    }
}


