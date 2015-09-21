import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
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


    private int gridSize;
    private int[] openSites;
    private int[] sites1d;
    private int[][] sites2D;
    private int virtualTop;
    private int virtualBottom;
    private  WeightedQuickUnionUF wquf;
    private static final int openIndicator = 1;
    //private static final int closedIndicator = 0;

    /*
     * Constructor - creates N-by-N grid, with all sites blocked
     */
    public Percolation(int N) {

        sites2D = new int[N][N];
      //  openSites = new int[N];
        gridSize = N;
        /*
         * for(int i=0; i<N; i ++){ for(int j=0; j<N; j++){ // sites2D[i][j] =
         * closedIndicator; }
         */
        wquf = new WeightedQuickUnionUF(N*N+2); 
        virtualTop = N * N;
        virtualBottom = N * N + 1;

     for (int i = 1; i <= N; i++) {
            wquf.union(virtualTop, map2Dto1D(1, i));
            wquf.union(virtualBottom, map2Dto1D(N, i));
        }
        

    }

    /*
     * This method maps a 2d array to a 1 d array using the row-major approach
     */
    private int map2Dto1D(int row, int column) {
        return gridSize * (row-1)  + (column-1);
        // sites1d[index2d] = sites2d[row][column];
        // return index2d;
    }

    private boolean isIndiceValid(int i, int j) {
        if (i <= 0 || j <= 0 || i > gridSize || j >gridSize) {
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
            throw new IndexOutOfBoundsException("Indices are out of bounds");
        }      
        int indicator = sites2D[i-1][j-1];
        if (indicator == openIndicator) {
            return true;
        }
        return false;
        
    }

    public void open(int i, int j) {

        if (!isIndiceValid(i, j)) {
            throw new IndexOutOfBoundsException("Indices are out of bounds");
        }
        if (!isOpen(i, j)) {
            sites2D[i-1][j-1] = openIndicator;
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
            throw new IndexOutOfBoundsException("Indices are out of bounds");
        }
        if(wquf.connected(map2Dto1D(i, j), virtualTop) && isOpen(i, j) ){
            return true;
        } 
        return false;
       
    }

    public boolean percolates() { // does the system percolate?

        return wquf.connected(virtualBottom, virtualTop);
    }

    public static void main(String[] args) { // test client (optional)
        /*Percolation testPerc1 = new Percolation(3);
        testPerc1.open(1, 1);
        testPerc1.open(1, 2);
        wquf.connected(1, 2);
        */

    }
}
