import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        GameBoard gb = new GameBoard();
        gb.readInput("/Users/bilalhodzic/Documents/JuniorYearFirstSem/COMS311/HW3/HW3ProjDir/src/testingfile.txt");
        ArrayList<Pair> plan =  gb.getPlan();
        for(Pair move : plan){
            System.out.println(move.id + " " + move.direction);
        }
        System.out.println(gb.getNumOfPaths());
    }
}