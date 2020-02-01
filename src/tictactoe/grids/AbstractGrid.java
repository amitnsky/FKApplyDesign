package tictactoe.grids;

import java.util.ArrayList;
import java.util.List;

import static tictactoe.GridManager.DEFAULT_CHAR;

// this is a basic 3 x 3 grid.

public abstract class AbstractGrid implements Grid {

    private int mMaxDimension;
    int mChanges;
    private List<List<Character>> mGrid;
    private char mWonPlayerCode;
    private boolean isWon;

    public AbstractGrid(int mxDimen) {
        mMaxDimension = mxDimen;
        mChanges = mxDimen * mxDimen;
        mGrid = new ArrayList<>();
        isWon = false;
        mWonPlayerCode = DEFAULT_CHAR;

        for (int i = 0; i < mMaxDimension; i++) {
            ArrayList<Character> list = new ArrayList<>(mMaxDimension);
            for (int j = 0; j < mMaxDimension; j++)
                list.add(j, '-');
            mGrid.add(list);
        }
    }

    public char getCharAt(int r, int c) {
        return mGrid.get(r).get(c);
    }

    @Override
    public boolean setCharAt(char ch, int r, int c) {
        if (getCharAt(r, c) == HexGrid.INVALID_HEX_CHAR) return false;
        mGrid.get(r).set(c, ch);
        if (ch == DEFAULT_CHAR) mChanges++;
        else mChanges--;
        if (hasWonGame(ch, r, c))
            setWon(ch);
        return true;
    }

    @Override
    public void print() {

        System.out.print("\n   ");
        for (int i = 0; i < mGrid.size(); i++)
            System.out.print(i + "  ");
        System.out.println();

        int j = 0;
        for (List<Character> row : mGrid) {

            System.out.print(j + "  ");
            for (char cell : row) {
                System.out.print(cell + "  ");
            }
            System.out.println("");
            j++;
        }

        System.out.println("");

    }

    @Override
    public boolean unSetCharAt(int r, int c) {
        if (mGrid.get(r).get(c) == DEFAULT_CHAR) return false;
        return setCharAt(DEFAULT_CHAR, r, c);
    }

    @Override
    public abstract boolean hasWonGame(char code, int r, int c);

    @Override
    public void setWon(char code) {
        mWonPlayerCode = code;
        isWon = true;
    }

    @Override
    public char getWonPlayerCode() {
        return mWonPlayerCode;
    }

    @Override
    public boolean canMove() {
        return (!isGameWon() && mChanges > 0);
    }

    @Override
    public boolean isGameWon() {
        return isWon;
    }

    @Override
    public boolean setNextEmpty(char code) {
        if (!canMove()) return false;
        for (int i = 0; i < mMaxDimension; i++) {
            for (int j = 0; j < mMaxDimension; j++) {
                if (mGrid.get(i).get(j) != DEFAULT_CHAR) continue;
                System.out.println(i + " " + j);
                mGrid.get(i).set(j, code);
                return true;
            }
        }
        return true;
    }

    @Override
    public int getMaxDimen() {
        return mMaxDimension;
    }

}
