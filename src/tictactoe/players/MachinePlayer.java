package tictactoe.players;

import tictactoe.GridManager;
import tictactoe.grids.Grid;

public class MachinePlayer extends Player {
    public MachinePlayer() {
        super("Machine", 'M');
    }

    public boolean makeNextMove(GridManager gridManager) {
        System.out.println("\nMacine, made a move (row, col)\n");
        return gridManager.setNextEmpty(getMoveCode());
    }
}
