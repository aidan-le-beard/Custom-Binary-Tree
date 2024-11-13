# Custom-Binary-Tree
Methods to create a binary tree, insert data into the tree, pre-order, in-order, and post-order traversals, breadth first traversal, and deleting nodes

## Create a Binary Tree
// creates a binary tree with root node of 15

BinaryTree binaryTree = new BinaryTree(15);

## Insert into the tree
// inserts 10 into the tree, if 10 isn't already in the tree

binaryTree.insert(10);

## Delete from the tree
// deletes 15 from the tree, if 15 is in the tree

binaryTree.delete(15);

## In order traversal
// returns a list of nodes in in-order order

List\<Node\> inOrderNodes = binaryTree.inOrderTraversal();

# Pre order traversal
// returns a list of nodes in pre-order order

List\<Node\> preOrderNodes = binaryTree.preOrderTraversal();

# Post order traversal
// returns a list of nodes in post-order order

List\<Node\> postOrderNodes = binaryTree.postOrderTraversal();

# Breadth first traversal
// returns a list of nodes in breadth first traversal order

List\<Node\> breadthFirstOrderNodes = binaryTree.breadthFirstTraversal();

