/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    public int n;
    private boolean[] opened;
    private UnionFind uF;
    private int numSites;
    private int numOpenSites;


    public Percolation(int n)             // create n-by-n grid, with all sites blocked
    {

        numSites = (n * n) + 2;
        opened = new boolean[numSites];
        numOpenSites = 0;
        uF = new UnionFind(numSites);

        for (int i = 0; i < numSites;
             i++)            // loop through sites
        {
            uF.id[i] = i;
            opened[i] = false;
/*            System.out.println(
                    "i: " + Integer.toString(i) + " | row: " + Integer
                            .toString(((i - (i % n)) / n) + 1)
                            + " | column: " + Integer.toString((i % n) + 1));*/
        }
    }

    private int iFromCoord(int row, int col) {

        int i = (((row - 1) * this.n) + (col - 1));

        return i;

    }

    private int colFromI(int i) {

        int col = (i % this.n) + 1;

        return col;

    }

    private int rowFromI(int i) {

        int row = ((i - ((i % this.n) - 1)) / this.n) + 1;

        return row;

    }


    public void open(int row, int col)    // open site (row, col) if it is not open already
    {


        if (!isOpen(row, col)) {
            int i = iFromCoord(row, col);
            int topi;
            int bottomi;


            if (col > 1) {

                int lefti = iFromCoord(row, col - 1);

                if (opened[lefti]) uF.union(i, lefti);

            }

            if (col < n) {

                int righti = iFromCoord(row, col + 1);

                if (opened[righti]) uF.union(i, righti);

            }

            if (row > 1) {

                topi = iFromCoord(row - 1, col);

                if (opened[topi]) uF.union(i, topi);

            }
            else {
                topi = numSites - 2;

                uF.union(i, topi);

            }

            if (row < n) {

                bottomi = iFromCoord(row + 1, col);

                if (opened[bottomi]) uF.union(i, bottomi);

            }
            else {
                bottomi = numSites - 1;

                uF.union(i, bottomi);

            }

            // continue for topi and bottomi


            opened[i] = true;
            numOpenSites++;
        }

    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        int i = iFromCoord(row, col);
        return opened[i];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        int i = iFromCoord(row, col);
        return (uF.root(i) == uF.root(numSites - 2));

    }

    public int numberOfOpenSites()       // number of open sites
    {
        return numOpenSites;
    }

    public boolean percolates()              // does the system percolate?
    {
/*        System.out.println(
                "Root at topsite is " + Integer.toString(uF.root(numSites - 2))
                        + " and root at bottom site is "
                        + Integer
                        .toString(uF.root(numSites - 1)) + "  and p.percolates is "
                        + isFull(n + 1, 2));*/
        return isFull(n + 1, 2);

    }

    public static void main(String[] args) {

        int n = StdIn.readInt();

        Percolation p = new Percolation(n);

        p.n = n;

        p.uF = new UnionFind(p.numSites);

        while (!p.percolates()) {

            // for (int i = 0; i < 10; i++) {

            // if (!p.percolates()) {

            int randomSite = StdRandom.uniform(p.numSites - 2);

            int row = p.rowFromI(randomSite);
            int col = p.colFromI(randomSite);
            /*
            System.out.println(
                    "Identified site " + Integer.toString(randomSite) + " at coodinates ("
                            + Integer
                            .toString(row) + ", " + Integer.toString(col) + ")");
            */

            p.open(row, col);
            /*
            System.out.println(
                    "Opened site " + Integer.toString(randomSite) + " at coodinates (" + Integer
                            .toString(row) + ", " + Integer.toString(col) + ")");
            */

            // }
        }

        /*int nos = p.numberOfOpenSites();

        System.out.println(Integer.toString(nos));*/
    }
}
