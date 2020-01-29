package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grid {
    public static final char INVALID_VALUE = '-';
    private int mMaxDimension;
    int mChanges;
    private List<List<Character>> mGrid;
    private Player mPlayerWon;
    private boolean isWon;
    private boolean isHexGrid;

    public Grid(int mxDimen) {
        mMaxDimension = mxDimen;
        mChanges = mxDimen * mxDimen;
        mGrid = new ArrayList<>();
        isWon = false;

        for (int i = 0; i < mMaxDimension; i++) {
            ArrayList<Character> list = new ArrayList<>(mMaxDimension);
            for (int j = 0; j < mMaxDimension; j++)
                list.add(j, '-');
            mGrid.add(list);
        }
    }

    //init hexagonal grid
    public Grid(int mxDimen, boolean isHex){
        mMaxDimension = mxDimen;
        isHexGrid = isHex;

        //change accordingly
        mChanges = mxDimen * mxDimen;
        mGrid = new ArrayList<>();
        isWon = false;

        for (int i = 0; i < mMaxDimension; i++) {
            ArrayList<Character> list = new ArrayList<>(mMaxDimension);
            for (int j = 0; j < mMaxDimension; j++)
                list.add(j, '-');
            mGrid.add(list);
        }
    }

    public Character getCharAt(int r, int c) {
        r = r%3;
        c = c%3;
        return mGrid.get(r).get(c);
    }

    public boolean setCharAt(char ch, int r, int c) {
        r = r % 3;
        c = c % 3;
        if (mGrid.get(r).get(c) != INVALID_VALUE) return false;
        mGrid.get(r).set(c, ch);
        mChanges--;
        return true;
    }

    public void print() {
        for (List<Character> row : mGrid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("");
        }
    }

    public boolean isWinning(Player player) {
        char code = player.getMoveCode();
        int r = player.getLastMoveX() % 3;
        int c = player.getLastMoveY() % 3;
        //check top-down col
        int cells = 0;
        for (int i = 0; i < mMaxDimension; i++) {
            if (Objects.equals(getCharAt(i, c), code))
                cells++;
        }
        if (cells == mMaxDimension) return true;

        //check left-right
        cells = 0;
        for (int i = 0; i < mMaxDimension; i++) {
            if (Objects.equals(getCharAt(r, i), code))
                cells++;
        }
        if (cells == mMaxDimension) return true;

        //check top-left to bottom-right diagonal
        cells = 0;
        if ((r == c)) {
            for (int i = 0; i < mMaxDimension; i++) {
                if (mGrid.get(i).get(i) == code)
                    cells++;
            }
            if (cells == mMaxDimension) return true;
        }

        //check other diagonal
        cells = 0;
        if (r == (mMaxDimension - c)) {
            for (int i = 0; i < mMaxDimension; i++) {
                if (mGrid.get(i).get((mMaxDimension - i - 1)) == code)
                    cells++;
            }
            if (cells == mMaxDimension) return true;
        }

        return false;
    }

    public boolean canMove() {
        return (!getIsWon() && mChanges > 0);
    }

    public boolean setNextEmpty(char code) {
        if (!canMove()) return false;
        for (int i = 0; i < mMaxDimension; i++) {
            for (int j = 0; j < mMaxDimension; j++) {
                if (mGrid.get(i).get(j) != INVALID_VALUE) continue;
                System.out.println(i + " " + j);
                mGrid.get(i).set(j, code);
                return true;
            }
        }
        return true;
    }

    public List<List<Character>> getGrid() {
        return mGrid;
    }

    public void setPlayerWon(Player playerWon){
        mPlayerWon = playerWon;
        setWon(true);
    }

    public void setWon(boolean won){
        isWon = won;
    }

    public Player getPlayerWon() {
        return mPlayerWon;
    }

    public boolean getIsWon() {
        return isWon;
    }
}
