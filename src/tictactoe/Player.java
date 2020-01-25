package tictactoe;

import java.util.Scanner;

public class Player {
    private String name;
    private Character moveCode;
    private int lastMoveX;
    private int lastMoveY;

    public Player(String name, Character code){
        this.name = name;
        this.moveCode = code;
    }

    public boolean makeNextMove(Grid grid){
        Scanner scanner = new Scanner(System.in);
        System.out.println(getName() + ", make a move. (row, col)");
        lastMoveX = scanner.nextInt();
        lastMoveY = scanner.nextInt();
        if(!grid.setCharAt(getMoveCode(), lastMoveX, lastMoveY)){
            return false;
        }
        return true;
    }

    // Returns whether this player is winning.
    public boolean isWinning(Grid grid){
        return grid.isWinning(getMoveCode(), lastMoveX, lastMoveY);
    }


    public String getName() {
        return name;
    }

    public Character getMoveCode() {
        return moveCode;
    }
}
