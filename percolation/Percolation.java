/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {

    /*    private boolean[][] openFromCoord;
        private boolean[] openFromNum;
        private int[][] idFromCoord;
        private int[] idFromNum;*/
    private boolean[] open;
    private int[] id;
    private int n;
    private int topsite = -1;


    public Percolation(int n)             // create n-by-n grid, with all sites blocked
    {
        int numSites = n * n;
        id = new int[numSites];
        open = new boolean[numSites];

        for (int i = 0; i < numSites;
             i++)            // loop through rows
        {

            updateID(i, i);
            updateOpenFromInt(i, false);
        }
    }


    private int root(int row,
                     int col)     //return root and update roots along the way (path compression)
    {
        int i = (((row - 1) * this.n) + col);
        int newID;

        while ((i != id[i]) && (id[i] != topsite)) {

            newID = idFromNum[idFromNum[i]];
            updateID(i, newID);
            i = newID;

        }
        return i;
    }

    private void updateID(int i, int newID) {

        int col = (i % this.n) + 1;
        int row = (i - col) / this.n;

        idFromNum[i] = newID;
        idFromCoord[row][col] = newID;


    }

    private void updateOpenFromInt(int i, boolean o) {

        int col = (i % this.n) + 1;
        int row = (i - col) / this.n;

        openFromNum[i] = true;
        openFromCoord[row][col] = true;

        // now that we've opened that site, see if any of the 4 adjacent sites are open. for each that is, set the id of all of them equal to the lowest number


    }

    private void setOpenFromInt(int i) {

        int col = (i % this.n) + 1;
        int row = (i - col) / this.n;

        openFromNum[i] = true;
        openFromCoord[row][col] = true;

        // now that we've opened that site, see if any of the 4 adjacent sites are open. for each that is, set the id of all of them equal to the lowest number


    }

    private void setOpenFromCoord(int row, int col) {

        int i = (this.n * (row - 1)) + (col - 1);

        openFromNum[i] = true;
        openFromCoord[row][col] = true;

        //now that we've opened that site, see if any of the 4 adjacent sites are open. for each that is, set the id of all of them equal to the lowest number


    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        updateOpen();

    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return openFromCoord[row][col];
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

    }

    public boolean percolates()              // does the system percolate?
    {
        int x = this.n;

        for (int i = 0; i < x; i++) {
            if (id[x - 1][i] == -1) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        Percolation p = new Percolation(n);

        p.n = n;


    }
}
