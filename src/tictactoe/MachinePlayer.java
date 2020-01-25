package tictactoe;

public class MachinePlayer extends Player{
    public MachinePlayer() {
        super("Machine", 'M');
    }

    @Override
    public boolean makeNextMove(Grid grid) {
        System.out.println("\nMacine, made a move (row, col)\n");
        return grid.setNextEmpty(getMoveCode());
    }
}
