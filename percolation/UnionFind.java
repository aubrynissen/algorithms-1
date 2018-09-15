/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionFind {

    public int[] id;
    public int[] sz;

    UnionFind(int N) {                     // initialize union-find data structure with
        id = new int[N];            //N objects (0 to N – 1)
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }

    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }


    public void union(int p, int q) { // add connection between p and q

        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }


/*        System.out.println(
                "Joined site " + Integer.toString(p) + " to site " + Integer
                        .toString(q));*/

    }

    public boolean connected(int p, int q) { // are p and q in the same component?

        return root(p) == root(q);

    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        UnionFind uf = new UnionFind(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
