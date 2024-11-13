# Custom-Binary-Tree
Methods to create a binary tree, insert data into the tree, pre-order, in-order, and post-order traversals, breadth first traversal, and deleting nodes

## Create a Binary Tree
BinaryTree binaryTree = new BinaryTree(15);

## Insert into the tree
binaryTree.insert(10);

## Delete from the tree
binaryTree.delete(15);

## In order traversal
List<Node> inOrderNodes = binaryTree.inOrderTraversal();

# Pre order traversal
List<Node> preOrderNodes = binaryTree.preOrderTraversal();

# Post order traversal
List<Node> postOrderNodes = binaryTree.postOrderTraversal();

# Breadth first traversal
List<Node> breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();

