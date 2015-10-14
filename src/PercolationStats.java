import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * This class tests the percolation operation using an N*N grid
 * 
 * @author peterwachira
 *
 */
public class PercolationStats {

    private static double mean;
    private static double stddev;
    private  double[] thresholds;
    private  int gridSize;
    private int gridSites;
    private int countOpenSites;
    private int T;
    private int[] valuesSelected;
    private Percolation pc;

    public PercolationStats(int N, int T) {
        gridSize = N;
        gridSites = N * N;
        thresholds = new double[T];
        for (int i = 1; i <= T; i++) {

            countOpenSites = 0;
            pc = new Percolation(N);

            valuesSelected = new int[gridSites];
            // loop to populate the array
            for (int k = 1; k <= (gridSites); k++) {
                valuesSelected[k - 1] = k;
            }

            // randomize the array
            StdRandom.shuffle(valuesSelected);
            // int currentSelected = StdRandom.uniform(1,(N*N +1));
            // loop array in order

            for (int m = 1; m <= valuesSelected.length; m++) {
                int x = generateXIndex(valuesSelected[m - 1]);
                int y = generateYIndex(valuesSelected[m - 1]);
                pc.open(x, y);
                countOpenSites++;
                if (pc.percolates()) {
                    break;
                }
            }

            thresholds[i - 1] = (double) countOpenSites / (gridSites);
            valuesSelected = null;
          
        }
       mean();
       stddev();

    }
 
    private int generateXIndex(int currentSelected) {
        return currentSelected % gridSize == 0 ? gridSize : currentSelected
                % gridSize;

    }

    private int generateYIndex(int currentSelected) {
        return (int) Math.ceil((double) currentSelected / gridSize);

    }

    public double mean() { // sample mean of percolation threshold
        mean = StdStats.mean(thresholds);
        return mean;
    }

    public double stddev() { // sample standard deviation of percolation
                             // threshold
        stddev =  StdStats.stddev(thresholds);
        return stddev;
    }

    public double confidenceLo() { // low endpoint of 95% confidence interval
        return mean - (1.96 * stddev / Math.sqrt(T));
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return mean + (1.96 * stddev / Math.sqrt(T));
    }

    public static void main(String[] args) {
        int N = StdIn.readInt() ;
        int T = StdIn.readInt();
       
        PercolationStats pcs = new PercolationStats(N,T );

        pcs.getClass().getName();
        StdOut.println();
        StdOut.println("Mean                   =" + mean);
        StdOut.println("Stddev                 =" + stddev);
        StdOut.print("95% Confidence level   ="
                + (mean - (1.96 * stddev / Math.sqrt(T))));
        StdOut.println(", " + (mean + (1.96 * stddev / Math.sqrt(T))));
       

    }
}
