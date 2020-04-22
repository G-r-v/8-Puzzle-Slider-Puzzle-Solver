import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

        private class Move implements Comparable<Move> {

                public Board board;
                public int moves;
                public Move prev;
                public int man;

                public Move(Board b) {
                        this.board = b;
                        moves = 0;
                        prev = null;
                        man = board.manhattan();
                }

                public Move(Board b, Move previous) {
                        board = b;
                        prev = previous;
                        moves = previous.moves + 1;
                        man = board.manhattan();
                }

                public int compareTo(Move that) {
                        return (this.man + this.moves) - (that.man + that.moves);
                }

        }

        private Move lastMove;

        // find a solution to the initial board (using the A* algorithm)
        public Solver(Board initial) {
                if (initial == null)
                        throw new IllegalArgumentException("argument to constructor is null");

                MinPQ<Move> moves = new MinPQ<Move>();
                moves.insert(new Move(initial));

                MinPQ<Move> twinMoves = new MinPQ<Move>();
                twinMoves.insert(new Move(initial.twin()));

                while (true) {
                        lastMove = game(moves);
                        if (lastMove != null || game(twinMoves) != null)
                                break;
                }

        }

        private Move game(MinPQ<Move> moves) {

                Move bestMove = moves.delMin();

                if (bestMove.board.isGoal()) {
                        return bestMove;
                }

                for (Board i : bestMove.board.neighbors()) {
                        if (bestMove.prev == null || !i.equals(bestMove.prev.board))
                                moves.insert(new Move(i, bestMove));
                }
                return null;
        }

        // is the initial board solvable? (see below)
        public boolean isSolvable() {
                return lastMove != null;
        }

        // min number of moves to solve initial board
        public int moves() {
                if (this.isSolvable())
                        return lastMove.moves;
                return -1;
        }

        // sequence of boards in a shortest solution

        public Iterable<Board> solution() {
                if (!this.isSolvable())
                        return null;

                Stack<Board> solution = new Stack<Board>();

                while (lastMove != null) {
                        solution.push(lastMove.board);
                        lastMove = lastMove.prev;
                }

                return solution;
        }

        // test client (see below)
        public static void main(String[] args) {

                // create initial board from file
                In in = new In(args[0]);
                int n = in.readInt();
                int[][] tiles = new int[n][n];
                for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)
                                tiles[i][j] = in.readInt();
                Board initial = new Board(tiles);

                // solve the puzzle
                Solver solver = new Solver(initial);

                // print solution to standard output
                if (!solver.isSolvable())
                        StdOut.println("No solution possible");
                else {
                        StdOut.println("Minimum number of moves = " + solver.moves());
                        for (Board board : solver.solution())
                                StdOut.println(board);
                }
        }

}