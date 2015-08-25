import java.util.Iterator;
public class Solver {
	private MinPQ<Node> pq;
	private MinPQ<Node> twin;
	private boolean solvable;
	private Node goalNode;
	
	public Solver (Board initial){
		if (initial == null)
			throw new NullPointerException();
		pq = new MinPQ<Node>();
		twin = new MinPQ<Node>();
		
		Board twinBoard = initial.twin();
		
		pq.insert(new Node(initial,initial.manhattan(),0,null));
		twin.insert(new Node(twinBoard,twinBoard.manhattan(),0,null));
		
		Node node = pq.delMin();
		Node twinNode = twin.delMin();
		
		while(!node.board.isGoal() && !twinNode.board.isGoal()) {
			Iterator<Board> iterator = node.board.neighbors().iterator();
			Iterator<Board> twinIterator =  twinNode.board.neighbors().iterator();
			
			
			
			Board previous;
			Board twinPrevious;
			try{
				previous = node.previous.board;
				twinPrevious = twinNode.previous.board;
			}
			catch (NullPointerException e) {
				while (iterator.hasNext()) {
					Board pop = iterator.next();
					pq.insert(new Node(pop,pop.manhattan(),node.moves + 1,node));
				}
				while(twinIterator.hasNext()) {
					Board twinPop = twinIterator.next();
					twin.insert(new Node(twinPop, twinPop.manhattan(),twinNode.moves+1,twinNode));
				}
				
				node = pq.delMin();
				twinNode = twin.delMin();
				continue;
			}
			
			while (iterator.hasNext()) {
				Board temp = iterator.next();
				if (!temp.equals(previous))
					pq.insert(new Node(temp,temp.manhattan(), node.moves + 1, node));
			}
			while (twinIterator.hasNext()) {
				Board temp = twinIterator.next();
				if (!temp.equals(twinPrevious))
					twin.insert(new Node(temp,temp.manhattan(), twinNode.moves + 1, twinNode));
				
			}
			
			node = pq.delMin();
			twinNode = twin.delMin();
		}
		if (!node.board.isGoal() && twinNode.board.isGoal())
			solvable = false;
		else{
			solvable = true;
			goalNode = node;
		}
		
		
	}
	
	public int moves() {
		if (solvable == false)
			return -1;
		return goalNode.moves;
	}
	
	public boolean isSolvable() {
		return solvable;
	}
	
	public Iterable<Board> solution() {
		if (solvable == false)
			return null;
		Stack<Board> stack = new Stack<Board>();
		Node temp = goalNode;
		while (temp != null){
			stack.push(temp.board);
			temp = temp.previous;
		}
		return stack;
	}
	
	private class Node implements Comparable<Node> {
		private Board board;
		private int moves; 
		private int manhattan;
		private Node previous;
		
		public Node(Board newBoard, int priority, int newMoves, Node newPrevious) {
			this.board = newBoard;
			this.moves = newMoves;
			this.manhattan = priority;
			this.previous = newPrevious;
		}
		
		public int compareTo(Node other) {
			// TODO Auto-generated method stub
			int thisPriority = manhattan + this.moves;
			int otherPriority = other.manhattan + other.moves;
			
			if (thisPriority > otherPriority)
				return 1;	
			else if (thisPriority < otherPriority)
				return -1;
			return 0;
		}
		
		public String toString() {
			return board.toString();
		}
		
	
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 // create initial board from file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

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
