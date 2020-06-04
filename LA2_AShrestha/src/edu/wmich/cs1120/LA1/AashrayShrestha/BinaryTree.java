package edu.wmich.cs1120.LA1.AashrayShrestha;
import java.util.Scanner;

/**
 * 
 * @author Aashray
 *
 */
public class BinaryTree {
	/** The main class instantiates "root" the TreeDataStructure class where "A" is passed as
	 *  an argument. 
	 *  root object is used to add the left and right child to the tree.
	 *  Prints the size of the tree from the search value
	 *  Initially prints the tree from the code given
	 *  Calling the getInput to get the input from the number to select the option
	 *  Runs the program according to the inout
	 * @param args
	 */
	 public static void main(String[] args) {
	        Scanner bucky = new Scanner(System.in);
	        TreeDataStructure root = new TreeDataStructure("A");
	        root.addChild("B", "A");
	        root.addChild("C", "A");
	        root.addChild("D", "B");
	        root.addChild("E", "B");
	        root.addChild("F", "C");
	        root.addChild("G", "C");
	        root.addChild("H", "D");
	        root.addChild("I", "D");
	        root.addChild("J", "E");
	        root.addChild("K", "E");
	        root.addChild("L", "F");
	        root.printTree();
	        System.out.println("There are  " + root.size() + "  nodes in this tree.");

	        while (true) {
	            int number = getInput(bucky);
	            if (number == 0) {
	                bucky.close();
	                System.exit(0);
	            } else if (number == 1) {
	                System.out.println("Enter the node you want to add : ");
	                String selectNode = bucky.nextLine();
	                System.out.println("Enter parent node you want to add the node to : " + selectNode);
	                String newParent = bucky.nextLine();
	                if (root.addChild(selectNode.toUpperCase(), newParent.toUpperCase()) == true) {
	                    System.out.println("Node successfully added!");
	                    root.printTree();
	                }
	            } else if (number == 2) {
	                System.out.println("Please enter the root node->");
	                String findnumber = bucky.nextLine();
	                TreeDataStructure find = (TreeDataStructure) root.find(findnumber.toUpperCase());
	                System.out.println("There are " + find.size() + " nodes in the tree");
	                System.out.println(find);
	            } else if (number == 3) {
	                System.out.println("Please enter the node present in the tree to look for->");
	                String findNode = bucky.nextLine();
	                INode find = root.find(findNode.toUpperCase());
	                if (find != null) {
	                    System.out.println("Node " + findNode.toUpperCase() + " found!");
	                    System.out.println(find);
	                } else {
	                    System.out.println("Node " + findNode.toUpperCase() + " does not exist.");
	                }
	            } else if (number == 4) {
	                root.printTree();
	            }
	        }
	    }
	
	/**
	  * 
	  * @param bucky
	  * @return number choice of the four given numbers 1, 2, 3, or 4s
	  */
	     public static int getInput(Scanner bucky) {
	    	 System.out.println("Please select from one of the following options:");
	         int number = 0;
	         boolean flag = true;
	         while (flag == true) {
	             printMenu();
	             System.out.print("-> ");
	             String numberString = bucky.nextLine();
	             if (numberValidation(numberString, "integer") == true) {
	                 number = Integer.parseInt(numberString);
	                 if (number == 1 || number == 2 || number == 3 || number == 0) {
	                     flag = false;
	                 } else {
	                     System.out.println("You must enter 1, 2, 3, or 0. Please try again");
	                 }
	             } else {
	                 System.out.println("You must enter 1, 2, 3, or 0. Please try again");
	             }
	         }
	         return number;
	     }
	     
/** Prints out the menu whenever called
 */
    public static void printMenu() {
        System.out.println(" 1. Add Node\n 2. Tree Size\n 3. Find Node\n 0. Exit");
    }
/**
 * This is a method to check number Validation such that the input is checked every time
 * so that there are no errors in the code
 * It uses the try catch with NumberFormatException and NullPointerException 
 * @param number
 * @param validationType
 * @return boolean validate
 */
    public static boolean numberValidation(String number, String validationType) {
        boolean validate = true;
        if (validationType == "integer") {
            try {
                Integer.parseInt(number);
            } catch (NumberFormatException e) {
                validate = false;
            } catch (NullPointerException e) {
                validate = false;
            }
       // } else if (validationType == "double") {
         //   try {
           //     Double.parseDouble(number);
           // } catch (NumberFormatException e) {
            //    validate = false;
           //  } catch (NullPointerException e) {
              //   validate = false;
            // }
        // } else {
           //  System.out.println("Bad validation type");
           // validate = false;
        }
        return validate;
    }


}
