import java.util.ArrayList;

public class PotentialSolution {
    int[][] currentState;

    Vehicle[] stateVehicles;

    Pair moveMade;
    int numOfPaths;
    PotentialSolution parent;

    public PotentialSolution(int[][] newState, Vehicle[] newVehicles, PotentialSolution parent){
        stateVehicles = new Vehicle[newVehicles.length];
        for(int i = 0 ; i < newVehicles.length; i++){
            stateVehicles[i] = newVehicles[i].clone();
        }
        currentState = new int[6][6];
        for(int i = 0; i < newState.length; i++){
            currentState[i] = newState[i].clone();
        }
        this.parent = parent;
        if(parent == null){
            numOfPaths = 1;
        }else{
            numOfPaths = parent.numOfPaths;
        }
    }
}
