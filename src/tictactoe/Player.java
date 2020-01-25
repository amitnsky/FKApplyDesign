package tictactoe;

public class Player {

    /**
     * tell if this player can make a move further.
     * If return is true, only then make next move otherwise match is draw.
     */
    private boolean canMakeNextMove() {
        return false;
    }

    // returns true if move was successful.
    public boolean makeNextMove(){
        return true;
    }

    // Returns whether this player is winning.
    public boolean isWinning(){
        return false;
    }
}
