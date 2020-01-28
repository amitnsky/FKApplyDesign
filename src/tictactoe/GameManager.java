package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Logger;

// manages the game, starts with startGame.
public class GameManager {
    private GridManager mGridManager;
    private List<Player> mPlayers;
    private int mMaxPlayers;
    private int mCurrentPlayerIndex;
    private Scanner mScanner;
    private Logger mLogger;

    // init the game, asks for no of players, corresponding code, returns true if initialisation was successful.
    private boolean init() {
        mLogger = Logger.getLogger(this.getClass().getSimpleName());
        mScanner = new Scanner(System.in);
        mPlayers = new ArrayList<>();
        mCurrentPlayerIndex = 0;
        mMaxPlayers = 2;
        mGridManager = GridManager.getInstance(3, GridManager.LEVEL_0);

        System.out.println("Choose the gaming mode: "
                + "\n1: Human vs Machine "
                + " \n2: Human vs Human");

        int mode = 1;
        if (!mScanner.hasNextInt()) return false;
        mode = mScanner.nextInt();

        if (!takePlayersInfo(mPlayers, mode))
            return false;

        return true;
    }

    // begins the game, takes no args, return void.
    public int startGame() {
        if (!init()) return 100;
        mLogger.entering("Game Manager", "StartGame");
        mGridManager.moveToNextLevel();
        // keep playing while there is a single box left empty
        while (mGridManager.canMove()) {
            //print grid
            mGridManager.print();
            Player currentPlayer = mPlayers.get(mCurrentPlayerIndex);

            //keep asking for input from current player, unless he provides a correct input.
            while (!makeNextMove(currentPlayer)) ;

            Move lastMove = new Move(mGridManager.getCurrentGridIndex(), currentPlayer.getLastMoveX(), currentPlayer.getLastMoveY());
            System.out.println("Press 1 to undo last move, 2 to continue: ");
            int opt = mScanner.nextInt();
            if(opt == 1){
                mGridManager.undoMove(lastMove);
                continue;
            }

            if (isWinningGame(currentPlayer)) {
                mGridManager.print();
                System.out.println(currentPlayer.getName() + " Won!!");

                // if wants to continue, reset and start again
                // else break
                System.out.println("Press\n" + "1 to play for next level.\n" + "0 to exit.\n");
                int shouldContinue = mScanner.nextInt();
                if(shouldContinue == 1){
                    mGridManager.moveToNextLevel();
                    continue;
                }
                break;
            }else if(mGridManager.getCurrentGrid().isWinning(currentPlayer)){
                System.out.println("This grid is won. Changing Grid.\n");
                mGridManager.getCurrentGrid().setPlayerWon(currentPlayer);
                mGridManager.changeCurrentGrid();
            }


            mCurrentPlayerIndex = (mCurrentPlayerIndex + 1) % mMaxPlayers;

        }
        if (!mGridManager.canMove()) {
            System.out.println("Draw!!");
            return 100;
        }
        mLogger.exiting("Game Manager", "StartGame");
        return mCurrentPlayerIndex;
    }

    private boolean makeNextMove(Player player) {
        GridManager manager = GridManager.getInstance();
        return player.makeNextMove(manager.getCurrentGrid());
    }

    private boolean isWinningGame(Player currentPlayer) {
        //  currentPlayer.getMoveCode(), currentPlayer.getLastMoveX(), currentPlayer.getLastMoveY())
        return GridManager.getInstance().isWinning(currentPlayer);
    }

    // clear up things before exiting game.
    private void exitGame() {

    }

    private boolean takePlayersInfo(List<Player> playerList, int nOfPlayers) {
        for (int i = 0; i < nOfPlayers; i++) {

            String name = "Player " + i + 1;
            Character code = (char) (i + 65);

            System.out.println("Enter Name:");
            if (mScanner.hasNext())
                name = mScanner.next();

            System.out.println("Enter Char Code:");
            if (mScanner.hasNext())
                code = mScanner.next().charAt(0);

            playerList.add(new Player(name, code));
        }

        if (nOfPlayers == 1) {
            System.out.println("Player 2: \nName: Machine\nCode: M\n");
            playerList.add(new MachinePlayer());
        }
        return true;
    }
}
