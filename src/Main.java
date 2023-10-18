import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        GameBoard gb = new GameBoard();
        gb.readInput("/Users/bilalhodzic/Documents/JuniorYearFirstSem/COMS311/HW3/HW3ProjDir/src/testingfile.txt");
    }
}