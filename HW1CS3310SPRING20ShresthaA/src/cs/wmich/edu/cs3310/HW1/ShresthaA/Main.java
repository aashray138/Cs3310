package cs.wmich.edu.cs3310.HW1.ShresthaA;

import java.io.*;
import java.util.*;

/**
 *
 * @author Aashray
 */
public class Main {

	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//Creates an instance of DoublyLinkedList to store the items 
		DoublyLinkedList<Item> itemLinkedList = new DoublyLinkedList<>();
		
		//#1. Passing the linkedlist as an argument to which the items will be stored
		readFile(itemLinkedList);
		
		// User input to get the number of bags
		System.out.print("Enter number of bags: ");
		Scanner bucky = new Scanner(System.in);
		int n = bucky.nextInt();
	
		// Printing the number of bags as said in the assignment
		System.out.println(" n= " + n);

		//#2. The best way to the fill the bags I think would be having an Array
		// Array of Item type
		ArrayList<Item> bags = new ArrayList<>();
		fillTheBags(itemLinkedList, n, bags);

		// If the user input is below 10 :
		// Show the first five contents of the bag
		// Using a loop
		// Do not need to print if the number is greater than 10, so no else clause
		if (n <= 10) {
			System.out.println("Bags before sorting:");
			for (int j = 0; j < n; j++) {
				System.out.println("Bag "+(j+1));
				for (int i = 0; i < 5; i++) {
					System.out.println(bags.get(j*25+i).rarity+" "
							+bags.get(j*25+i).name+", "
							+bags.get(j*25+i).currentStrength);
				}
				System.out.println("........................");
			}
		}

		// Setting boolean found to false
		Boolean found = false;
		//#3. get single search time before sorting
		// Selecting a random element from the list of items
		Item randomElement = getRandomElement(itemLinkedList);
		System.out.println("Searching for "+randomElement.rarity+" "+randomElement.name+"...");
		// set the index of search to 1 
		int indexOfSearch = 1;
		long now = System.nanoTime();
		// Loop through the bags to see if any one contains the item we are looking for
		for (Item bag : bags) {
			if (randomElement.name.equals(bag.name) && randomElement.rarity.equals(bag.rarity)) {
				found= true;
				// If found : print the bag#, slot#, and the curent Strength
				// Break, if found
				System.out.println("Found in bag " + (indexOfSearch / 25) + ", slot " + (indexOfSearch % 25) + " , "
						+ "Strength: " + bag.currentStrength + ".");
				break;
			}
			// Increse the index of search 
			indexOfSearch++;
		}
		// If not found, Print the item was not found
		if(!found){
			System.out.println("Not found.");
		}
		
		//#4. System. nanoTime() method returns the current value of the most precise available system timer, in nanoseconds. 
		
		System.out.println("Linear Search - Single search time : " + (System.nanoTime() - now)+" nanoseconds.");

		// get average search time before sorting
		// Using the getRandomElement and System nano time to calculate the time
		randomElement = getRandomElement(itemLinkedList);
		now = System.nanoTime();
		for(int x = 0 ; x < 100;x++){
			indexOfSearch = 0;
			for (Item bag : bags) {
				if (randomElement.name.equals(bag.name) && randomElement.rarity.equals(bag.rarity)) {
					break;
				}
				indexOfSearch++;
			}
		}
		// The average search time of the items search 
		System.out.println("Average search time: "+((System.nanoTime()-now)/100)+" nanoseconds.");

		//5. Sort the bags 
		Collections.sort(bags);

		// The bags after the sort
		// This will also only print if the num of bags is less than 10
		if (n <= 10) {
			System.out.println("Bags after sort");
			for (int j = 0; j < n; j++) {
				System.out.println("Bag "+ (j+1) );
				for (int k = 0; k < 5; k++) {
					System.out.println(bags.get(j*25+k).rarity+" "+bags.get(j*25+k).name+", "+bags.get(j*25+k).currentStrength);
				}
				System.out.println("........................");
			}
		}

		//#6 .Search for a Random Element and Binary Search the item
		randomElement = getRandomElement(itemLinkedList);
		System.out.println("Searching for "+randomElement.rarity+" "
				+randomElement.name+"...");
		now = System.nanoTime();
		indexOfSearch = binarySearch(bags,randomElement);
		if(indexOfSearch !=-1){
			System.out.println("Found in bag " + (indexOfSearch / 25) + ", slot " + (indexOfSearch % 25) + " . "
					+ "Strength: " + bags.get(indexOfSearch).currentStrength + ".");
		}
		else{
			System.out.println("Not found.");
		}
		System.out.println("Binary Search - Single search time: " + (System.nanoTime() - now)+" nanoseconds.");
	
		// get average search time of binary search
		// Using the getRandomElement and System nano time to calculate the time
		randomElement = getRandomElement(itemLinkedList);
		now = System.nanoTime();
		for(int k =0 ;k<100;k++){
			binarySearch(bags,randomElement);
		}
		System.out.println("Binary Search - Average search time: "+((System.nanoTime()-now)/100)+" nanoseconds.");
	}

	/**
	 * Get random element of an item of the linked list.
	 * @param itemLinkedList list to get random element from.
	 * @return a random item.
	 */
	private static Item getRandomElement(DoublyLinkedList<Item> itemLinkedList) {
		int randomElementIndex = Math.abs(new Random().nextInt()) % itemLinkedList.size();
		Item randomElement = null;
		// getting random element
		Iterator<Item> it = itemLinkedList.iterator();
		for (int i = 0; i < randomElementIndex; i++) {
			randomElement = it.next();
		}
		return randomElement;
	}

	/**
	 * Binary search the list to get a key.
	 * @param bags list to search in.
	 * @param key key to search for.
	 * @return index of the element. or -1 if not found.
	 */
	private static int binarySearch(ArrayList<Item> bags, Item key) {
		int start =0;
		int end = bags.size();
		int mid;
		while(start<=end){
			mid = start+((end-start)/2);
			if(bags.get(mid).equals(key)){
				return mid;
			}
			else if (bags.get(mid).compareTo(key) < 0){
				start=mid+1;
			}
			else{
				end=mid-1;
			}
		}
		return -1;
	}

	/**
	 * Read file into the list.
	 * @param list to read the file into.
	 * @throws FileNotFoundException if file is not found.
	 * @throws IOException if there is a problem reading from file.
	 */
	public static void readFile(DoublyLinkedList<Item> list) throws FileNotFoundException, IOException {
		File file = new File("input.txt");
		BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
		String line;
		bufferedreader.readLine();
		xwhile ((line = bufferedreader.readLine()) != null) {
			String[] arr = line.split(",");
			list.add(new Item(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), arr[3]));
		}
	}

	/**
	 * Fill the bags from the item list.
	 * @param itemLinkedList the list of the all the items.
	 * @param n size of the array to be filled.
	 * @param bags array to be filled.
	 */
	private static void fillTheBags(DoublyLinkedList<Item> itemLinkedList, int n, ArrayList<Item> bags) {
		Iterator<Item> it = itemLinkedList.iterator();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 25; j++) {
				if (!it.hasNext()) {
					it = itemLinkedList.iterator();
				}
				bags.add(it.next());
			}
		}
	}

}
