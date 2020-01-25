package tictactoe;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

// manages the game, starts with startGame.
public class GameManager {
    private Grid mGrid;
    private List<Player> mPlayers;
    private int mMaxPlayers;
    private int mCurrentPlayerIndex;
    private Scanner mScanner;

    private Logger mLogger;
    // init the game, asks for no of players, corresponding code, returns true if initialisation was successful.
    private boolean init(){
        mLogger = Logger.getLogger(this.getClass().getSimpleName());
        mScanner = new Scanner(System.in);
        mPlayers = new ArrayList<>();
        mCurrentPlayerIndex = 0;
        mMaxPlayers = 2;
        mGrid = new Grid(3);

        System.out.println("Choose the gaming mode: "
                + "\n1: Human vs Machine "
                + " \n2: Human vs Human");

        int mode = 1;
        if(!mScanner.hasNextInt()) return false;
        mode = mScanner.nextInt();

        if(!takePlayersInfo(mPlayers, mode))
            return false;

        return true;
    }

    // begins the game, takes no args, return void.
    public void startGame(){
       if(!init()) return;
        mLogger.entering("Game Manager", "StartGame");

        // keep playing while there is a single box left empty
        while (mGrid.canMove()){
            //print grid
            mGrid.print();
            Player currentPlayer = mPlayers.get(mCurrentPlayerIndex);

            //keep asking for input from current player, unless he provides a correct input.
            while (!currentPlayer.makeNextMove(mGrid));
            mCurrentPlayerIndex = (mCurrentPlayerIndex + 1)%mMaxPlayers;

            if(currentPlayer.isWinning(mGrid)){
                mGrid.print();
                System.out.println(currentPlayer.getName() + " Won!!");
                return;
            }else
                continue;
        }
        if(!mGrid.canMove()){
            System.out.println("Draw!!");
            return;
        }
        mLogger.exiting("Game Manager", "StartGame");

    }

    // clear up things before exiting game.
    private void exitGame(){

    }

    private boolean takePlayersInfo(List<Player> playerList, int nOfPlayers){
        for (int i=0; i<nOfPlayers; i++){

            String name = "Player " + i + 1;
            Character code = (char) (i + 65);

            System.out.println("Enter Name:");
            if(mScanner.hasNext())
                name = mScanner.next();

            System.out.println("Enter Char Code:");
            if(mScanner.hasNext())
                code = mScanner.next().charAt(0);

            playerList.add(new Player(name, code));
        }

        if(nOfPlayers == 1){
            System.out.println("Player 2: \nName: Machine\nCode: M\n");
            playerList.add(new MachinePlayer());
        }
        return true;
    }
}
