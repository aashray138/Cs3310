package edu.wmich.cs3310.Spring2020.ShresthaA;

import java.io.IOException;
import java.util.*;

public class Main {

	// Creating a value temp that can be used in the program
	static double temp;

	// Creating a List of PerfectList type 
	static List<PerfectList> perfectListsFromFile;

	/**
	 * Main method that takes the name of the file, finds out the time taken to form the list 
	 * and checks the formed lists 
	 * Has a try catch block for a IOException : FileNotFoundExceptions
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Scanner to get user input
			Scanner scanner = new Scanner(System.in);

			// Ask the user the name of the file
			System.out.println("Please enter the file name containing DNA/RNA Sequences:");
			String filename = scanner.nextLine();
			//get the time in miliseconds
			temp = System.currentTimeMillis();

			// get the linkedList
			perfectListsFromFile = Data.getListsOfSequences(filename);
			List<PerfectList> perfectLists = new ArrayList<>();

			// The time taken to create linked list
			System.out.println("Time taken to create linked lists from sequences: " +
					(System.currentTimeMillis() - temp) + " milliseconds\n");

			// This code is different from what we were instructed, but it does find the similar genetic sequences  for the 10
			perfectLists.add(generateFromString("TATA"));
			perfectLists.add(generateFromString("UAGCTA"));
			perfectLists.add(generateFromString("ACCTAGGT"));
			perfectLists.add(generateFromString("TGGATCCA"));
			perfectLists.add(generateFromString("TTTACCT"));
			perfectLists.add(generateFromString("TAGUCTA"));
			perfectLists.add(generateFromString("CTAGUCTAA"));
			perfectLists.add(generateFromString("TTAAATTCCTTTAGG"));
			perfectLists.add(generateFromString("AGTCCGATCCGT"));
			perfectLists.add(generateFromString("AAAAAATTTTTTGGGGGGCCCCCC"));

			// Test case method to check for each of the lines
			testCase(perfectLists);
			testCase(perfectListsFromFile);

			// to store time
			long sortTime = System.currentTimeMillis();
			System.out.print("The first sorted line is :");
			SortList sorted = new SortList("GTTTTGGAACCAAAACCTTGCAAAACCUUG");
			System.out.println();

			// to print out the last sorted 
			System.out.print("The last sorted line is :");
			SortList sorted2 = new SortList("CAAAACCUUGCAAAACCTTGGTTTTGGAAC");
			System.out.println();
			long sortTime2 = System.currentTimeMillis();

			// print the time taken to sort
			System.out.println("Time taken to sort " + (sortTime2-sortTime) + " milliseconds");
		} catch (IOException e) {
			System.out.println("Can't read the file");
		}
	}

	/**
	 * @param perfectList
	 * @param fromFile
	 * @return count if similar genetic palindromes(perfectList) in strings from file
	 */
	private static int countSimilarGeneticPalindromes (PerfectList perfectList, List<PerfectList> fromFile) {
		int count =0;
		// loop through the size 
		for (int i = 0; i < fromFile.size(); i++) {
			// We do not have anything more than 14, so just checking if it is more than 3 
			if (perfectList.size()>3) {
				int j=0;
				// while loop
				while (j<fromFile.get(i).size()-3) {
					// check the value in a double nested if 
					for (int k = j+3; k < fromFile.get(i).size(); k++) {
						if (k-j+1==perfectList.size()) {
							if (isSimilar1(perfectList, sublist(fromFile.get(i), j, k)))
								// count similar palindromes
								count++;
						}
					}
					j++;
				}
			}
		}
		return count;
	}

	/**
	 * @param perfectList
	 * @param sublist
	 * @return true if perfectList and sublist is similar
	 */
	private static boolean isSimilar1(PerfectList perfectList, PerfectList sublist) {
		return isGeneticPalindrome(perfectList) && isGeneticPalindrome(sublist);
	}

	/**
	 * This is a set of tests for a single sequence of DNA OR RNA
	 * @param perfectLists
	 */
	private static void testCase(List<PerfectList> perfectLists) {
		// loop to get the DNA sequence
		for (int i = 0; i < perfectLists.size(); i++) {
			System.out.println("DNA sequence " + (i+1) + " : " + getString(perfectLists.get(i)) + "\n");
			// Input Validation
			if (isDNAorRNA(perfectLists.get(i))) {
				temp = System.currentTimeMillis();
				// if it is geentic palindrome true else false
				if (isGeneticPalindrome(perfectLists.get(i)))
					System.out.println(getString(perfectLists.get(i)) + " is a genetic palindrome. ");
				else
					System.out.println(getString(perfectLists.get(i)) + " is not a palindromic sequence.");
				// find the genetic palindromes from the given 
				findGeneticPalindromes(perfectLists.get(i));
				System.out.println("Time to test whether a genomic subsequence in Sequence " + i + " is palindromic:" + (System.currentTimeMillis() - temp));
				temp = System.currentTimeMillis();
				System.out.println(getString(perfectLists.get(i)) + " has " +countSimilarGeneticPalindromes(perfectLists.get(i), perfectListsFromFile) +  " similar sequences in the file, and it took " + (System.currentTimeMillis()-temp) + "  milliseconds to determine that.\n");
			}
			else {
				System.out.println(getString(perfectLists.get(i)) + " contains characters that do not denote DNA molecules. Skipped.\n");
			}

		}
	}

	/**
	 * find genetic palindromes in perfectList
	 * @param perfectList
	 */
	private static void findGeneticPalindromes(PerfectList perfectList) {
		PerfectList tmp = new PerfectList();
		if (perfectList.size()>3) {
			int i=0;
			while (i<perfectList.size()) {
				// find the genetic palindromes inside the lists
				for (int j = i+3; j < perfectList.size(); j++) {
					if (isGeneticPalindrome(sublist(perfectList, i, j))) {
						tmp.append(sublist(perfectList, i, j));
						tmp.add('-');
					}

				}

				i++;
			}
			System.out.println("However, it has the following genetic palindromes " + getString(tmp));
		}
		else {
			System.out.println("This sequence does not contain any genetic palindromes");
		}
	}

	/**
	 * @param perfectList
	 * @param i
	 * @param j
	 * @return sublist from i to j
	 */
	private static PerfectList sublist(PerfectList perfectList, int i, int j) {
		// this creates a sub lisst than narrows down
		Iterator iterator = perfectList.iterator();
		PerfectList tmp = new PerfectList();
		for (int k = 0; k < i; k++) iterator.next();
		for (int k = i; k < j+1; k++) tmp.add((char) iterator.next());
		return tmp;
	}

	/**
	 * @param perfectList
	 * @return
	 */
	private static String getString(PerfectList perfectList) {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator iterator = perfectList.iterator();
		while (iterator.hasNext()) stringBuilder.append(iterator.next());
		return stringBuilder.toString();
	}

	/**
	 * @param s
	 * @return perfect list with characters from string s
	 */
	private static PerfectList generateFromString(String s) {
		PerfectList tmp = new PerfectList();
		for (Character character : s.toCharArray()) tmp.add(character);
		return tmp;
	}

	/**
	 * if perfect list contains only 'A', 'T', 'U', 'C' or 'G'
	 * @param perfectList
	 * @return
	 */
	private static boolean isDNAorRNA(PerfectList perfectList) {
		// check to see if it really is a dna or rna and does not contain any rubbish data
		for (int i = 0; i < perfectList.size(); i++) {
			if (!((char)perfectList.get(i)== 'A' ||
					(char)perfectList.get(i)== 'T' ||
					(char)perfectList.get(i)== 'U' ||
					(char)perfectList.get(i)== 'C' ||
					(char)perfectList.get(i)== 'G')) return false;
		}
		return true;
	}

	/**
	 * @param perfectList
	 * @return true if perfect list it's genetic palindrome
	 */
	private static boolean isGeneticPalindrome(PerfectList perfectList) {
		boolean tmp = true;
		for (int i = 0; i < perfectList.size() / 2; i++) {
			if (!(isPair((char)perfectList.get(i),(char)perfectList.get(perfectList.size()-i-1)))) tmp = false;
		}
		return tmp;
	}

	/**
	 * return true if char1 and char2 it is the pair is either a base or its complement
	 * @param character1
	 * @param character2
	 * @return
	 */
	private static boolean isPair(Character character1, Character character2) {
		// check to see if it really is a dna or rna and match it with the base
		boolean tmp = true;
		if (character1=='A' )
			if (!(character2 == 'T' || character2 == 'U' )) tmp = false;
		if (character1=='T' )
			if (!(character2 == 'A' )) tmp = false;
		if (character1=='U' )
			if (!(character2 == 'A' )) tmp = false;
		if (character1=='C' )
			if (!(character2 == 'G' )) tmp = false;
		if (character1=='G' )
			if (!(character2 == 'C' )) tmp = false;
		return tmp;
	}
}