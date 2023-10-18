import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {
    public int[][] boardArray = new int[6][6];
    public Vehicle[] vehicles;

    public GameBoard() {
        //Initialize board array to all -1 signfyign empty spots
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[0].length; j++) {
                boardArray[i][j] = -1;
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
                        boardArray[num / 6][num % 6] = currVehicleIndex;
                    }
                }
                vehicles[currVehicleIndex] = newVehicle;
                currVehicleIndex++;
            }
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public ArrayList<Pair> getPlan() {
        //TODO
        return null;
    }

    public int getNumOfPaths() {
        //TODO
        return -1;
    }
}
