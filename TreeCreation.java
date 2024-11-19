import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TreeCreation {
    public static void main(String[] args) {

        // create a binary tree and insert some data
        BinaryTree binaryTree = new BinaryTree(15);
        binaryTree.insert(10);
        binaryTree.insert(25);
        binaryTree.insert(36);
        binaryTree.insert(20);
        binaryTree.insert(30);
        binaryTree.insert(5);
        binaryTree.insert(8);
        binaryTree.insert(23);
        binaryTree.insert(18);
        binaryTree.insert(39);
        binaryTree.insert(45);
        binaryTree.insert(42);
        binaryTree.insert(19);
        binaryTree.insert(33);
        binaryTree.insert(22);
        binaryTree.insert(24);

        System.out.println("In Order:");
        List<Node> inOrderNodes = binaryTree.inOrderTraversal();
        inOrderNodes.stream().forEach(node -> System.out.print(node.data + ", "));
        System.out.println();

        System.out.println("Pre Order:");
        List<Node> preOrderNodes = binaryTree.preOrderTraversal();
        preOrderNodes.stream().forEach(node -> System.out.print(node.data + ", "));
        System.out.println();

        System.out.println("Post Order:");
        List<Node> postOrderNodes = binaryTree.postOrderTraversal();
        postOrderNodes.stream().forEach(node -> System.out.print(node.data + ", "));
        System.out.println();

        System.out.println("Breadth first order:");
        List<Node> breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();
        breadthFirstOrderNodes.stream().forEach(node -> System.out.print(node.data + " (lvl: " + node.level + "), "));
        System.out.println();

        binaryTree.delete(20);
        System.out.println("Breadth first order: deleting 20");
        breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();
        breadthFirstOrderNodes.stream().forEach(node -> System.out.print(node.data + " (lvl: " + node.level + "), "));
        System.out.println();

        binaryTree.delete(5);
        System.out.println("Breadth first order: deleting 5");
        breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();
        breadthFirstOrderNodes.stream().forEach(node -> System.out.print(node.data + " (lvl: " + node.level + "), "));
        System.out.println();

        binaryTree.delete(8);
        System.out.println("Breadth first order: deleting 8");
        breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();
        breadthFirstOrderNodes.stream().forEach(node -> System.out.print(node.data + " (lvl: " + node.level + "), "));
        System.out.println();

        binaryTree.delete(36);
        System.out.println("Breadth first order: deleting 36");
        breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();
        breadthFirstOrderNodes.stream().forEach(node -> System.out.print(node.data + " (lvl: " + node.level + "), "));
        System.out.println();

        binaryTree.delete(10);
        System.out.println("Breadth first order: deleting 10");
        breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();
        breadthFirstOrderNodes.stream().forEach(node -> System.out.print(node.data + " (lvl: " + node.level + "), "));
        System.out.println();

        binaryTree.delete(19);
        System.out.println("Breadth first order: deleting 19");
        breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();
        breadthFirstOrderNodes.stream().forEach(node -> System.out.print(node.data + " (lvl: " + node.level + "), "));
        System.out.println();

        binaryTree.delete(18);
        System.out.println("Breadth first order: deleting 18");
        breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();
        breadthFirstOrderNodes.stream().forEach(node -> System.out.print(node.data + " (lvl: " + node.level + "), "));
        System.out.println();

    }
}

/**
 * Creates a binary tree by creating a root node that can be inserted into and
 * deleted from. Does not allow duplicate values
 */
class BinaryTree extends Node implements BinaryTreeInterface {

    /**
     * Constructor, call superconstructor for superclass then fill parent and level
     * of nodes in tree (in this case only the root node).
     * 
     * @param data The int value for the root node.
     */
    BinaryTree(int data) {
        super(data);
        this.fillParentsAndLevel(); // fill in level and parent of root data
    }

    /**
     * Override implemented interface, calls overloaded insert on "this," allowing
     * us to insert data into the tree just using .insert(5).
     * 
     * @param data The int value to be inserted into the tree.
     */
    @Override
    public void insert(int data) {

        if (this.data == null) {
            this.data = data;
        } else {
            insert(data, this);
        }

        this.fillParentsAndLevel(); // fill in level and parent of inserted and all data
    }

    /**
     * Inserts a new node if a node with this data does not already exist in the
     * tree. This overloaded method called by overridden insert(int data) method.
     * 
     * @param data The int value to be inserted into the tree.
     * @param node The current iteration node to check if value can be inserted as a
     *             child of.
     */
    private void insert(int data, Node node) {

        // insert node to left if the data is smaller and the left node is null
        if (node.left == null && data < node.data) {
            node.left = new Node(data);
            // otherwise if data is smaller, continue recursion
        } else if (data < node.data) {
            insert(data, node.left);
            // insert node to right if data is larger and right node is null
        } else if (node.right == null && data > node.data) { // change to >= if we want to allow repeat values
            node.right = new Node(data);
            // otherwise if data is larger, continue recursion
        } else if (data > node.data) { // change to >= if we want to allow repeat values
            insert(data, node.right);
        }
    }

    /**
     * Calls traverse with parameter to return in-order list of nodes.
     * 
     * @return Returns a list of nodes in in-order traversal order.
     */
    List<Node> inOrderTraversal() {
        return traverse(this, "inOrder", new ArrayList<>());
    }

    /**
     * Calls traverse with parameter to return pre-order list of nodes.
     * 
     * @return Returns a list of nodes in pre-order traversal order.
     */
    List<Node> preOrderTraversal() {
        return traverse(this, "preOrder", new ArrayList<>());
    }

    /**
     * Calls traverse with parameter to return post-order list of nodes.
     * 
     * @return Returns a list of nodes in post-order traversal order.
     */
    List<Node> postOrderTraversal() {
        return traverse(this, "postOrder", new ArrayList<>());
    }

    /**
     * 
     * Returns a list of nodes in pre-order, in-order, or post-order order, based on
     * input given
     * 
     * @param node      Current node of traversal.
     * @param traversal A string representing the traversal wanted: inOrder,
     *                  preOrder, or postOrder.
     * @param nodes     The list of nodes in order requested.
     * @return The finished list of nodes in the desired traversal order.
     */
    private List<Node> traverse(Node node, String traversal, List<Node> nodes) {

        if (node == null) {
            return nodes;
        }

        // if pre-order, add node before recursion
        if (traversal.equals("preOrder")) {
            nodes.add(node);
        }

        // recurse
        traverse(node.left, traversal, nodes);

        // if in-order, add node in middle of recursion
        if (traversal.equals("inOrder")) {
            nodes.add(node);
        }

        // recurse
        traverse(node.right, traversal, nodes);

        // if post-order, add node at end of recursion
        if (traversal.equals("postOrder")) {
            nodes.add(node);
        }

        return nodes;
    }

    /**
     * Calls overloaded breadthFirstTraversal for the root node, translates the
     * returned TreeMap to a list, and returns the list.
     * 
     * @return List of Nodes in breadth first traversal order.
     */
    List<Node> breadthFirstTraversal() {

        // get a tree map of breadth first ordered nodes
        TreeMap<Integer, List<Node>> breadthTreeMap = breadthFirstTraversal(this, new TreeMap<>(), 0);
        List<Node> nodes = new ArrayList<>();

        // iterate tree map and add to list in correct order to return
        for (int key : breadthTreeMap.keySet()) {
            for (Node node : breadthTreeMap.get(key)) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    /**
     * Returns a tree map of nodes in breadth first traversal order, where the key
     * is the level, and each level has a list of nodes on that level--in order,
     * left to right. Also fills in each node's parent and level
     * 
     * @param node  The current node of the traversal.
     * @param nodes A TreeMap of the nodes in breadth first traversal order, keeping
     *              track of the nodes by traversing left to right and adding to a
     *              list separating based on level, with the map being
     *              TreeMap<Level, List<Node> L->R> to maintain order. TreeMap can
     *              then be iterated by Level:List Order to get breadth first
     *              traversal order.
     * @param level The level the node in the binary tree is at, starting at 0.
     * @return A TreeMap in breadth first traversal order if iterated over entries
     *         by key (level, naturally in top to bottom order) and value (List,
     *         naturally in left -> right order)
     */
    private TreeMap<Integer, List<Node>> breadthFirstTraversal(Node node, TreeMap<Integer, List<Node>> nodes,
            int level) {

        // base case
        if (node == null) {
            return nodes;
        }

        // set the level
        node.level = level;

        // set this node as the children's parent, if they exist
        if (node.left != null) {
            node.left.parent = node;
        }
        if (node.right != null) {
            node.right.parent = node;
        }

        // if this level isn't a key in the tree map, add it and the node as a new list
        if (nodes.get(level) == null) {
            nodes.put(level, new ArrayList<>(List.of(node)));
            // else add this node to the existing level key
        } else {
            nodes.get(level).add(node);
        }

        // continue recursion, adding 1 to level for the next deeper level
        breadthFirstTraversal(node.left, nodes, level + 1);
        breadthFirstTraversal(node.right, nodes, level + 1);

        return nodes;
    }

    /**
     * Fills in the parent and the level of each node, by using breadth first
     * traversal method, which fills in level and parent.
     */
    private void fillParentsAndLevel() {
        breadthFirstTraversal(this, new TreeMap<>(), 0);
    }

    /**
     * Calls overloaded delete on the root node, and then fills back in parent and
     * levels after deletion, due to node reordering.
     * 
     * @param data The integer value to be deleted from the tree.
     */
    void delete(int data) {

        if (this.data == null || (this.data == data && this.left == null && this.right == null)) {
            this.data = null;
            return;
        }

        // we need to know our in-order successor to delete
        fillInOrderSuccessors(this);
        delete(data, this); // call delete

        // parents and levels may have changed after deletion, so recalculate
        this.fillParentsAndLevel();
    }

    /**
     * Deletes node if it exists, replacing node with in-order successor, if it
     * exists. This overloaded method is called by other method that is more simple
     * to call for the user.
     * 
     * @param data The integer value to search for to delete from the tree.
     * @param node The current node in the tree iteration.
     */
    private void delete(int data, Node node) {

        // base case
        if (node == null) {
            return;
        }

        // if we've found a matching node to delete
        if (node.data == data) {

            // if both children aren't null
            if (node.left != null && node.right != null) {

                // if we're reordering by moving successor up in the tree
                if (node.inOrderSuccessor.parent != node) {

                    // change parent's left to our right -- we're already all the way left,
                    // due to being the in-order successor
                    node.inOrderSuccessor.parent.left = node.inOrderSuccessor.right;

                    // change deleted node's data to repositioned node's data
                    node.data = node.inOrderSuccessor.data;

                    // else if successor is child of parent
                } else {

                    // set deleted node's parent's left/right to the successor,
                    // depending on which way the deleted node is
                    if (node.parent.left == node) {
                        node.parent.left = node.inOrderSuccessor;
                    } else {
                        node.parent.right = node.inOrderSuccessor;
                    }

                    // set our left (we know it's null currently due to being in-order successor,
                    // meaning we're all the way left) as deleted node's left
                    node.inOrderSuccessor.left = node.left;
                }

                return;

                // else if we're the root node (don't have a parent)
            } else if (node.parent == null) {

                if (node.left == null) {
                    node.data = node.right.data;
                    node.left = node.right.left;
                    node.right = node.right.right;
                } else {
                    node.data = node.left.data;
                    node.left = node.left.left;
                    node.right = node.left.right;
                }

                // else if the left is null, move up our left
            } else if (node.left == null) {

                if (node.parent.right == node) {
                    node.parent.right = node.right;
                } else {
                    node.parent.left = node.right;
                }
                return;

                // else if the right is null, move up our right
            } else if (node.right == null) {
                if (node.parent.right == node) {
                    node.parent.right = node.left;
                } else {
                    node.parent.left = node.left;
                }
                return;
            }
        }

        // recurse to find data requested to delete
        delete(data, node.left);
        delete(data, node.right);

    }

    /**
     * Fills in in-order successors of each node.
     * 
     * @param rootNode The root node (any binary tree object that has been created
     *                 is a root node, as it extends Node).
     */
    private void fillInOrderSuccessors(BinaryTree rootNode) {

        // get the list of nodes in the tree in in-order order
        List<Node> inOrder = rootNode.inOrderTraversal();

        // iterate over them and set their in order successor attribute
        for (int i = 0; i < inOrder.size() - 1; i++) {
            inOrder.get(i).inOrderSuccessor = inOrder.get(i + 1);
        }
    }

}

/**
 * Node class for tree, knows its left, right, level, parent, and
 * in-order successor, and has a value
 */
class Node {
    Node left;
    Node right;
    int level;
    Integer data;
    Node parent;
    Node inOrderSuccessor;

    /**
     * Constructor method creates a Node and sets its initial values.
     * 
     * @param data
     */
    Node(int data) {
        this.left = null;
        this.right = null;
        this.parent = null;
        this.data = data;
        this.inOrderSuccessor = null;
    }

}

/**
 * interface to implement insert method
 */
interface BinaryTreeInterface {

    /**
     * Method to be implemented, to insert data into the binary tree.
     * 
     * @param data Data to be inserted into the tree.
     */
    public void insert(int data);
}
