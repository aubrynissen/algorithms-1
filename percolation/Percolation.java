/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    private boolean[] open1;
    private int[] id;
    private int n;
    private int topsite = -1;


    public Percolation(int n)             // create n-by-n grid, with all sites blocked
    {
        int numSites = n * n;
        id = new int[numSites];
        open1 = new boolean[numSites];

        for (int i = 0; i < numSites;
             i++)            // loop through sites
        {
            if (i < n) id[i] = -1;
            else {
                id[i] = i;
            }
            open1[i] = false;
            System.out.println(
                    "i: " + Integer.toString(i) + " | row: " + Integer
                            .toString(((i - (i % n)) / n) + 1)
                            + " | column: " + Integer.toString((i % n) + 1));
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


    private int root(int row,
                     int col)     // return root and update roots along the way (path compression)
    {
        int i = iFromCoord(row, col);
        int newID;

        while ((i != id[i]) && (topsite != id[i])) {

            newID = id[id[i]];
            id[i] = newID;
            i = newID;

        }
        return i;
    }


    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        int i = iFromCoord(row, col);

        open1[i] = true;

        // now that we've opened that site, see if any of the 4 adjacent sites are open. for each that is, join the components by calling union

        for (int x = -1; x < 1; x += 2) {

            int r = row + x;

            if (isOpen(r, col)) union(row, col, r, col);

        }

        for (int x = -1; x < 1; x += 2) {

            int c = col + x;

            if (isOpen(row, c)) union(row, col, row, c);

        }
    }

    private void union(int site1Row, int site1Col, int site2Row, int site2Col) {


        int site1i = iFromCoord(site1Row, site1Col);
        int site2i = iFromCoord(site2Row, site2Col);

        int site1id = id[site1i];
        int site2id = id[site2i];

        int minID;
        int maxID;

        if (site1id
                < site2id)  // find minimum id for root to set id values of component sites to (because -1 is the minimum and we want it to spread)
        {
            minID = site1id;
            maxID = site2id;
        }
        else {
            minID = site2id;
            maxID = site1id;
        }

        for (int i = 0; i < id.length; i++) {

            if (id[i] == maxID) id[i] = minID;

        }


    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        int i = iFromCoord(row, col);
        return open1[i];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (root(row, col) == topsite) {
            return true;
        }
        else {
            return false;
        }
    }

    public int numberOfOpenSites()       // number of open sites
    {
        int openSites = 0;

        for (int i = 0; i < open1.length; i++) {

            if (open1[i]) openSites++;

        }

        return openSites;
    }

    public boolean percolates()              // does the system percolate?
    {
        int x = id.length;
        boolean p = false;
        int lastRow = this.n;
        int firstCol = 1;
        int lastRowFirstCol = iFromCoord(lastRow, firstCol);

        for (int i = lastRowFirstCol; i < x; i++) {
            if (id[i] == -1) {
                p = true;
                break;
            }
        }

        return p;
    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        Percolation p = new Percolation(N);

        p.n = N;

        while (!p.percolates()) {

            int randomSite = StdRandom.uniform(N);

            int row = p.rowFromI(randomSite);
            int col = p.colFromI(randomSite);
            System.out.println(
                    "Identified site " + Integer.toString(randomSite) + " at coodinates (" + Integer
                            .toString(row) + ", " + Integer.toString(col) + ")");

            p.open(row, col);
            System.out.println(
                    "Opened site " + Integer.toString(randomSite) + " at coodinates (" + Integer
                            .toString(row) + ", " + Integer.toString(col) + ")");

        }

        int nos = p.numberOfOpenSites();

        System.out.println(Integer.toString(nos));
    }
}
