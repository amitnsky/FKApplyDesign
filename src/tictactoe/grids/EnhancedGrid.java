package tictactoe.grids;

import tictactoe.GridManager;

import java.util.List;
import java.util.Objects;

// A grid which can simulate any size of grid using only BasicGrids.
public class EnhancedGrid extends AbstractGrid {

    public EnhancedGrid(int mxDimen) {
        super(mxDimen);
    }

    @Override
    public boolean hasWonGame(char code, int r, int c) {
        return checkGameWon(code, 0, getMaxDimen(), 0, getMaxDimen());
    }

    // re and ce are exclusive.
    private boolean checkGameWon(char code, int rb, int re, int cb, int ce) {
        int dimen = re - rb;
        int baseDimen = 3;

        boolean won = false;
        boolean temp = true;

        if (dimen == baseDimen) {
            for (int i = 0; i < baseDimen; i++) {

                // check for rows
                temp = true;
                for (int j = 0; j < baseDimen; j++)
                    temp = temp && (getCharAt(rb + i, cb + j) == code);
                won = won || temp;

                //check for col
                temp = true;
                for (int j = 0; j < baseDimen; j++)
                    temp = temp && (getCharAt(rb + j, cb + i) == code);
                won = won || temp;

            }

            // check for diagonal
            temp = true;
            for (int i = 0; i < baseDimen; i++) {
                temp = temp && (getCharAt(rb + i, cb + i) == code);
            }
            won = won || temp;

            // check for diagonal top-right to bottom-left
            temp = true;
            for (int i = 0; i < baseDimen; i++) {
                temp = temp && (getCharAt(rb + i, cb + baseDimen - i - 1) == code);
            }
            won = won || temp;

            return won;
        }

        boolean[][] res = new boolean[3][3];

        /*
         * base dimen is 3,4 or 5
         * dimen can be any power of these 9, 16 or 25 or 27 etc.
         * dfactor is the dimension of smaller unit like 27->9 or 9->3
         */
        int dfactor = dimen / baseDimen;

        for (int i = 0; i < baseDimen; i++) {
            for (int j = 0; j < baseDimen; j++) {
                res[i][j] = checkGameWon(code, rb + i * dfactor, rb + (i + 1) * dfactor,
                        cb + j * dfactor, cb + (j + 1) * dfactor);
            }
        }

        for (int i = 0; i < baseDimen; i++) {
            // check for rows
            temp = true;
            for (int j = 0; j < baseDimen; j++)
                temp = temp && res[i][j];
            won = won || temp;

            // check for cols
            temp = true;
            for (int j = 0; j < baseDimen; j++)
                temp = temp && res[j][i];
            won = won || temp;
        }

        // check for diag top-left to bottom-right
        temp = true;
        for (int i = 0; i < baseDimen; i++)
            temp = temp && res[i][i];
        won = won || temp;

        // check for diag top-right to bottom-left
        temp = true;
        for (int i = 0; i < baseDimen; i++)
            temp = temp && res[i][baseDimen - i - 1];
        won = won || temp;

        return won;
    }

}
