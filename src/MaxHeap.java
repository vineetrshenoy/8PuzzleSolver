
public class MaxHeap<T extends Comparable<T>> {
	
	private T [] heap;
	private int N;
	
	public MaxHeap(int capacity){
		heap = (T[]) new Comparable[capacity];
		N = 0;
	}
	
	public int size(){
		return N;
	}
	
	public T removeMax(){
		T item = heap[0];		
		heap[0] = heap[--N];
		heap[N] = null;
		this.siftDown(0);
		
		return item;
	}
	
	public void insert(T item){
		heap[N] = item;
		this.siftUp(N);
		N++;
	}
	
	private void siftUp(int index){
		int parent = (index - 1)/2;
		while (parent >= 0 && heap[parent].compareTo(heap[index]) < 0){
			this.exchange(index, parent);
			index = parent;
			parent = (index-1)/2;
		}
				
	}
	
	private void siftDown(int index){
		while (2*index+1 < N){
			int child = 2 * index + 1;
			if (!(child < N))
				break;
			if (heap[child].compareTo(heap[child + 1]) < 0)
				child++;
			if (heap[index].compareTo(heap[child]) > 0)
				break;
			this.exchange(index, child);
			index = child;
		}
	}

	private void exchange(int indexOne, int indexTwo){
		T temp = heap[indexOne];
		heap[indexOne] = heap[indexTwo];
		heap[indexTwo] = temp;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
