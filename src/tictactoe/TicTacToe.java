package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        int []score = new int[]{0, 0};
        while(true) {
            GameManager manager = new GameManager();
            int player = manager.startGame();
            if(player<2) score[player]++;

            System.out.println("Enter 1 to restart game, 2 to exit.");


            Scanner scanner = new Scanner(System.in);
            int mode = scanner.nextInt();
            if (mode == 1) {
                continue;
            }
            break;
        }
        System.out.println("Score: \nPlayer1: " + score[0] + "\nPlayer2: " + score[1]);
    }
}
