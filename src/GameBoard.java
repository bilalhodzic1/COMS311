import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameBoard {
    public int[][] firstBoard = new int[6][6];
    public Vehicle[] vehicles;

    public HashMap<Integer, int[][]> previousStates = new HashMap<>();

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
                if(coords[2] != 0){
                    newVehicle.length = 3;
                }else{
                    newVehicle.length = 2;
                }
                for(int num : coords){
                    if(num != 0) {
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

    public ArrayList<Pair> checkAvailableMoves(PotentialSolution state){
        ArrayList<Pair> potentialMoves = new ArrayList<>();
        for(Vehicle vehicle : state.stateVehicles){
            if(vehicle.leftRight){
                if(vehicle.xCoord -1 > 0){
                    if(state.currentState[vehicle.yCoord][vehicle.xCoord -1] == -1){
                        potentialMoves.add(new Pair(vehicle.vehicleId, 'w'));
                    }
                    if(vehicle.xCoord + vehicle.length <5){
                        if(state.currentState[vehicle.yCoord][vehicle.xCoord + vehicle.length] == -1) {
                            potentialMoves.add(new Pair(vehicle.vehicleId, 'e'));
                        }
                    }
                }else if(vehicle.xCoord + vehicle.length <5){
                    if(state.currentState[vehicle.yCoord][vehicle.xCoord + vehicle.length] == -1) {
                        potentialMoves.add(new Pair(vehicle.vehicleId, 'e'));
                    }
                }
            }else{
                if(vehicle.yCoord -1 >0){
                    if(state.currentState[vehicle.yCoord -1][vehicle.xCoord] == -1){
                        potentialMoves.add(new Pair(vehicle.vehicleId, 'n'));
                    }
                    if(vehicle.yCoord + vehicle.length < 5){
                        if(state.currentState[vehicle.yCoord + vehicle.length][vehicle.xCoord] == -1){
                            potentialMoves.add(new Pair(vehicle.vehicleId, 's'));
                        }
                    }
                }else if(vehicle.yCoord + vehicle.length < 5){
                    if(state.currentState[vehicle.yCoord + vehicle.length][vehicle.xCoord] == -1){
                        potentialMoves.add(new Pair(vehicle.vehicleId, 's'));
                    }
                }
            }
        }
        return potentialMoves;
    }

    public ArrayList<Pair> getPlan() {
        PotentialSolution startingState = new PotentialSolution(new ArrayList<>(),firstBoard, vehicles);

        ArrayList<Pair> possibleMoves = checkAvailableMoves(startingState);

        return null;
    }

    public int getNumOfPaths() {
        //TODO
        return -1;
    }
}
