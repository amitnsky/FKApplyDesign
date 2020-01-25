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

    public boolean canMove(){
        return mChanges>0;
    }
    
}
