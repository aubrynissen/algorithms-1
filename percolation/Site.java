/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Site {

    int id;
    boolean open;
    int column;
    int row;


    public void setSite(int i, int n) {

        int col;
        int r;

        col = (i % n) + 1;
        this.column = col;
        this.open = false;
        r = ((i - (col - 1)) / n) + 1;
        this.row = r;
        System.out.println(
                Integer.toString(i) + " | col: " + Integer.toString(col) + " | row: " + Integer
                        .toString(r));

    }

    public int getSiteIDfromCoord() {
        return id;
    }

    public static void main(String[] args) {

        Site s = new Site();
        s.setSite();
        return s.getSite();

    }
}
