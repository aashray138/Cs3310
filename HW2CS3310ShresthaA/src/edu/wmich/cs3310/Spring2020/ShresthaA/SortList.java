package edu.wmich.cs3310.Spring2020.ShresthaA;

import java.util.*;

public class SortList {

	String listSort;

	public SortList (String listSort) {
		this.listSort = listSort;
		String dispList = listSort(listSort);
		display(dispList);
	}

	private void display(String dispList) {
		char [] printed = dispList.toCharArray();
		for (int i =0 ; i < dispList.length(); i++) {
			System.out.print(printed[i]);
		}
		System.out.println();
	}

	private String listSort(String inputString) {
		Character tempArray[] = new Character[inputString.length()]; 
		for (int i = 0; i < inputString.length(); i++) { 
			tempArray[i] = inputString.charAt(i); 
		} 

		Arrays.sort(tempArray, new Comparator<Character>(){ 

			@Override
			public int compare(Character c1, Character c2) 
			{ 

				return Character.compare(Character.toLowerCase(c1), 
						Character.toLowerCase(c2)); 
			} 
		}); 

		StringBuilder sb = new StringBuilder(tempArray.length); 
		for (Character c : tempArray) 
			sb.append(c.charValue()); 

		return sb.toString(); 

	}


}
