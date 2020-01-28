package tictactoe;

import sun.java2d.loops.GeneralRenderer;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GridManager {
    private int mExpandFactor;
    private int mDimen;
    private int level;
    public static final int DEFAULT_GRID_DIMEN = 3;
    public static final int LEVEL_0 = 0;
    public int mCurrentGridIndex;
    private List<Grid> mGridsList;
    private static GridManager mGridManager;

    public static GridManager getInstance() {
        if (mGridManager == null) {
            mGridManager = GridManager.getInstance(DEFAULT_GRID_DIMEN, LEVEL_0);
        }
        return mGridManager;
    }

    public static GridManager getInstance(int initDimen, int initLevel) {
        if (mGridManager == null) {
            mGridManager = new GridManager();
            mGridManager.mGridsList = new ArrayList<>();
            mGridManager.getGridsList().add(new Grid(initDimen));
            mGridManager.mGridsList.add(new Grid(initDimen));
            mGridManager.setDimen(initDimen);
            mGridManager.setExpandFactor(initDimen);
            mGridManager.mCurrentGridIndex = 0;
            mGridManager.setGameLevel(GridManager.LEVEL_0);
        }
        return mGridManager;
    }

    public boolean isWinning(Player player) {

        GridManager manager = GridManager.getInstance();

        if (level == LEVEL_0) {
            return manager.getGridsList().get(0).isWinning(player);
        }

        int gridIndex = manager.getGridIndex(player.getLastMoveX(), player.getLastMoveY());

        // check this col grids
        if (manager.getGridsList().get(gridIndex % 3).isWinning(player) &&
                manager.getGridsList().get(((gridIndex % 3) + 3)).isWinning(player) &&
                manager.getGridsList().get((gridIndex % 3) + 6).isWinning(player)) {
            return true;
        }

        // check this row grids
        if (manager.getGridsList().get((gridIndex / 3) * 3).isWinning(player) &&
                manager.getGridsList().get(((gridIndex / 3) * 3 + 1)).isWinning(player) &&
                manager.getGridsList().get((gridIndex % 3) * 3 + 2).isWinning(player)) {
            return true;
        }

        // top-left to bottom-right diagonal
        if (gridIndex / 3 == gridIndex % 3)
            if (manager.getGridsList().get(0).isWinning(player) &&
                    manager.getGridsList().get(4).isWinning(player) &&
                    manager.getGridsList().get(8).isWinning(player)) {
                return true;
            }

        // top-right to bottom-left diagonal
        if (gridIndex / 3 == (3 - gridIndex % 3 - 1))
            if (manager.getGridsList().get(2).isWinning(player) &&
                    manager.getGridsList().get(4).isWinning(player) &&
                    manager.getGridsList().get(6).isWinning(player)) {
                return true;
            }

        return false;
    }

    // if can move in any of grids, return true.
    public boolean canMove() {
        return GridManager.getInstance().getGridsList().stream().anyMatch((g) -> !g.getIsWon() && g.canMove());
    }

    public void moveToNextLevel() {
        GridManager manager = GridManager.getInstance();
        manager.setGameLevel(manager.getGameLevel() + 1);
        manager.setGridsList(new ArrayList<>());
        for (int i = 0; i < (manager.getDimen() * manager.getExpandFactor()); i++) {
            System.out.println("Adding new lists");
            manager.getGridsList().add(new Grid(manager.getDimen()));
        }
    }

    public void print() {
        List<Grid> gridList = GridManager.getInstance().getGridsList();
        if (GridManager.getInstance().getGameLevel() == GridManager.LEVEL_0) {
            gridList.get(0).print();
            return;
        }
        int mxDimen = GridManager.getInstance().getDimen();
        int temp = level;
        while (temp > 0) {
            mxDimen = mxDimen * getExpandFactor();
            temp--;
        }
        for (int row = 0; row < mxDimen; row++) {
            for (int col = 0; col < mxDimen; col++) {
                int index = getGridIndex(row, col);
                System.out.print(gridList.get(index).getGrid().get(row % 3).get(col % 3) + " ");
                if (col % 3 == 2) {
                    System.out.print("| ");
                }
            }
            System.out.println("");
        }
    }



    public void changeCurrentGrid() {
        GridManager manager = GridManager.getInstance();
       // System.out.println("Changing grid actually!!");
        if (manager.canMove()) {
            while (!manager.getCurrentGrid().canMove()) {
                manager.setCurrentGridIndex((manager.getCurrentGridIndex()+1) % 9);
             //   System.out.println("This is index " + manager.getCurrentGridIndex());
            }
        }
    }

    public void setCurrentGridIndex(int index){
        mCurrentGridIndex = index;
    }

    public int getCurrentGridIndex(){
        return mCurrentGridIndex;
    }

    private int getGridIndex(int r, int c) {
        int dimen = GridManager.getInstance().getDimen();
        return (r / dimen + c / dimen);
    }

    public boolean makeNextMove() {
        return false;
    }

    public void setmExpandFactor(int mExpandFactor) {
        this.mExpandFactor = mExpandFactor;
    }

    public int getExpandFactor() {
        return mExpandFactor;
    }

    public int getDimen() {
        return mDimen;
    }

    public List<Grid> getGridsList() {
        return mGridsList;
    }

    public void setDimen(int mDimen) {
        this.mDimen = mDimen;
    }

    private void setExpandFactor(int d) {
        mExpandFactor = d;
    }

    public void setGameLevel(int x) {
        level = x;
    }

    public int getGameLevel() {
        return level;
    }

    public void setGridsList(List<Grid> gridsList) {
        mGridsList = gridsList;
    }

    public Grid getCurrentGrid() {
        return mGridsList.get(mCurrentGridIndex);
    }
}
