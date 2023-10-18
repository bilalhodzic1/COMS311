import java.util.ArrayList;

public class PotentialSolution {
    int[][] currentState;

    Vehicle[] stateVehicles;
    ArrayList<Pair> currentMoves = new ArrayList<>();

    public PotentialSolution(ArrayList<Pair> newMoves, int[][] newState, Vehicle[] newVehicles){
        stateVehicles = new Vehicle[newVehicles.length];
        for(int i = 0 ; i < newVehicles.length; i++){
            stateVehicles[i] = newVehicles[i].clone();
        }
        currentState = new int[6][6];
        for(int i = 0; i < newState.length; i++){
            currentState[i] = newState[i].clone();
        }
        currentMoves.addAll(newMoves);
    }
}
