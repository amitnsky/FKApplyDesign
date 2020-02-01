package tictactoe.grids;

public class HexGrid extends AbstractGrid {
    int mGridSize;
    int mHexGridDimen;
    public static final char INVALID_HEX_CHAR = '*';

    // @param mxDimen is min no of hex grids for gameplay, default is 4.
    public HexGrid(int mxDimen) {
        super(mxDimen * 2 - 1);
        mGridSize = mxDimen * 2 - 1;
        mHexGridDimen = mxDimen;

        for (int i = 0; i < mGridSize; i++) {
            for (int j = 0; j < mGridSize; j++) {
                if ((i + j) % 2 != 1)
                    setCharAt(INVALID_HEX_CHAR, i, j);
            }
        }
    }


    @Override
    public boolean hasWonGame(char code, int r, int c) {
        return checkGameWon(code);
    }

    private boolean checkGameWon(char code) {
        return checkDiagonalLeftToRight(code) || checkDiagonalRightToLeft(code);
    }

    boolean checkDiagonalLeftToRight(char code) {

        for (int sum = 1; sum < mGridSize; sum += 2) {
            for (int i = 0; i <= sum; i += sum) {
                int j = sum - i;
                int count = 0;
                for (int r = i, c = j; r < mGridSize && c < mGridSize; ) {
                    if (getCharAt(r, c) == code)
                        count++;
                    else
                        count = 0;
                    if (count >= mHexGridDimen) return true;
                    r++;
                    c++;
                }
            }
        }


        return false;
    }

    boolean checkDiagonalRightToLeft(char code) {

        for (int sum = mHexGridDimen - 1; sum <= (2 * mGridSize - mHexGridDimen - 1); sum += 2) {

            int count = 0;

            for (int i = 0; i < mGridSize; i++) {

                int j = sum - i;
                if (j < 0 || j >= mGridSize) continue;

                if (getCharAt(i, j) == code)
                    count++;
                else
                    count = 0;

                if (count >= mHexGridDimen) return true;
            }
        }

        return false;
    }

}
