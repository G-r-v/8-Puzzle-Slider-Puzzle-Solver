# 8-Puzzle-Slider-Puzzle-Solver
 Program to solve the 8-puzzle problem (and its natural higher generalizations) using the A* search algorithm.


The problem: The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square. The goal is to rearrange the tiles so that they are in row-major order, using as few moves as possible. You are permitted to slide tiles either horizontally or vertically into the blank square. The following diagram shows a sequence of moves from an initial board  to the goal board .

0  1  3          1  0  3          1  2  3                                                                                             
4  2  6   ==>    4  2  6   ==>    4  0  6                                                                                         
7  5  8          7  5  8          7  5  8                                                                                              

      1  2  3           1  2  3                                            
==>   4  5  6    ==>    4  5  6                                                                   
      7  0  8           7  8  0                                                                 


API:

Board data type. a data type that models an n-by-n board with sliding tiles.

           public class Board {

               // create a board from an n-by-n array of tiles,
               // where tiles[row][col] = tile at (row, col)
               public Board(int[][] tiles)

               // string representation of this board
               public String toString()

               // board dimension n
               public int dimension()

               // number of tiles out of place
               public int hamming()

               // sum of Manhattan distances between tiles and goal
               public int manhattan()

               // is this board the goal board?
               public boolean isGoal()

               // does this board equal y?
               public boolean equals(Object y)

               // all neighboring boards
               public Iterable<Board> neighbors()

               // a board that is obtained by exchanging any pair of tiles
               public Board twin()

               // unit testing (not graded)
               public static void main(String[] args)

           }
           
Solver data type. implement A* search to solve n-by-n slider puzzles.

            public class Solver {

                // find a solution to the initial board (using the A* algorithm)
                public Solver(Board initial)

                // is the initial board solvable? (see below)
                public boolean isSolvable()

                // min number of moves to solve initial board
                public int moves()

                // sequence of boards in a shortest solution
                public Iterable<Board> solution()

                // test client (see below) 
                public static void main(String[] args)

            }
