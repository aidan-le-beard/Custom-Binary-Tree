import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TreeCreation3 {
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
     * constructor, call superconstructor for superclass then fill parents and level
     */
    BinaryTree(int data) {
        super(data);
        this.fillParentsAndLevel(); // fill in level and parents of root data
    }

    /**
     * Override implemented interface, calls insert on this
     */
    @Override
    public void insert(int data) {

        if (this.data == null) {
            this.data = data;
        } else {
            insert(data, this);
        }

        this.fillParentsAndLevel(); // fill in level and parents of inserted and all data
    }

    /**
     * Inserts a new node if a node with this data does not already exist in the
     * tree
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
     * Calls traverse to return in-order list of nodes
     */
    List<Node> inOrderTraversal() {
        return traverse(this, "inOrder", new ArrayList<>());
    }

    /**
     * Calls traverse to return pre-order list of nodes
     */
    List<Node> preOrderTraversal() {
        return traverse(this, "preOrder", new ArrayList<>());
    }

    /**
     * Calls traverse to return post-order list of nodes
     */
    List<Node> postOrderTraversal() {
        return traverse(this, "postOrder", new ArrayList<>());
    }

    /**
     * Returns a list of nodes in pre-order, in-order, or post-order order, based on
     * input given
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
     * Calls breadth first traversal for the root node, translates the tree map to a
     * list, and returns the list
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
     */
    private TreeMap<Integer, List<Node>> breadthFirstTraversal(Node node, TreeMap<Integer, List<Node>> nodes,
            int level) {

        // base case
        if (node == null) {
            return nodes;
        }

        // set the level
        node.level = level;

        // set this node as the children's parents, if they exist
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
     * Fills in the parents and the level of each node, by using breadth first
     * traversal
     */
    private void fillParentsAndLevel() {
        breadthFirstTraversal(this, new TreeMap<>(), 0);
    }

    /**
     * Calls delete on the root node, and then fills back in parents and levels
     * after deletion, due to node reordering
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
     * deletes node, replacing node with in-order successor
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
                    if (node.inOrderSuccessor.parent.parent.left == node) {
                        node.inOrderSuccessor.parent.parent.left = node.inOrderSuccessor;
                    } else {
                        node.inOrderSuccessor.parent.parent.right = node.inOrderSuccessor;
                    }

                    // set our left (we know it's null currently due to being in-order successor,
                    // meaning we're all the way left) as deleted node's left
                    node.inOrderSuccessor.left = node.left;
                }

                return;

                // else if the left is null, move up our left
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
     * Fills in in-order successors of each node
     */
    private void fillInOrderSuccessors(BinaryTree rootNode) {
        List<Node> inOrder = rootNode.inOrderTraversal();
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

    Node(int data) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.inOrderSuccessor = null;
    }

}

/**
 * interface to implement insert method
 */
interface BinaryTreeInterface {
    public void insert(int data);
}
