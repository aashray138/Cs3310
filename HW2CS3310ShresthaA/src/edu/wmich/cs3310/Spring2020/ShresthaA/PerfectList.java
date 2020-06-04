package edu.wmich.cs3310.Spring2020.ShresthaA;
import java.util.Iterator;

public class PerfectList implements MyList, Iterable {

	/**
	 * Node 
	 * @author aashrayshrestha
	 *
	 * @param <Character> of type Node
	 */
	public class Node<Character> {
		Character character;
		Node<Character> next;
		Node<Character> prev;
		public Node(Character character, Node<Character> next, Node<Character> prev) {
			this.character = character;
			this.next = next;
			this.prev = prev;
		}

		public Character getCharacter() {
			return character;
		}
		public void setCharacter(Character character) {
			this.character = character;
		}
		public Node<Character> getNext() {
			return next;
		}
		public void setNext(Node<Character> next) {
			this.next = next;
		}
		public Node<Character> getPrev() {
			return prev;
		}
		public void setPrev(Node<Character> prev) {
			this.prev = prev;
		}

	}
	Node<Character> first;
	Node<Character> last;
	private int size =0;

	/**
	 * Perfect List Constructor
	 */
	public PerfectList() {
		last = new Node<Character>(null, null, first);
		first = new Node<Character>(null, last, null);
	}

	/**
	 * Getter for the first val
	 */
	public Node<Character> getFirst() {
		return first;
	}

	/**
	 * Getter for the first val
	 */
	public Node<Character> getLast() {
		return last;
	}

	/**
	 * Count the total number
	 */
	@Override
	public int count(char c) {
		// get the count
		int count =0;
		// iterate throguh to get the count
		Iterator iterator = this.iterator();
		while (iterator.hasNext())
			if (iterator.next().equals(c)) count++;
		return count;
	}

	/**
	 * insert to the Nth position
	 */
	@Override
	public void insert(int position, char c) {
		// if valid then only do the insert, else ignore
		if (position>=0 && position < size) {
			Node temp = new Node(c, null, null);
			Node target = first.getNext();
			for (int i=0;i<position;i++)
				target = getNext(target);
			temp.next=target.next;
			temp.prev=target;
			target.next=temp;
		}
	}

	/**
	 * Sorted insert as mentioned
	 */
	@Override
	public void sortedInsert(char c) {
		Node target = first.getNext();
		int pos = 0;
		while ((char)target.getCharacter()>=c || target.next==null) {
			pos++;
			target = getNext(target);
		}
		insert(pos, c);
	}

	/**
	 * Add method
	 */
	@Override
	public void add(Character character) {
		// if valid then use the add method
		Node prev = last;
		prev.setCharacter(character);
		last=new Node(null, null, prev);
		prev.setNext(last);
		size++;
	}

	/**
	 * Append Mehtod for the List
	 */

	@Override
	public void append(MyList myList) {
		// if empty, create a new list
		if (size==0) {
			first = myList.getFirst();
			last = myList.getLast();
			size = myList.size();
		}
		else {
			// add on to the last list 
			last.getPrev().setNext(myList.getFirst().getNext());
			myList.getFirst().getNext().setPrev(last.getPrev());
			last = myList.getLast();
			size = size+myList.size();
		}
	}

	/**
	 * Delete Method
	 */
	@Override
	public void clear() {
		// clear out the references to the first and last node, no referencing available then
		last = new Node<Character>(null, null, first);
		first = new Node<Character>(null, last, null);
	}

	/**
	 * Remove a character node
	 */
	@Override
	public boolean remove(Character character) {
		// remove a character node
		Node current = first;
		while (current.next!=null) {
			if (current.getCharacter().equals(character)) {
				current.getNext().setPrev(current.getPrev());
				return true;
			}
			current=current.getNext();
		}
		System.out.println("element not found");
		return false;
	}

	/**
	 * Object value to an Array
	 */
	@Override
	public Object[] toArray() {
		Character[] characters = new Character[size];
		Iterator iterator = iterator();
		for (int i=0;iterator.hasNext();i++) {
			characters[i]= (Character) iterator.next();
		}
		return characters;
	}

	/**
	 * return size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Check if the list has the given character
	 */
	@Override
	public boolean contains(Character character) {
		// check to see if the list contains the given
		Iterator iterator = iterator();
		while (iterator.hasNext())
			if (iterator.next().equals(character)) return true;
		return false;
	}

	/**
	 * Get the Object that will be later used to check the different palindromic  instances
	 */
	@Override
	public Object get(int index) {
		// return the object in the nth index, but also check to validate
		if (index>=0 && index<size) {
			Node target = first.getNext();
			for (int i=0;i<index;i++) {
				target = getNext(target);
			}
			return target.getCharacter();
		}
		else {
			System.out.println("Illegal index");
			return null;
		}
	}

	/**
	 * get the next of the current Node
	 * @param current
	 * @return
	 */
	private Node getNext(Node current) {
		return  current.getNext();
	}

	/**
	 * Iterator that loops
	 */
	@Override
	public Iterator iterator() {
		return new Iterator() {
			int counter=0;
			@Override
			public boolean hasNext() {
				return counter<size;
			}

			@Override
			public Object next() {
				return get(counter++);
			}
		};
	}


}