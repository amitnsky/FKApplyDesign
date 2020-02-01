package tictactoe;

import tictactoe.players.MachinePlayer;
import tictactoe.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// manages the game, starts with startGame.
public class GameManager {
    private GridManager mGridManager;
    private List<Player> mPlayers;
    private int mCurrentPlayerIndex;
    private Scanner mScanner;


    public static enum GAME_MODE {HUMAN_VS_HUMAN, HUMAN_VS_MACHINE}

    // init the game, asks for no of players, corresponding code, returns true if initialisation was successful.
    private boolean init() {
        mScanner = new Scanner(System.in);
        mPlayers = new ArrayList<>();
        mCurrentPlayerIndex = -1;

        System.out.println("Enter\n"
                + "1: Human vs Human\n"
                + "2: Human vs Machine");


        int mode = mScanner.nextInt();

        GAME_MODE gametype = GAME_MODE.HUMAN_VS_MACHINE;

        if (mode == 1) gametype = GAME_MODE.HUMAN_VS_HUMAN;

        if (!takePlayersInfo(gametype))
            return false;

        System.out.println("Choose\n" + "1: Enhanced TicTacToe\n" + "2: Hex TicTacToe");
        int gridType = mScanner.nextInt();
        if (gridType == 1)
            mGridManager = new GridManager(GridManager.DEFAULT_ENHANCED_GRID_DIMEN, GridManager.GRID_TYPES.ENHANCED);
        else mGridManager = new GridManager(GridManager.DEFAULT_HEX_GRID_DIMEN, GridManager.GRID_TYPES.HEXA);


        return true;
    }

    // begins the game, takes no args, return void.
    // TODO: Refactor these return codes to enum.
    public int startGame() {
        if (!init()) return -100;

        while (true) {
            if (isGameOver()) {
                if (getCurrentPlayer().hasWon()) {
                    System.out.println(getCurrentPlayer().getName() + " has Won!");
                }
                System.out.println("Enter 1 to play next level, 0 to quit :");
                int continueGame = mScanner.nextInt();
                if (continueGame == 1) {
                    mGridManager.moveToNextLevel();
                    continue;
                }
                break;
            }
            if (!makeNextMove()) break;
        }

        if (getCurrentPlayer().hasWon())
            return mCurrentPlayerIndex;
        return -100;
    }

    private boolean makeNextMove() {

        mCurrentPlayerIndex = (mCurrentPlayerIndex + 1) % mPlayers.size();
        Player player = getCurrentPlayer();

        if (player instanceof MachinePlayer)
            return ((MachinePlayer) player).makeNextMove(mGridManager);

        return player.makeNextMove(mScanner, mGridManager);
    }

    private boolean isGameOver() {
        return mGridManager.isGameOver();
    }

    public Player getCurrentPlayer() {
        return mPlayers.get(mCurrentPlayerIndex);
    }

    private boolean takePlayersInfo(GAME_MODE mode) {
        int noOfPlayers = 1;
        if (mode == GAME_MODE.HUMAN_VS_HUMAN) noOfPlayers = 2;
        for (int i = 1; i <= noOfPlayers; i++) {

            String name = "Player " + i + 1;
            Character code = (char) (i + 65);

            System.out.println("Enter Name:");
            if (mScanner.hasNext())
                name = mScanner.next();

            System.out.println("Enter Char Code:");
            if (mScanner.hasNext())
                code = mScanner.next().charAt(0);

            mPlayers.add(new Player(name, code));
        }

        if (mode == GAME_MODE.HUMAN_VS_MACHINE) {
            System.out.println("Player 2: \nName: Machine\nCode: M\n");
            mPlayers.add(new MachinePlayer());
        }

        return true;
    }

    // clear up things before exiting game.
    public void endGame() {
    }

    // save game state
    public boolean saveGame() {
        return false;
    }

    // restore game state
    public boolean restoreGame() {
        return false;
    }


}
