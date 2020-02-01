package tictactoe;

import tictactoe.grids.EnhancedGrid;
import tictactoe.grids.Grid;
import tictactoe.grids.HexGrid;

public class GridManager {

    private int mExpandFactor;
    private int mDimen;

    private Grid mGrid;
    private GRID_TYPES mCurrentGridType;

    public static enum GRID_TYPES {ENHANCED, HEXA}

    public static final int DEFAULT_ENHANCED_GRID_DIMEN = 3;
    public static final int DEFAULT_HEX_GRID_DIMEN = 4;

    public static final char DEFAULT_CHAR = '-';

    public GridManager(int initDimen, GRID_TYPES grid_type) {
        mDimen = initDimen;
        mExpandFactor = initDimen;
        mCurrentGridType = grid_type;
        setUpGrid();
    }

    public void moveToNextLevel() {
        mDimen = mDimen * mExpandFactor;
        setUpGrid();
    }

    void setUpGrid() {
        GRID_TYPES grid_type = getGridType();
        if (grid_type == GRID_TYPES.ENHANCED)
            mGrid = new EnhancedGrid(mDimen);
        else
            mGrid = new HexGrid(mDimen);
    }

    public boolean isGameOver() {
        return !mGrid.canMove();
    }

    public void printGrids() {
        mGrid.print();
    }

    public boolean setCharAt(char code, int r, int c) {
        if (mGrid.getCharAt(r, c) != DEFAULT_CHAR) return false;
        return mGrid.setCharAt(code, r, c);
    }

    public boolean hasWon(char code, int r, int c) {
        return mGrid.hasWonGame(code, r, c);
    }

    public boolean setNextEmpty(char code){
        return mGrid.setNextEmpty(code);
    }

    GRID_TYPES getGridType() {
        return mCurrentGridType;
    }

}
