import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameBoard {
    public int[][] firstBoard = new int[6][6];
    public Vehicle[] vehicles;

    public ArrayList<PotentialSolution> winningSolutions = new ArrayList<>();
    public HashMap<Integer, PotentialSolution> previousStates = new HashMap<>();

    public GameBoard() {
        //Initialize board array to all -1 signfyign empty spots
        for (int i = 0; i < firstBoard.length; i++) {
            for (int j = 0; j < firstBoard[0].length; j++) {
                firstBoard[i][j] = -1;
            }
        }
    }

    public void readInput(String FileName) throws IOException {
        try {
            File file = new File(FileName);
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            vehicles = new Vehicle[Integer.parseInt(line)];
            int currVehicleIndex = 0;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] splitLine = line.split("\\s+");
                Vehicle newVehicle = new Vehicle();
                int[] coords = new int[3];
                int coordsIndex = 0;
                for (String str : splitLine) {
                    coords[coordsIndex] = Integer.parseInt(str) - 1;
                    coordsIndex++;
                }
                newVehicle.vehicleId = currVehicleIndex;
                newVehicle.xCoord = coords[0] % 6;
                newVehicle.yCoord = coords[0] / 6;
                newVehicle.leftRight = coords[0] + 1 == coords[1];
                if (coords[2] != 0) {
                    newVehicle.length = 3;
                } else {
                    newVehicle.length = 2;
                }
                for (int num : coords) {
                    if (num != 0) {
                        firstBoard[num / 6][num % 6] = currVehicleIndex;
                    }
                }
                vehicles[currVehicleIndex] = newVehicle;
                currVehicleIndex++;
            }
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public ArrayList<Pair> checkAvailableMoves(PotentialSolution state) {
        ArrayList<Pair> potentialMoves = new ArrayList<>();
        for (Vehicle vehicle : state.stateVehicles) {
            if (vehicle.leftRight) {
                if (vehicle.xCoord - 1 >= 0) {
                    if (state.currentState[vehicle.yCoord][vehicle.xCoord - 1] == -1) {
                        potentialMoves.add(new Pair(vehicle.vehicleId, 'w'));
                    }
                    if (vehicle.xCoord + vehicle.length <= 5) {
                        if (state.currentState[vehicle.yCoord][vehicle.xCoord + vehicle.length] == -1) {
                            potentialMoves.add(new Pair(vehicle.vehicleId, 'e'));
                        }
                    }
                } else if (vehicle.xCoord + vehicle.length <= 5) {
                    if (state.currentState[vehicle.yCoord][vehicle.xCoord + vehicle.length] == -1) {
                        potentialMoves.add(new Pair(vehicle.vehicleId, 'e'));
                    }
                }
            } else {
                if (vehicle.yCoord - 1 >= 0) {
                    if (state.currentState[vehicle.yCoord - 1][vehicle.xCoord] == -1) {
                        potentialMoves.add(new Pair(vehicle.vehicleId, 'n'));
                    }
                    if (vehicle.yCoord + vehicle.length <= 5) {
                        if (state.currentState[vehicle.yCoord + vehicle.length][vehicle.xCoord] == -1) {
                            potentialMoves.add(new Pair(vehicle.vehicleId, 's'));
                        }
                    }
                } else if (vehicle.yCoord + vehicle.length <= 5) {
                    if (state.currentState[vehicle.yCoord + vehicle.length][vehicle.xCoord] == -1) {
                        potentialMoves.add(new Pair(vehicle.vehicleId, 's'));
                    }
                }
            }
        }
        return potentialMoves;
    }

    public ArrayList<Pair> getPlan() {
        Queue<PotentialSolution> BFSQ = new LinkedList<>();
        PotentialSolution startingState = new PotentialSolution(firstBoard, vehicles, null);
        int hash = Arrays.deepHashCode(startingState.currentState);
        previousStates.put(hash, startingState);
        BFSQ.add(startingState);
        while(!BFSQ.isEmpty()){
            PotentialSolution currentStateSolution = BFSQ.poll();
            if(checkWin(currentStateSolution)){
                winningSolutions.add(currentStateSolution);
            }else{
                ArrayList<Pair> potentialMoves = checkAvailableMoves(currentStateSolution);
                for(Pair move : potentialMoves){
                    PotentialSolution testState = move(currentStateSolution, move);
                    int testHash = Arrays.deepHashCode(testState.currentState);
                    if(!previousStates.containsKey(testHash)){
                        previousStates.put(testHash,testState);
                        BFSQ.add(testState);
                    }else{
                        previousStates.get(testHash).numOfPaths += testState.numOfPaths;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    public int getNumOfPaths() {
        return winningSolutions.size();
    }

    public PotentialSolution move(PotentialSolution startingState, Pair move) {
        PotentialSolution nextState = new PotentialSolution(startingState.currentState, startingState.stateVehicles, startingState);
        Vehicle toMove = nextState.stateVehicles[move.id];
        if (move.direction == 'n') {
            toMove.yCoord--;
            nextState.currentState[toMove.yCoord][toMove.xCoord] = toMove.vehicleId;
            nextState.currentState[toMove.yCoord + toMove.length][toMove.xCoord] = -1;
        } else if (move.direction == 's') {
            nextState.currentState[toMove.yCoord][toMove.xCoord] = -1;
            nextState.currentState[toMove.yCoord + toMove.length][toMove.xCoord] = toMove.vehicleId;
            toMove.yCoord++;
        } else if (move.direction == 'e') {
            nextState.currentState[toMove.yCoord][toMove.xCoord] = -1;
            nextState.currentState[toMove.yCoord][toMove.xCoord + toMove.length] = toMove.vehicleId;
            toMove.xCoord++;
        } else {
            toMove.xCoord--;
            nextState.currentState[toMove.yCoord][toMove.xCoord] = toMove.vehicleId;
            nextState.currentState[toMove.yCoord][toMove.xCoord + toMove.length] = -1;
        }
        nextState.moveMade = move;
        return nextState;
    }

    public boolean checkWin(PotentialSolution toCheck){
        return toCheck.currentState[2][5] == 0;
    }
}
