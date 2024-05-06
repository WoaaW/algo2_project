import java.util.ArrayList;
import java.util.Arrays;

public class OptimisationAlg {

    private static int max = 0;
    
    static void exhaustion(ArrayList<Lamp> lamps, int switchesNo){

        int index = 0;
        int[] horizontalSwitches = new int[switchesNo];
        Arrays.fill(horizontalSwitches, 0);

        _exhaustion(lamps, horizontalSwitches, index);

        System.out.println("There can be at most " + max + " lamps lit at the same time.");

        return;
    }

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

    private static int checkLamp(ArrayList<Lamp> lamps, int[] horizontalSwitches){
        boolean  lampState = false;
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

            if (lampState == false) {
                lampNbr -= 1;
            }
            lampState = false;
        }
        return lampNbr;
    }
}


