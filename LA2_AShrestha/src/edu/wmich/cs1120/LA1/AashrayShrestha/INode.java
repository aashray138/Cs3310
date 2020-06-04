package edu.wmich.cs1120.LA1.AashrayShrestha;

public interface INode {
    /**
     * This method checks to see if the specified parent node exists in the tree. If
     * not, it returns false after printing an appropriate message. If the node
     * exists, the method checks to see if it already has two children. If it does,
     * the method returns false. Otherwise, it either adds the new node as the
     * parent nodes left child (if the parent has no children) or as the right
     * child (if the parent already has one child).
     *
     * @param ID       new node to add
     * @param parentID parent node in Tree
     * @return true if successful, false otherwise
     */
    public boolean addChild(String ID, String parrentID);
    /**
     * This method looks within the tree to find if value(the ID of the node to
     * be found) is contained in that subtree. The node used to call the find method
     * acts as the root of that tree / subtree.
     *
     * @param value a string (ID of a node) to be found in the tree
     *
     * @return the node if found.
     */
    public INode find(String value);
    /**
     * Gets the parent of this node.
     *
     ** @return the parent node of the Node used to call this method.
     */
    public INode getParent();
    /**
     * Gets the size of the tree.
     *
     * @return the size of the tree starting from the node that is used to call this
     *         method, all the way down to the leaf nodes.
     */
    public int size();
    /**
     * Method to get the ID of the node.
     *
     * @return String representation of the node ID
     */
    public String toString();
    /**
     * Method to get the ID of the node.
     * 
     * @return ID
     */
    public String getId();

    /**
     * Prints the node upon which this method is called as well as all the children
     * nodes to show the structure of the tree. Uses the toString() format to print.
     */
    public void printTree();
} // End of INode interface
