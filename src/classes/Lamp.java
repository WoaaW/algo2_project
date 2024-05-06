/*
Projet Algorithmique 2 - FIAT LUX

Auteurs : Maucq Thibault (000 47 49 22) et Van Sint Jan Kolya (000 57 37 39)

Description : Fichier Lamp du projet. Ce fichier contient la class Lamp, qui représente les ampoule. Un objet Lamp sera donc crée
              pour chaque ampoule présente sur le grille.

Date : 6 mai 2024.
*/

public class Lamp {
    int[] _values;  // Correspond aux entiers reprenant toutes les informations de l'ampoule sous le format "l c x y z t".
    int[] _coord = new int[2];  // Correspond à la ligne et colonne de l'ampoule.
    int[] _switches = new int[4];   // Correspond aux bits indiquant si l'ampoule s'allume pour chacune des 4 combinaisons possibles des interrupteurs.

    /**
     * Constructeur de Lamp. 
     * 
     * @param values Correspond aux entiers reprenant toutes les informations de l'ampoule sous le format "l c x y z t".
     **/
    public Lamp(int[] values){
        _values = values;
        for (int i = 0; i <= 1; i++){
            _coord[i] = values[i];
        }
        for (int i = 0; i < 4; i++){
            _switches[i] = values[i + 2];
        }
    }

    public int[] getValues(){
        return _values;
    }

    public int[] getCoordinates(){
        return _coord;
    }

    public int[] getSwitches(){
        return _switches;
    }

    public boolean getSwitchBool(int value, int state) {
        if (_switches[value] == state) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "[" + _values[0] + ", " + _values[1] + ", " + _values[2] + ", " + _values[3] + ", " + _values[4] + ", " + _values[5] + "]";
    }
}
