import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        GameBoard gb = new GameBoard();
        gb.readInput("/Users/bilalhodzic/Documents/JuniorYearFirstSem/COMS311/HW3/HW3ProjDir/src/testingfile.txt");
        gb.getPlan();
        for (int i = 0; i < gb.firstBoard.length; i++) {
            for (int j = 0; j < gb.firstBoard[0].length; j++) {
                System.out.printf("%4d", gb.firstBoard[i][j]);
            }
            System.out.println();
        }
    }
}