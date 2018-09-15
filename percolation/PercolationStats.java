/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {


    private Percolation p;
    private int trials;
    private double[] pThrshld;

    public PercolationStats(int n,
                            int trials)    // perform trials independent experiments on an n-by-n grid
    {

        this.trials = trials;
        pThrshld = new double[trials];

        for (int i = 0; i < trials; i++) {


            p = new Percolation(n);
            int x = p.numberOfOpenSites();
            pThrshld[i] = (x / (n * n));


        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(pThrshld);
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(pThrshld);
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return this.mean() - ((1.96 * this.stddev()) / Math.sqrt(this.trials));
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return this.mean() + ((1.96 * this.stddev()) / Math.sqrt(this.trials));

    }

    public static void main(String[] args) {
/*
        int n = StdIn.readInt();
        int trials = StdIn.readInt();*/
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, trials);

/*        StdOut.println("mean                    =" + ps.mean());
        StdOut.println("stddev                  =" + ps.stddev());
        StdOut.println(
                "95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");*/
        System.out.println("mean                    =" + ps.mean());
        System.out.println("stddev                  =" + ps.stddev());
        System.out.println(
                "95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");

    }
}
