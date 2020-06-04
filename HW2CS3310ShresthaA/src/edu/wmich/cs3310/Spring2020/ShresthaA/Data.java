package edu.wmich.cs3310.Spring2020.ShresthaA;

import java.io.*;
import java.util.*;

public class Data {

	public static final List<String> readStringsFromFile(String filename) throws IOException {
		List<String> temp = new LinkedList<String>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
		String line;
		// Read each line and add to the temp file
		while ((line = bufferedReader.readLine())!=null) temp.add(line);
		//return temp
		return temp;
	}

	/**
	 * @param filename is the file name
	 * @return  returns a list of dna and rna sequence
	 * @throws IOException .
	 */
	public static final List<PerfectList> getListsOfSequences(String filename) throws IOException {
		List<String> strings = readStringsFromFile(filename);
		List<PerfectList> result = new ArrayList<>();
		PerfectList temp;
		// loop 
		for (String s : strings) {
			// create new perfectlist (linkedlist)
			temp = new PerfectList();
			// loop through
			for (Character character : s.toCharArray())
				temp.add(character);
			// add to the result
			result.add(temp);
		}
		return result;
	}
}
