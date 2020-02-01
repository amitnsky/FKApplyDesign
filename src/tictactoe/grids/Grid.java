package tictactoe.grids;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface Grid {

    boolean canMove();
    boolean isGameWon();
    boolean hasWonGame(char code, int r, int c);
    void setWon(char code);
    char getWonPlayerCode();
    boolean setCharAt(char ch, int r, int c);
    char getCharAt(int r, int c);
    void print();
    boolean setNextEmpty(char code);
    boolean unSetCharAt(int r, int c);
    int getMaxDimen();

}
