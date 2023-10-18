import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {
    public int[][] boardArray = new int[6][6];
    public Vehicle[] vehicles;
    public GameBoard(){
        //Initialize board array to all -1 signfyign empty spots
        for(int i = 0; i < boardArray.length; i++){
            for(int j = 0; j < boardArray[0].length; j++){
                boardArray[i][j] = -1;
            }
        }
    }
    public void readInput(String FileName) throws IOException {
        try{
            File file = new File(FileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }catch (Exception e){
            throw new IOException();
        }
    }

    public ArrayList<Pair> getPlan(){
        //TODO
        return null;
    }
    public int getNumOfPaths(){
        //TODO
        return -1;
    }
}
