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
