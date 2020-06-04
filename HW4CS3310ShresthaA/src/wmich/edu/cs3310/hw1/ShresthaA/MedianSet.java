package wmich.edu.cs3310.hw1.ShresthaA;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * This class represents a set constructed from
 * two different queues: min queue based on the 
 * heap (linked list implementation), and max queue
 * based on the heap (array implementation).
 * Also this class contains a main method to run the
 * programs. Inside main method, only first console
 * program is uncommented so it can be run. Other
 * two programs (question H and Extra credit) are commented.
 *
 */
public class MedianSet {

	/**
	 * Declare and instantiate min queue.
	 */
	MinQueue qmin = new MinQueue();
	
	/**
	 * Declare and instantiate max queue.
	 * 10000 to make sure it will be enough
	 * space to store large input file.
	 */
	MaxQueue qmax = new MaxQueue(10000);

	public static void main(String[] args) throws IOException {
		
		// Console program.
		runConsoleProgram();
	}
	
	/**
	 * Runs console program.
	 * @throws IOException 
	 */
	public static void runConsoleProgram() throws IOException {
		MedianSet ms = new MedianSet();
		
		Scanner bucky = new Scanner (System.in);
		int i = 0;
		do {
			System.out.println("Choose one of the following :\n1: Run Manual Program\n2:Extra Credit Program For Average Time\n" 
					+ "3: Find Average Time for Different Operations\n");
			 i = bucky.nextInt();
		} while (i < 1 || i > 3);
		
			if (i ==2) {
				runExtraCreditProgram();
			} else if ( i == 3) {
				runQuestionHProgram();
			} else if ( i == 1) {
		
		 
		try (Scanner sc = new Scanner(System.in)) {
			outer: while (true) {
				System.out.println("R - read from file?\nA - add?\nC - check median?\nD - delete median?\nE - exit?\n");
				switch (sc.nextLine().trim()) {
				case "A":
					System.out.println("ok, specify the value v to be inserted into S");
					int v = Integer.parseInt(sc.nextLine().trim());
					String q = ms.add(v);
					System.out.println("done, value " + v + " is inserted in " + q + ",");
					System.out.println(ms.qmax.getInorder());
					System.out.println(ms.qmin.getInorder());
					break;
				case "C":
					int median = ms.getMedian();
					System.out.println("the median is " + (median == -1 ? "no median" : median));
					System.out.println(ms.qmax.getInorder());
					System.out.println(ms.qmin.getInorder());
					break;
				case "D":
					q = ms.deleteMedian();
					System.out.println(q);
					System.out.println(ms.qmax.getInorder());
					System.out.println(ms.qmin.getInorder());
					break;
				case "E":
					System.out.println("exit program.");
					break outer;
				case "R":
					readFromFile(sc, ms);
					break;
					default: 
						System.out.println("Invalid option");
						break outer;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid input");
		}
		
			}
	}
	
	/**
	 * Method allows the user to read from the input file and get output.
	 * 
	 * @param sc the Scanner object
	 * @param ms the reference to MedianSet instance
	 * @throws IOException when input file is invalid or not found
	 */
	public static void readFromFile(Scanner sc, MedianSet ms) throws IOException {
		System.out.print("Enter file name: ");
		
		String input = new String(Files.readAllBytes(Paths.get(sc.nextLine().trim())));
		String items[] = input.split(",");
		for (String item : items) {
			if (item.equals("g")) {		
				long t1 = System.nanoTime();
				int median = ms.getMedian();
				System.out.println("the median is " + (median == -1 ? "no median" : median));
				System.out.println("The time taken to get the median is : " + (System.nanoTime() -t1) + "nano seconds");
				
				 System.out.println(ms.qmax.getInorder());
				 System.out.println(ms.qmin.getInorder());
			
			} else if (item.equals("d")) {
				
				long t1 = System.nanoTime();
				String q = ms.deleteMedian();
				System.out.println(q);
				System.out.println("The time taken to delete the median is : " + (System.nanoTime()-t1) + "nano seconds");
				System.out.println(ms.qmax.getInorder());
				System.out.println(ms.qmin.getInorder());
			
			} else if (item.startsWith("a")) {
				
				long t1 = System.nanoTime();
				int v = Integer.parseInt(item.split(" ")[1]);
				String q = ms.add(v);
				System.out.println("The time taken to add the value is : " + (System.nanoTime() -t1) + "nano seconds");
				System.out.println("done, value " + v + " is inserted in " + q + ",");
				System.out.println(ms.qmax.getInorder());
				System.out.println(ms.qmin.getInorder());			
			}
		}
		
		
	}
	
	/**
	 * Runs question H program (measure times for 3 methods).
	 * 
	 * @throws IOException if input file is invalid or not found
	 */
	public static void runQuestionHProgram() throws IOException {
		MedianSet ms = new MedianSet();
		
		ArrayList<Long> addTime = new ArrayList<>();
		ArrayList<Long> deleteMedTime = new ArrayList<>();
		ArrayList<Long> getMedTime = new ArrayList<>();
		String input = new String(Files.readAllBytes(Paths.get("hw4input.txt")));
		String items[] = input.split(",");
		for (String item : items) {
			if (item.equals("g")) {
				long t = System.nanoTime();
				ms.getMedian();
				getMedTime.add(System.nanoTime() - t); // Store time for getMedian()
			} else if (item.equals("d")) {
				long t = System.nanoTime();
				ms.deleteMedian();
				deleteMedTime.add(System.nanoTime() - t); // Store time for deleteMedian()
			} else if (item.startsWith("a")) {
				int v = Integer.parseInt(item.split(" ")[1]);
				long t = System.nanoTime();
				ms.add(v);
				addTime.add(System.nanoTime() - t); // Store time for add(v)
			}
		}
		System.out.println(
				"Average time for add: " + (addTime.stream().mapToLong(Long::longValue).sum() / addTime.size()) + " nanoseconds");
		System.out.println("Average time for deleteMedian: "
				+ (deleteMedTime.stream().mapToLong(Long::longValue).sum() / deleteMedTime.size()) + " nanoseconds");
		System.out.println("Average time for getMedian: "
				+ (getMedTime.stream().mapToLong(Long::longValue).sum() / getMedTime.size()) + " nanoseconds");
		System.out.println();
	}
	
	/**
	 * Runs extra credit program.
	 * 
	 * @throws IOException if input file is invalid or not found
	 */
	public static void runExtraCreditProgram() throws IOException {
		MedianSet ms = new MedianSet();
		String input = new String(Files.readAllBytes(Paths.get("hw4input.txt")));
		String items[] = input.split(",");
		for (String item: items) {
			if (item.startsWith("a")) { // Here we are interested only "add" option because
				// the task is to sort arrays.

				int v = Integer.parseInt(item.split(" ")[1]);
				ms.add(v);
			}
		}
		long t = System.nanoTime();
		System.out.println("Sort using medians: " + Arrays.toString(ms.sortUsingMedians()));
		System.out.println("Time: " + (System.nanoTime() - t));
		System.out.println();
		for (String item: items) {
			if (item.startsWith("a")) { // Here we are interested only "add" option because
				// the task is to sort arrays.

				int v = Integer.parseInt(item.split(" ")[1]);
				ms.add(v);
			}
		}
		t = System.nanoTime();
		System.out.println("Sort using merge sort: " + Arrays.toString(ms.sortUsingMergeSort()));
		System.out.println("Time: " + (System.nanoTime() - t));
	}

	/**
	 * Method that sorts values in two queues and returns
	 * them as array.
	 * 
	 * @return sorted array of integers in ascending order
	 */
	public int[] sortUsingMedians() {

		// First, create empty array with the defined length.
		int[] res = new int[qmax.size() + qmin.size()];
		
		// Then, declare median index.
		int i;
	
		// If length of array is even.
		if (res.length % 2 == 0) {
			i = res.length / 2 - 1; // Store median index.
			
//			 Here we need to loop through the two indexes (j and k)
//			 to define places where the results of deleteMedian operations
//			 will be put. In case of even cardinality, index j should go down,
//			 whereas index k should go up. Every time we perform deleteMedian
//			 operation, we get two already sorted values (two queues, two halves
//			 or array) in ascending order, but every first value will be going down, 
//			 and every second value will be going up.
			for (int j = i, k = i + 1; j >= 0; j --, k ++) {
				res[j] = deleteMedianSort();
				if (k < res.length) {
					res[k] = deleteMedianSort();
				}
			}
		} else { // Odd cardinality case (logic will be vice versa).
			i = res.length / 2;
			for (int j = i, k = i - 1; j < res.length; j ++, k --) {
				res[j] = deleteMedianSort();
				if (k >= 0) {
					res[k] = deleteMedianSort();
				}
			}
		}

		return res;
	}

	/**
	 * Method to perform sort the array using merge sort.
	 * First, it needs to fill out the array with values
	 * from the queues, second sort this array using merge
	 * sort and return sorted array.
	 * 
	 * @return sorted array of integers in ascending order
	 */
	public int[] sortUsingMergeSort() {
		int[] res = new int[qmax.size() + qmin.size()];
		int i = 0;
		while (!qmax.isEmpty()) { // while qmax  not empty
			res[i++] = qmax.remove(); // remove the value
		}
		while (!qmin.isEmpty()) { // while qin  not empty
			res[i++] = qmin.remove(); // remove the value
		}

		mergeSort(res, 0, res.length - 1); // call the mergesort method
		return res;
	}

	/**
	 * Required add operation to add value to the
	 * set.
	 * 
	 * @param v the value to be added
	 * 
	 * @return string representation of added value
	 *         (required for console program)
	 */
	public String add(int v) {

		String q;

		if (qmax.isEmpty() && qmin.isEmpty()) {
			qmax.add(v);
			q = "Q-";
		} else if (qmin.isEmpty() && !qmax.isEmpty()) {
			qmin.add(v);
			q = "Q-";
		} else if (!qmin.isEmpty() && qmax.isEmpty()) {
			qmax.add(v);
			q = "Q+";
		} else { // If both of them is not empty
			if (v > getMedian()) {
				qmin.add(v);
				q = "Q+";
			} else {
				qmax.add(v);
				q = "Q-";
			}
		}

		// Rebalance.
		if (qmin.size() - qmax.size() > 1) {
			qmax.add(qmin.remove());
		} else if (qmax.size() - qmin.size() > 1) {
			qmin.add(qmax.remove());
		}
		return q;
	}

	/**
	 * Required method to return the median from the set.
	 * 
	 * @return the median value
	 */
	public int getMedian() {

		if (qmax.isEmpty() && qmin.isEmpty()) {
			return -1; // If no values present yet.
		}

		if (qmax.size() == qmin.size()) {
			return (qmax.peek() + qmin.peek()) / 2;
		} else if (qmax.size() > qmin.size()) {
			return qmax.peek();
		} else {
			return qmin.peek();
		}
	}

	/**
	 * Required method to delete the median.
	 * 
	 * @return the string representation of deleted value
	 *         (required for console program)
	 */
	public String deleteMedian() {
		if (qmax.isEmpty() && qmin.isEmpty()) {
			return "no value present";
		}
		if (qmax.isEmpty() && !qmin.isEmpty()) {
			return "median " + qmin.remove() + " is deleted from Q+";
		}
		if (!qmax.isEmpty() && qmin.isEmpty()) {
			return "lower median " + qmax.remove() + " is deleted from Q-";
		}
		String q;

		if ((qmax.size() + qmin.size()) % 2 == 0) { // If even cardinality.
			int m = qmin.remove(); // Delete upper median.
			q = "upper median " + m + " is deleted from Q+";
		} else { // Otherwise, delete median.
			int m = qmax.size() > qmin.size() ? qmax.remove() : qmin.remove();
			q = "median " + m + " is deleted from Q-";
		}
		return q;
	}

	/**
	 * The same function as deleteMedian, but returns
	 * only integer value (previously deleted).
	 * Required for sortUsingMedians() operation
	 * 
	 * @return deleted median value
	 */
	public int deleteMedianSort() {
		if (qmax.isEmpty() && qmin.isEmpty()) {
			return -1;
		}
		if (qmax.isEmpty() && !qmin.isEmpty()) {
			return qmin.remove();
		}
		if (!qmax.isEmpty() && qmin.isEmpty()) {
			return qmax.remove();
		}

		if ((qmax.size() + qmin.size()) % 2 == 0) { // If even cardinality.
			int m = qmax.remove();
			return m;
		} else {
			int m = qmin.remove();
			return m;
		}
	}

	/**
	 * Helper operation to merge two sorted arrays.
	 * 
	 * @param a array to be sorted
	 * @param p index from
	 * @param q middle index
	 * @param r index to
	 */
	private void merge(int[] a, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int left[] = new int[n1 + 1];
		int right[] = new int[n2 + 1];
		for (int i = 0; i < n1; i++) {
			left[i] = a[p + i];
		}
		for (int i = 0; i < n2; i++) {
			right[i] = a[q + i + 1];
		}
		left[n1] = Integer.MAX_VALUE;
		right[n2] = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		for (int k = p; k <= r; k++) {
			if (left[i] <= right[j]) {
				a[k] = left[i];
				i++;
			} else {
				a[k] = right[j];
				j++;
			}
		}
	}

	/**
	 * Performs a merge sort of array a.
	 * 
	 * @param a the array to be sorted
	 * @param p index from
	 * @param r index to
	 */
	public void mergeSort(int a[], int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			merge(a, p, q, r);
		}
	}

}
