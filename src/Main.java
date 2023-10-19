import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        GameBoard gb = new GameBoard();
        gb.readInput("/Users/bilalhodzic/Documents/JuniorYearFirstSem/COMS311/HW3/HW3ProjDir/src/testingfile.txt");
        ArrayList<Pair> plan =  gb.getPlan();
        for(Pair move : plan){
            System.out.println(move.id + " " + move.direction);
        }
        System.out.println(gb.getNumOfPaths());
    }
}