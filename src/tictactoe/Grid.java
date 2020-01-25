package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grid {
    public static final char INVALID_VALUE = '-';
    private int mMaxDimension;
    int mChanges;
    private List<List<Character>> mGrid;

    public Grid(int mxDimen){
        mMaxDimension = mxDimen ;
        mChanges = mxDimen * mxDimen;
        mGrid = new ArrayList<>();

        for(int i = 0; i< mMaxDimension; i++){
            ArrayList<Character> list = new ArrayList<>(mMaxDimension);
            for(int j = 0; j< mMaxDimension; j++)
                list.add(j, '-');
            mGrid.add(list);
        }
    }

    public Character getCharAt(int r, int c){
        return mGrid.get(r).get(c);
    }

    public boolean setCharAt(char ch, int r, int c){
        if(mGrid.get(r).get(c) != INVALID_VALUE ) return false;
        mGrid.get(r).set(c, ch);
        mChanges--;
        return true;
    }

    public void print(){
        for(List<Character> row: mGrid){
            for(char cell: row){
                System.out.print(cell + " ");
            }
            System.out.println("");
        }
    }

    public boolean isWinning(char code, int r, int c){
        //check top-down col
        int cells = 0;
        for(int i = 0; i< mMaxDimension; i++){
            if(Objects.equals(getCharAt(i, c),code))
                cells++;
        }
        if(cells == mMaxDimension) return true;

        //check left-right
        cells=0;
        for(int i = 0; i< mMaxDimension; i++){
            if(Objects.equals(getCharAt(r, i),code))
                cells++;
        }
        if(cells == mMaxDimension) return true;

        //check top-left to bottom-right diagonal
        cells = 0;
        if((r == c)){
            for(int i = 0; i< mMaxDimension; i++){
                if(mGrid.get(i).get(i) == code)
                    cells++;
            }
            if(cells == mMaxDimension) return true;
        }

        //check other diagonal
        cells = 0;
        if(r == (mMaxDimension - c)){
            for(int i = 0; i< mMaxDimension; i++){
                if(mGrid.get(i).get((mMaxDimension - i - 1)) == code)
                    cells++;
            }
            if(cells == mMaxDimension) return true;
        }

        return false;
    }

    public boolean canMove(){
        return mChanges>0;
    }

}
