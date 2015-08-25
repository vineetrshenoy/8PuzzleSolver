# 8PuzzleSolver
An 8-puzzle solver using heaps (priority queues) and the A* search algorithm

This project was created as a part of Coursera's Algorithms I course from Princeton University. The full assignment specification can be found here: http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html

An 8-puzzle is a classic game where the tiles 1-8 are place on a 3 x 3 grid with one empty space (the game can get bigger with tiles 1-(N^2-1) placed on a N x N grid). The objective is to get all the numbers in order, from left to right then top to bottom, by sliding the tiles into the empty space. This program simulated an 8 puzzle solver. 

First, the Board class was implemented to represent a board. The API is as follows:

   public class Board {
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension()                 // board dimension N
    public int hamming()                   // number of blocks out of place
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    public boolean isGoal()                // is this board the goal board?
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    public boolean equals(Object y)        // does this board equal y?
    public Iterable<Board> neighbors()     // all neighboring boards
    public String toString()               // string representation of this board (in the output format specified below)

    public static void main(String[] args) // unit tests (not graded)
}
