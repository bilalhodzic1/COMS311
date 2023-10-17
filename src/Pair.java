public class Pair {
    int id;
    char direction;
    public Pair(int i, char d){
        direction = d;
        id = i;
    }
    public char getDirection(){
        return direction;
    }
    public int getId(){
        return id;
    }
    public void setId(int i){
        id = i;
    }
    public void setDirection(char d){
        direction = d;
    }
}
