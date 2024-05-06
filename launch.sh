# Projet Algorithmique 2 - FIAT LUX
#
# Auteurs : Maucq Thibault (000 47 49 22) et Van Sint Jan Kolya (000 57 37 39)
#
# Description : Script launch du projet d'algorithmique 2. Ce script compile tous les fichiers utiles à l'exécution
#               des algorithmes. Une fois la compilation terminée, le script lance l'exécutable avec les arguments
#               donnés par l'utilisateur. Ici $1 peut être soit -d (décision) soit -o (optimisation) pour sélectionner
#               l'algorithme à exécuter et $2 correspond au nom du fichier .txt (test.txt par exemple) où les
#               informations de la grille sont stockées.
#
# Date : 6 mai 2024.

cd src/main
javac Main.java -classpath ../classes/ -d ../../build/

cd ../../build
time java Main $1 ../res/$2
