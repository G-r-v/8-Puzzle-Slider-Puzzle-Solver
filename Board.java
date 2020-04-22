import java.util.LinkedList;

public class Board {

        private int[][] tiles, goal;
        private final int n;

        // create a board from an n-by-n array of tiles,
        // where tiles[row][col] = tile at (row, col)
        public Board(int[][] tiles) {
                n = tiles.length;
                goal = new int[n][n];
                this.tiles = new int[n][n];

                for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)
                                this.tiles[i][j] = tiles[i][j];

                for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)
                                goal[i][j] = i * n + j + 1;
                goal[n - 1][n - 1] = 0;

        }

        // string representation of this board
        public String toString() {
                StringBuilder s = new StringBuilder();
                s.append(n);

                for (int i = 0; i < n; i++) {
                        s.append("\n");
                        for (int j = 0; j < n; j++) {

                                if (tiles[i][j] < 10)
                                        s.append(" ");
                                s.append(tiles[i][j] + " ");
                        }
                }

                return s.toString();
        }

        // board dimension n
        public int dimension() {
                return n;
        }

        // number of tiles out of place
        public int hamming() {
                int ham = 0;
                for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)

                                if (tiles[i][j] != 0)
                                        if (tiles[i][j] != goal[i][j])
                                                ham++;
                return ham;
        }

        // sum of Manhattan distances between tiles and goal
        private int manhattan(int i, int j) {
                int tile = tiles[i][j];
                int x = (tile - 1) / n;
                int y = (tile - 1) % n;

                return Math.abs(x - i) + Math.abs(y - j);
        }

        public int manhattan() {
                int man = 0;
                for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)

                                if (tiles[i][j] != 0)
                                        man += manhattan(i, j);
                return man;
        }

        // is this board the goal board?
        public boolean isGoal() {
                return this.equals(new Board(goal));
        }

        // does this board equal y?
        public boolean equals(Object y) {
                if (y == this)
                        return true;
                if (y == null)
                        return false;
                if (y.getClass() != this.getClass())
                        return false;

                Board that = (Board) y;
                if (this.n != that.n)
                        return false;
                if (!this.toString().equals(that.toString()))
                        return false;
                return true;
        }

        // all neighboring boards
        /*
         * private class Neighbors implements Iterable<Board> { public Iterator<Board>
         * iterator() { return new ListNeighbors(); }
         * 
         * private Board b; private final int n; LinkedList<Board> padosi = new
         * LinkedList<Board>();
         * 
         * public Neighbors(int[][] t) { b = new Board(t); n = t.length;
         * 
         * int x = 0, y = 0; // finding empyty tile for (int i = 0; i < n; i++) for (int
         * j = 0; j < n; j++) if (b.tiles[i][j] == 0) { x = i; y = j; i = n - 1; j = n -
         * 1; break; }
         * 
         * if (x - 1 >= 0 && x - 1 < n) padosi.add(b.swap(x, y, x - 1, y)); if (x + 1 >=
         * 0 && x + 1 < n) padosi.add(b.swap(x, y, x + 1, y)); if (y - 1 >= 0 && y - 1 <
         * n) padosi.add(b.swap(x, y, x, y - 1)); if (y + 1 >= 0 && y + 1 < n)
         * padosi.add(b.swap(x, y, x, y + 1));
         * 
         * }
         * 
         * private class ListNeighbors implements Iterator<Board> {
         * 
         * Iterator<Board> i = padosi.iterator();
         * 
         * public boolean hasNext() { return i.hasNext(); }
         * 
         * public Board next() { return i.next(); }
         * 
         * }
         * 
         * }
         */

        public Iterable<Board> neighbors() {

                LinkedList<Board> padosi = new LinkedList<Board>();

                int x = 0, y = 0; // finding empty tile
                for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)
                                if (tiles[i][j] == 0) {
                                        x = i;
                                        y = j;
                                        i = n - 1;
                                        j = n - 1;
                                        break;
                                }

                if (x - 1 >= 0 && x - 1 < n)
                        padosi.add(swap(x, y, x - 1, y));
                if (x + 1 >= 0 && x + 1 < n)
                        padosi.add(swap(x, y, x + 1, y));
                if (y - 1 >= 0 && y - 1 < n)
                        padosi.add(swap(x, y, x, y - 1));
                if (y + 1 >= 0 && y + 1 < n)
                        padosi.add(swap(x, y, x, y + 1));

                return padosi;

        }

        private Board swap(int i, int j, int x, int y) {
                Board swap = new Board(tiles);
                int temp = swap.tiles[i][j];
                swap.tiles[i][j] = swap.tiles[x][y];
                swap.tiles[x][y] = temp;

                return swap;
        }

        // a board that is obtained by exchanging any pair of tiles
        public Board twin() {

                if (tiles[0][0] != 0) {
                        if (tiles[0][1] != 0)
                                return this.swap(0, 0, 0, 1);
                        else
                                return this.swap(0, 0, 1, 0);
                } else
                        return this.swap(1, 1, 1, 0);

        }

        // unit testing (not graded)
        public static void main(String[] args) {

        }

}
