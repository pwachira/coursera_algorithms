
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * This class models a percolation operation using an N*N grid with intially all
 * sites closed.
 * 
 * It provides methods associated with opening a given site, and checking if the
 * system percolates
 * 
 * @author peterwachira
 *
 */
public class Percolation {


    private static final byte OPEN_INDICATOR = 1;
    private int gridSize;
    private byte[][] sites2D;
    private int virtualTop;
    private int virtualBottom;
    private  WeightedQuickUnionUF wquf;
 

    /*
     * Constructor - creates N-by-N grid, with all sites blocked
     */
    public Percolation(int N) {

        sites2D = new byte[N][N];
<<<<<<< HEAD
        gridSize = N;
=======

        gridSize = N;

>>>>>>> 21f57961701a920a3cf77f8fca8f33059396a409
        wquf = new WeightedQuickUnionUF(N*N+2); 
        virtualTop = N * N;
        virtualBottom = N * N + 1;

     for (int i = 1; i <= N; i++) {
            wquf.union(virtualTop, map2Dto1D(1, i));
            wquf.union(virtualBottom, map2Dto1D(N, i));
        }
        

    }

    /*
     * Maps a 2d array to a 1d array using the row-major approach
     * 
     * @param row the row number based on a 1- based index
<<<<<<< HEAD
     * @param column
=======
     * @param 
>>>>>>> 21f57961701a920a3cf77f8fca8f33059396a409
     * @returns int
     */
    private int map2Dto1D(int row, int column) {
        return gridSize * (row-1)  + (column-1);

    }

    private boolean isIndiceValid(int i, int j) {
        if (i <= 0 || j <= 0 || i > gridSize || j > gridSize) {
            return false;
        }
        return true;
    }

    private void tryUnionAdjacent(int i, int j, int site1dValue) {
        if (isIndiceValid(i, j) && isOpen(i, j)) {
            int adj1dValue = map2Dto1D(i, j);
            if (!wquf.connected(adj1dValue, site1dValue)) {
                wquf.union(adj1dValue, site1dValue);
            }
        }
    }

    public boolean isOpen(int i, int j) {
        if (!isIndiceValid(i, j)) {
<<<<<<< HEAD
            throw new IndexOutOfBoundsException("Indices are out of bounds: i="+i+"j = "+j);
=======
            throw new IndexOutOfBoundsException(String.format("Indices are out of bounds i:%d j:%d",i,j));
>>>>>>> 21f57961701a920a3cf77f8fca8f33059396a409
        }      
        int indicator = sites2D[i-1][j-1];
        if (indicator == OPEN_INDICATOR) {
            return true;
        }
        return false;
        
    }

    public void open(int i, int j) {

        if (!isIndiceValid(i, j)) {
<<<<<<< HEAD
            throw new IndexOutOfBoundsException("Indices are out of bounds: i="+i+"j = "+j);
=======
            throw new IndexOutOfBoundsException(String.format("Indices are out of bounds i:%d j:%d",i,j));
>>>>>>> 21f57961701a920a3cf77f8fca8f33059396a409
        }
        if (!isOpen(i, j)) {
            sites2D[i-1][j-1] = OPEN_INDICATOR;
            int site1dvalue = map2Dto1D(i, j);
            int aboveX = i;
            int aboveY = j - 1;
            int leftX = i - 1;
            int leftY = j;
            int rightX = i + 1;
            int rightY = j;
            int belowX = i;
            int belowY = j + 1;
            tryUnionAdjacent(aboveX, aboveY, site1dvalue);
            tryUnionAdjacent(belowX, belowY, site1dvalue);
            tryUnionAdjacent(rightX, rightY, site1dvalue);
            tryUnionAdjacent(leftX, leftY, site1dvalue);

        }
    }


    public boolean isFull(int i, int j) { // is site (row i, column j) full?
        if (!isIndiceValid(i, j)) {
<<<<<<< HEAD
            throw new IndexOutOfBoundsException("Indices are out of bounds: i="+i+"j = "+j);
=======
            throw new IndexOutOfBoundsException("Indices are out of bounds");
>>>>>>> 21f57961701a920a3cf77f8fca8f33059396a409
        }
        if (wquf.connected(map2Dto1D(i, j), virtualTop) && isOpen(i, j)) {
            return true;
        } 
        return false;
       
    }

    public boolean percolates() { 

        return wquf.connected(virtualBottom, virtualTop);
    }

    public static void main(String[] args) { 
       

    }
}
