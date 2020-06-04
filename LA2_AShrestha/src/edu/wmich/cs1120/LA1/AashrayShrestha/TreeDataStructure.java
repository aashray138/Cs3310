package edu.wmich.cs1120.LA1.AashrayShrestha;

public class TreeDataStructure implements INode {
    private String id;
   // private TreeDataStructure rootNode;
    private TreeDataStructure leftNode, rightNode;
    private String pId;

    TreeDataStructure(String root) {
        id = root;
        leftNode = rightNode = null;
    }

    TreeDataStructure(String ID, String parentID) {
        id = ID;
        pId = parentID;
        leftNode = rightNode = null;
    }
    
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
    @Override
    public boolean addChild(String ID, String parentID) {
        boolean leftChild = false;
        boolean rightChild = false;
        if (id.equalsIgnoreCase(parentID)) {
            if (leftNode == null) {
                leftNode = new TreeDataStructure(ID, parentID);
                return true;
            } else if (rightNode == null) {
                rightNode = new TreeDataStructure(ID, parentID);
                return true;
            } else {
                System.out.println("Node " + id + " already has 2 children.");
                return false;
            }
        } else {
            if (leftNode != null && rightNode == null) {
                return leftNode.addChild(ID, parentID);
            }
            if (leftNode != null && rightNode != null) {
                leftChild = leftNode.addChild(ID, parentID);
                rightChild = rightNode.addChild(ID, parentID);
                if (leftChild == true || rightChild == true) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * This method looks within the tree to find if �value� (the ID of the node to
     * be found) is contained in that subtree. The node used to call the find method
     * acts as the root of that tree / subtree.
     *
     * @param value a string (ID of a node) to be found in the tree
     *
     * @return the node if found.
     */
    @Override
    public INode find(String value) {
        if (id.equalsIgnoreCase(value)) {
            return this;
        } else {
            if (leftNode != null && rightNode == null) {
                return leftNode.find(value);
            }
            if (leftNode != null && rightNode != null) {
                INode foundNodeLeft = leftNode.find(value);
                INode foundNodeRight = rightNode.find(value);
                if (foundNodeLeft != null) {
                    return foundNodeLeft;
                }
                if (foundNodeRight != null) {
                    return foundNodeRight;
                }
            }
        }
        return null;
    }
    /**
     * Gets the parent of this node.
     *
     ** @return the parent node of the Node used to call this method.
     */
    @Override
    public INode getParent() {
        return this.getParent();
    }

    /**
     * Gets the size of the tree.
     *
     * @return the size of the tree starting from the node that is used to call this
     *         method, all the way down to the leaf nodes.
     */
  
    @Override
    public int size() {
        int count = 1;
        if (this.leftNode != null) {
            count += this.leftNode.size();
        }
        if (this.rightNode != null) {
            count += this.rightNode.size();
        }
        return count;
    }

    /**
     * Method to get the ID of the node.
     *
     * @return String representation of the node ID
     */
    public String toString() {
        String node = "";
        if (this != null) {
            node = this.getId();
            if (this.leftNode != null) {
                node = node + " " + this.leftNode.getId();
                if (this.rightNode != null) {
                    node = node + " " + this.rightNode.getId();
                }
            }
        }
        return node;
    }
    /**
     * Method to get the ID of the node.
     *
     * @return String representation of the node ID
     */
    @Override
    public String getId() {
        return id;
    }
    
    /**
     * Prints the node upon which this method is called as well as all the children
     * nodes to show the structure of the tree. Uses the toString() format to print.
     */
    @Override
    public void printTree() {
        System.out.println(this.toString());
        if (this.leftNode != null) {
            this.leftNode.printTree();
        }
        if (this.rightNode != null) {
            this.rightNode.printTree();
        }
    }

}