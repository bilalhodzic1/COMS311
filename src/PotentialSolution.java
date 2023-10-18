import java.util.ArrayList;

public class PotentialSolution {
    int[][] currentState;
    ArrayList<Pair> currentMoves = new ArrayList<>();

    public PotentialSolution(ArrayList<Pair> newMoves, int[][] newState){
        currentState = newState;
        currentMoves.addAll(newMoves);
    }
}
