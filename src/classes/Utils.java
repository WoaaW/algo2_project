import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

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
