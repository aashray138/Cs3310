package wmich.edu.cs3310.hw1.ShresthaA;

/**
 * 
 * A max-oriented priority queue, Q−, of all 
 * elements less than the current median value.
 * Implementation Q-using array-based heaps.
 *
 */
public class MaxQueue {

	/**
	 * Array to store heap.
	 */
	private int[] heapArr;

	/**
	 * Size of heap.
	 */
	private int size;

	/**
	 * Constructor to instantiate MaxQueue and initialize it with the maximum size.
	 * 
	 * @param max_size the maximum size of heap
	 */
	public MaxQueue(int max_size) {
		heapArr = new int[max_size + 1];
		heapArr[0] = Integer.MAX_VALUE; // Use sentinel to the first
		// item.
	}

	/**
	 *  method to return parent index
	 * 
	 * @param idx the index for which the parent index should be returned
	 * @return the parent index of index idx
	 */
	private int getParentIndex(int idx) {
		return idx / 2;
	}

	/**
	 *  method to return index of left successor (child)
	 * 
	 * @param idx the index for which the left successor index should be returned
	 * @return the left successor index of idx
	 */
	private int getLeftSuccessorIndex(int idx) {
		return 2 * idx;
	}

	/**
	 *  method to return index of right successor (child)
	 * 
	 * @param idx the index for which the right successor index should be returned
	 * @return the right successor index of idx
	 */
	private int getRightSuccessorIndex(int idx) {
		return 2 * idx + 1;
	}

	/**
	 *  method that returns true if item under the given idx has no successors
	 * (no left/right child).
	 * 
	 * @param idx the given index for which the successors should be defined
	 * @return true if there is at least 1 successor, otherwise false
	 */
	private boolean hasNoSuccessors(int idx) {
		return idx >= size / 2 && idx <= size;
	}

	/**
	 * Method to swap elements in an array under indexes idx1 and idx2. As a
	 * result, item under idx1 will be under idx2, and item under idx2 will be under
	 * idx1.
	 * 
	 * @param idx1 first index, element under which needs to be swapped
	 * @param idx2 second index, element under which needs to be swapped
	 */
	private void swap(int idx1, int idx2) {
		int tmp = heapArr[idx1];
		heapArr[idx1] = heapArr[idx2];
		heapArr[idx2] = tmp;
	}

	/**
	 *  method to build the heap starting from the element under given idx. To
	 * perform this operation, the element under idx must have at least one
	 * successor (or child) since heap is built starting from this element as the
	 * root.
	 * 
	 * @param idx the given index of element from which the heap is built
	 */
	private void heapify(int idx) {

		if (!hasNoSuccessors(idx)) {

			if (heapArr[idx] < heapArr[getLeftSuccessorIndex(idx)]
					|| heapArr[idx] < heapArr[getRightSuccessorIndex(idx)]) {

				// Check the successor comparing the elements under successors' indexes.
				if (heapArr[getRightSuccessorIndex(idx)] < heapArr[getLeftSuccessorIndex(idx)]) {
					swap(idx, getLeftSuccessorIndex(idx));
					heapify(getLeftSuccessorIndex(idx));
				} else {
					swap(idx, getRightSuccessorIndex(idx));
					heapify(getRightSuccessorIndex(idx));
				}
			}
		}
	}

	/**
	 * Method to add element to the heap.
	 * 
	 * @param item the element to be added
	 */
	public void add(int item) {
		heapArr[++size] = item;

		int initIdx = size;
		while (heapArr[initIdx] > heapArr[getParentIndex(initIdx)]) {
			swap(initIdx, getParentIndex(initIdx));
			initIdx = getParentIndex(initIdx);
		}
	}

	/**
	 * Returns a string represented inorder heap traversal.
	 * 
	 * @return inorder traversal as string
	 */
	public String getInorder() {
		StringBuilder s = new StringBuilder();
		getInorder(s, 1);
		if (s.length() > 2) {
			s.setLength(s.length() - 2);
		}
		return "Inorder traversal of Q- is: " + s;
	}

	/**
	 *  method to store inorder traversal using
	 * recursion. Inorder traversal assumes to visit
	 * left child, then visit node, then visit right
	 * child.
	 * 
	 * @param s the StringBuilder to append output
	 * @param i the current index of array in heap
	 */
	private void getInorder(StringBuilder s, int i) {
		if (i <= 0 || i > size) {
			return;
		}
		getInorder(s, getLeftSuccessorIndex(i));
		s.append(heapArr[i] + ", ");
		getInorder(s, getRightSuccessorIndex(i));
	}

	/**
	 * Removes top element (maximum element) from the heap
	 * because it is maxheap. Maximum element is always 
	 * stored as the first element (avoiding sentinel).
	 * 
	 * @return maximum element of heap
	 */
	public int remove() {
		int res = heapArr[1];
		heapArr[1] = heapArr[size--];
		heapify(1);
		return res;
	}

	/**
	 * Returns maximum element of the heap
	 * 
	 * @return maximum element of the heap
	 */
	public int peek() {
		return heapArr[1];
	}

	/**
	 * Returns true if this heap is empty, otherwise false
	 * 
	 * @return true if empty, otherwise false
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns number of elements in the heap
	 * 
	 * @return size of heap
	 */
	public int size() {
		return size;
	}

}
