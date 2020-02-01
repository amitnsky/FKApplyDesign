package tictactoe.players;

import tictactoe.GridManager;

import java.util.Scanner;

public class Player {
    private String name;
    private Character moveCode;
    private int lastMoveX;
    private int lastMoveY;
    private boolean hasWon;

    public Player(String name, Character code) {
        this.name = name;
        this.moveCode = code;
    }

    public String getName() {
        return name;
    }

    public Character getMoveCode() {
        return moveCode;
    }

    public int getLastMoveX() {
        return lastMoveX;
    }

    public int getLastMoveY() {
        return lastMoveY;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public void setWon(boolean val) {
        hasWon = true;
    }


    public boolean makeNextMove(Scanner scanner, GridManager gridManager) {

        if (gridManager.isGameOver()) return false;

        while (true) {
            gridManager.printGrids();

            System.out.println(this.getName() + " enter (row col):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!gridManager.setCharAt(getMoveCode(), row, col)) {
                System.out.println("That is a wrong input, Let's try again!!");
                continue;
            }
            break;
        }

        if (gridManager.hasWon(getMoveCode(), getLastMoveX(), getLastMoveY()))
            this.setWon(true);

        return true;
    }
}
