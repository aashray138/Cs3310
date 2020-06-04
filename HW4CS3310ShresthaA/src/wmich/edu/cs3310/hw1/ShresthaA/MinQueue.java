package wmich.edu.cs3310.hw1.ShresthaA;

/**
 * A min-oriented priority queue, Q+, of all elements greater than or equal to
 * the current median value. Implementation Q+ using size-balanced Linked-List
 * based heaps
 *
 */
public class MinQueue {

	/**
	 * Stores first node (or top node with minimum value).
	 */
	private Node first;

	/**
	 * Adds value to the heap/queue. If first node is null, create first (root)
	 * node. Otherwise add new node recursively starting from the first using 
	 * method.
	 * 
	 * @param v the value to be added
	 */
	public void add(int v) {
		if (first == null) {
			first = new Node(v, 0, null, null, null);
		} else {
			add(add(first, v));
		}
	}

	/**
	 *  method to iterate over the nodes until parent of the current node is
	 not null, and update every node.
	 * 
	 * @param node the node to be added
	 */
	private void add(Node node) {
		while (node.parentNode != null) {
			if (node.v < node.parentNode.v) {
				int tmpVal = node.v; // Store current node value to
				// add it to the parent value.

				node.v = node.parentNode.v;
				node.parentNode.v = tmpVal;
				node = node.parentNode;
			} else {
				break;
			}
		}
	}

	/**
	 *  method to add new node recursively.
	 * 
	 * @param node the current node
	 * @param v    the current value
	 * @return the node to be added
	 */
	private Node add(Node node, int v) {
		if (node.rightSuccessor == null) {
			return addRight(node, v);
		} else if (node.leftSuccessor == null) {
			return addLeft(node, v);
		}

		node.numSuccessors++;

		if (node.leftSuccessor.numSuccessors <= node.rightSuccessor.numSuccessors) {
			return add(node.leftSuccessor, v);
		} else {
			return add(node.rightSuccessor, v);
		}
	}

	/**
	 * Creates left node and add it to the given node.
	 * 
	 * @param node the given node
	 * @param v    the value of node
	 * @return the created left node
	 */
	private Node addLeft(Node node, int v) {
		node.leftSuccessor = new Node(v, 0, node, null, null);
		node.numSuccessors++;
		return node.leftSuccessor;
	}

	/**
	 * Creates right node and add it to the given node.
	 * 
	 * @param node the given node
	 * @param v    the value of node
	 * @return the created left node
	 */
	private Node addRight(Node node, int v) {
		node.rightSuccessor = new Node(v, 0, node, null, null);
		node.numSuccessors++;
		return node.rightSuccessor;
	}

	/**
	 * Returns size of the queue.
	 * 
	 * @return 0 if first node is null, otherwise number of nodes
	 */
	public int size() {
		return first == null ? 0 : getNumSuccessors(first) + 1;
	}

	/**
	 * Returns true if queue is empty, otherwise false.
	 * 
	 * @return true if queue is empty, otherwise false
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns first value of the queue.
	 * 
	 * @return first value of the queue.
	 */
	public int peek() {
		return first.v;
	}

	/**
	 *  method to return number of successors of the given node (or number of
	 * children).
	 * 
	 * @param node the given node
	 * @return the number of successors
	 */
	private int getNumSuccessors(Node node) {
		return node.numSuccessors;
	}

	/**
	 *  method to process given last node. Since the first node needs to be
	 * deleted, we need to provide tree rotations.
	 * 
	 * @param lastNode the node to be processed
	 */
	private void processLastNode(Node lastNode) {
		first.v = lastNode.v;
		if (lastNode.parentNode.leftSuccessor != null && lastNode.parentNode.rightSuccessor != null
				&& lastNode.parentNode.leftSuccessor.v == lastNode.parentNode.rightSuccessor.v) {
			lastNode = lastNode.parentNode.leftSuccessor;
		}
		if (lastNode.parentNode.leftSuccessor != null && lastNode.parentNode.leftSuccessor.v == lastNode.v) {
			lastNode.parentNode.leftSuccessor = null;
			lastNode.parentNode = null;
		} else {
			lastNode.parentNode.rightSuccessor = null;
			lastNode.parentNode = null;
		}
	}

	/**
	 *  method to perform left rotation.
	 * 
	 * @param node the node whose subtree needs to be rotated
	 */
	private void rotateLeft(Node node) {
		int tmp = node.v;
		node.v = node.leftSuccessor.v;
		node.leftSuccessor.v = tmp;
	}

	/**
	 *  method to perform right rotation.
	 * 
	 * @param node node the node whose subtree needs to be rotated
	 */
	private void rotateRight(Node node) {
		int tmp = node.v;
		node.v = node.rightSuccessor.v;
		node.rightSuccessor.v = tmp;
	}

	/**
	 *  method to process first node. This operation requires actions to
	 * rotate subtress to build the minheap.
	 * 
	 * @param firstNode the node to be processed
	 */
	private void processFirstNode(Node firstNode) {

		while (true) {

			if (getNumSuccessors(firstNode) == 0) { // If no successors.

				break; // It doesn't make sense to provide rotations.

			} else if (getNumSuccessors(firstNode) == 1) { // If only 1 successor.

				if (firstNode.leftSuccessor != null && firstNode.leftSuccessor.v < firstNode.v) {

					rotateLeft(firstNode);

				} else if (firstNode.rightSuccessor != null && firstNode.rightSuccessor.v < firstNode.v) {

					rotateRight(firstNode);
				}
				break; // Only 1 successor, so it doesn't need to continue.

			} else if (getNumSuccessors(firstNode) > 1) { // If more than 1 successors.

				if (firstNode.leftSuccessor.v < firstNode.rightSuccessor.v) {

					if (firstNode.v > firstNode.leftSuccessor.v) {

						rotateLeft(firstNode);
						firstNode = firstNode.leftSuccessor;

					} else {
						break; // Out of loop, this is min heap.
					}
				} else {

					if (firstNode.v > firstNode.rightSuccessor.v) {

						rotateRight(firstNode);
						firstNode = firstNode.rightSuccessor;

					} else {
						break;
					}
				}
			}
		}
	}

	/**
	 * Removes first value of the queue. After remove operation the heap should be
	 * rebalanced. Rebalancing operation includes processing the last node, as well
	 * as the first node.
	 * 
	 * @return the removed value
	 */
	public int remove() {
		int res = peek(); // Store returned value before rebalancing.

		if (!isEmpty()) { // It makes sense only if queue is not empty.

			if (getNumSuccessors(first) == 0) { // If only one node is present.
				first = null; // Remove one node (obvious, it's first node).
				return res; // Return result immediately.
			}

			Node lastNode = getLastNode(first); // First, find last node.

			processLastNode(lastNode); // Process last node.

			Node traverseNode = first; // Second, find first node.

			processFirstNode(traverseNode); // Process first node.
		}
		return res; // Return result.
	}

	/**
	 *  method to get the last node in the heap starting from the given node.
	 * 
	 * @param node the given node
	 * @return the last node
	 */
	private Node getLastNode(Node node) {
		if (node.leftSuccessor == null && node.rightSuccessor == null) { // Case when given node is the last one.
			return node;
		} else if (node.rightSuccessor == null) { // If only left node.
			node.numSuccessors--;
			return node.leftSuccessor; // Return left node.
		} else if (node.leftSuccessor == null) { // If only right node.
			node.numSuccessors--;
			return node.rightSuccessor; // Return right node.
		} else { // Otherwise.
			node.numSuccessors--;
			if (node.leftSuccessor.numSuccessors > node.rightSuccessor.numSuccessors) {
				return getLastNode(node.leftSuccessor); // Return call this method recursively
				// using left child.

			} else {
				return getLastNode(node.rightSuccessor); // Return call this method recursively
				// using right child.
			}
		}
	}

	/**
	 * Method to return string representation of inorder traversal.
	 * 
	 * @return string representation of inorder traversal
	 */
	public String getInorder() {
		StringBuilder s = new StringBuilder();
		getInorder(first, s);
		if (s.length() > 2) {
			s.setLength(s.length() - 2);
		}
		return ("Inorder traversal of Q+ is: " + s);
	}

	/**
	 *  method to populate string builder with inorder traversal.
	 * 
	 * @param s    the output
	 * @param node the current visited node
	 */
	private void getInorder(Node node, StringBuilder s) {
		if (node == null) { // Check base case to avoid stackoverflow error
			return;
		}
		getInorder(node.leftSuccessor, s); // Left child traversal.
		s.append(node.v + ", "); // Visit node.
		getInorder(node.rightSuccessor, s); // Right child traversal.
	}

	/**
	 * 
	 * Static class that represents single node in the heap.
	 *
	 */
	static class Node {

		/**
		 * Stores value.
		 */
		int v;
		
		/**
		 * Stores number of successors.
		 */
		int numSuccessors;
		
		/**
		 * Stores parent node.
		 */
		Node parentNode;
		
		/**
		 * Stores left child.
		 */
		Node leftSuccessor;
		
		/**
		 * Stores right child.
		 */
		Node rightSuccessor;

		/**
		 * Constructor to instantiate Node and initialize
		 * it with the parameters.
		 * 
		 * @param v the value
		 * @param numSuccessors the number of successors
		 * @param parentNode the parent node
		 * @param leftSuccessor the left successor
		 * @param rightSuccessor the right successor
		 */
		public Node(int v, int numSuccessors, Node parentNode, Node leftSuccessor, Node rightSuccessor) {
			this.v = v;
			this.numSuccessors = numSuccessors;
			this.parentNode = parentNode;
			this.leftSuccessor = leftSuccessor;
			this.rightSuccessor = rightSuccessor;
		}
	}

}
