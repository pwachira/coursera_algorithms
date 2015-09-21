import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private int[] thresholds;
    private double stddev;
    private int T;
    private double mean;
    
    public PercolationStats(int N, int T) { // perform T independent experiments
                                   // on an N-by-N grid
        for (int i=i;i<=T; i++){
            
        }
        
 
        
    }

    public double mean() { // sample mean of percolation threshold
        return StdStats.mean(thresholds);
    }

    public double stddev() { // sample standard deviation of percolation
                             // threshold
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() { // low endpoint of 95% confidence interval
        return mean - (1.96 * stddev/Math.sqrt(T));
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return mean + (1.96 * stddev/Math.sqrt(T));
    }

    public static void main(String[] args) { // test client (described below)
    }
}
