import java.util.Arrays;
public class Board {
	private int[] array;
	private int N;
	
	private Board(int [] other) {
		array = other;
		N = (int) Math.sqrt(array.length);
	}
	
	public Board(int[][]blocks) {
		int length = blocks.length * blocks.length;
		N = blocks.length;
		array = new int[length];
		int count = 0;
		
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				array[count] = blocks[i][j];
				count++;
				
			}				
		}			
	}
	
	public int dimension() {
		return N;
	}
	
	public int hamming() {
		int hamming = 0;
		for (int i = 0; i < array.length; i++) {
			if (i != (array[i] - 1))
				hamming++;
		}
		return hamming - 1;
	}
	
	public int manhattan() {
		int manhattan = 0;
		
		for (int i = 0; i < N * N; i++) {
			if (array[i] == 0) 
				continue;
			int vertical = Math.abs((i / N) - ((array[i] -1) / N));
			int horizontal = Math.abs((i % N) - ((array[i] -1) % N));
			manhattan += vertical + horizontal;
		}
		
		/**
		for (int i = 0; i < array.length; i++){
			if (array[i] != 0){
				int value = Math.abs((array[i]-1-i)) ;
				if (value != 1 && value != 2)
					value = value / 2;
				manhattan += value;
			}			
		}
		*/
		return manhattan;
	}
	
	public boolean isGoal() {
		int length = array.length;
		for (int i = 0; i < length - 1; i++){
			if (!(i == array[i]-1))
				return false;
		}
		return true;
	}
	
	public Board twin() {
		int [] other = new int[N * N];
		for (int i = 0; i < N * N; i++)
			other[i] = array [i];
		
		
		
		for (int i = 0; i < (N * N) -1; i++) {
			if ((other[i] == 0) || (other[i+1] == 0))
				continue;
			if ((i % N == (N-1)) && ((i+1) % N == 0))
				continue;
			Board.exchange(other, i, i+1);
			break;			
		}
		/**
		int count = 0;
		int [] [] board = new int [N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++){
				board[i] [j] = other[count];
				count++;				
			}
		**/	
		return new Board(other);		
	}
	
	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null)
			return false;
		
		if (y.getClass() != this.getClass())
			return false;
		
		Board board = (Board) y;
	
		
		
		
		if (this.N != board.N)
			return false;
		if (board.dimension() != this.dimension())
			return false;
		int length = this.array.length;
		for (int i = 0; i < length; i++)
			if (this.array[i] != board.array[i])
				return false;
		return true;
	}
	
	public Iterable<Board> neighbors() {
		Stack<Board> stack = new Stack<Board>();
		int index = 0;
		for (int i = 0; i < N * N; i++) {
			if (array[i] == 0){
				index = i;
				break;
			}
		}
	
		//find board above
		if (index - N >= 0) {
			int [] above = Arrays.copyOf(array, array.length);
			Board.exchange(above, index, index - N);
			stack.push(new Board(above));
		}
		//find board below
		if (index + N < (N * N)) {
			int [] below = Arrays.copyOf(array, array.length);
			Board.exchange(below, index, index + N);
			stack.push(new Board(below));
		}
		// find board left
		if (index % N != 0) {
			int [] left = Arrays.copyOf(array, array.length);
			Board.exchange(left, index, index - 1);
			stack.push(new Board(left));
		}
		// find board right
		if (index % N != (N-1)) {
			int [] right = Arrays.copyOf(array, array.length);
			Board.exchange(right, index, index + 1);
			stack.push(new Board(right));
		}
		
		
		return stack;
	}
	private static void exchange(int[] data, int indexOne, int indexTwo) {
		int temp = data[indexOne];
		data[indexOne] = data[indexTwo];
		data[indexTwo] = temp;
	}
	public String toString() {
		int count = 0;
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
		            s.append(String.format("%2d ", array[count]));
		            count++;
		        }
		        s.append("\n");
		    }
		    return s.toString();
		
		/**StdOut.println(N);
		int Nsquared = N * N;
		int count = 0;
		for (int i = 0; i < N * N; i++){
			if (count == 3){
				count = 0;
				StdOut.println();
			}
			StdOut.print(array[i] + " ");
			count++;
		}
		return "";
		**/
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {8, 1, 3, 4, 0, 2, 7, 6, 5};
		int [] goal = {8, 1, 3, 4, 0, 2, 7, 6, 5};
		Board boardOne = new Board(array);
		Board boardTwo = new Board(goal);
		System.out.println(boardOne);
		System.out.println();
		System.out.println();
		Stack<Board> stack = (Stack<Board>) boardOne.neighbors();
		// Checks if the boards are equal
		//System.out.println(boardOne.equals(boardTwo));
		
		//Finding a twin
		//System.out.println(boardOne.twin());
		
		//System.out.println(boardOne.equals(null));
		//Check Hamming and Manhattan
		//System.out.println(boardOne.hamming());
		//System.out.println(boardTwo.manhattan());
		/** Finds neigboring boards
		
		System.out.println(stack.pop());
		System.out.println();
		System.out.println();
		System.out.println(stack.pop());
		System.out.println();
		System.out.println();
		System.out.println(stack.pop());
		System.out.println();
		System.out.println();	
		System.out.println(stack.pop());
		System.out.println();
		System.out.println();
		*/
		
				

	}

}
