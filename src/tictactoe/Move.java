package tictactoe;

public class Move {
    public int getGridindex() {
        return gridindex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int gridindex;
    int x;
    int y;
    public Move(int index, int xm, int ym){
        gridindex = index;
        x = xm;
        y = ym;
    }



}
