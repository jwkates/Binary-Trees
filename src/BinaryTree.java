public class BinaryTree {
    private Node root;

    /*
     --Node--
     The binary tree is built using this nested node class.
     Each node stores one data element, and has left and right
     sub-tree pointer which may be null.
     The node is a "dumb" nested class -- we just use it for
     storage; it does not have any methods.
    */
    private static class Node {
        private int data;

        private Node left;
        private Node right;

        private Node() {
            this(0);
        }

        private Node(int data) {
            this.data = data;
        }
    }

    /**
     Creates an empty binary tree -- a null root pointer.
     */
    public BinaryTree() {
        root = null;
    }

    public BinaryTree(int... contents) {
        this();
        for (int i : contents) {
            insert(i);
        }
    }

    /**
     Returns true if the given target is in the binary tree.
     Uses a recursive helper.
     */
    public boolean lookup(int data) {
        return lookup(root, data);
    }

    /**
     Recursive lookup  -- given a node, recur
     down searching for the given data.
     */
    private boolean lookup(Node node, int data) {
        if (node == null) {
            return false;
        } else if (node.data == data) {
            return true;
        } else if (data < node.data) {
            return lookup(node.left, data);
        } else {
            return lookup(node.right, data);
        }
    }

    /**
     Inserts the given data into the binary tree.
     Uses a recursive helper.
     */
    public void insert(int data) {
        root = insert(root, data);
    }

    /**
     Recursive insert -- given a node pointer, recur down and
     insert the given data into the tree. Returns the new
     node pointer (the standard way to communicate
     a changed pointer back to the caller).
     */
    private Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else if (data <= node.data) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }
        return node;
    }

    private void build123a() {
        Node b = new Node(1);
        Node c = new Node(3);
        Node a = new Node(2);

        a.left = b;
        a.right = c;
        root = a;
    }

    private void build123b() {
        root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
    }

    private void build123c() {
        insert(2);
        insert(1);
        insert(3);
    }

    /**
     Returns the number of nodes in the tree.
     Uses a recursive helper that recurs
     down the tree and counts the nodes.
     */
    public int size() {
        return(size(root));
    }

    private int size(Node node) {
        if (node == null) {
            // The size of an empty tree is zero.
            return 0;
        }

        // The size of a non-empty tree is 1 plus the combined size of its subtrees.
        return 1 + size(node.left) + size(node.right);
    }

    /**
     Returns the max root-to-leaf depth of the tree.
     Uses a recursive helper that recurs down to find
     the max depth.
     */
    public int maxDepth() {
        return(maxDepth(root));
    }

    private int maxDepth(Node node) {
        if (node == null) {
            // Depth of an empty node is zero.
            return 0;
        }

        // Recursively find the depth of the subtrees.
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);

        if (leftDepth > rightDepth) {
            return 1 + leftDepth;
        } else {
            return 1 + rightDepth;
        }
    }

    /**
     Returns the min value in a non-empty binary search tree.
     Uses a helper method that iterates to the left to find
     the min value.
     */
    public int minValue() {
        return(minValue(root));
    }

    /**
     Finds the min value in a non-empty binary search tree.
     */
    private int minValue(Node node) {
        if (node.left == null) {
            return node.data;
        } else {
            return minValue(node.left);
        }
    }

    public void printPostorder() {
        printPostorder(root);
        System.out.println();
    }

    private void printPostorder(Node node) {
        if (node != null) {
            printPostorder(node.left);
            printPostorder(node.right);
            System.out.print(node.data + " ");
        }
    }

    /**
     Given a tree and a sum, returns true if there is a path from the root
     down to a leaf, such that adding up all the values along the path
     equals the given sum.
     Strategy: subtract the node value from the sum when recurring down,
     and check to see if the sum is 0 when you run out of tree.
     */

    public boolean hasPathSum(int sum) {
        return(hasPathSum(root, sum));
    }

    private boolean hasPathSum(Node node, int sum) {
        if (node == null) {
            return false;
        } else {
            sum -= node.data;
            return (sum == 0) || hasPathSum(node.left, sum) || hasPathSum(node.right, sum);
        }
    }

    /**
     Given a binary tree, prints out all of its root-to-leaf
     paths, one per line. Uses a recursive helper to do the work.
     */
    public void printPaths() {
        int[] path = new int[1000];
        printPaths(root, path, 0);
    }

    /**
     Recursive printPaths helper -- given a node, and an array containing
     the path from the root node up to but not including this node,
     prints out all the root-leaf paths.
     */
    private void printPaths(Node node, int[] path, int pathLen) {
        if (node != null) {
            // Add the current node to the path
            path[pathLen] = node.data;
            pathLen++;

            // To prevent double-printing, if this is a leaf, print now.
            if (node.left == null && node.right == null) {
                printArray(path, pathLen);
            } else {
                // If not, print each path through the left subtree and the right subtree
                printPaths(node.left, path, pathLen);
                printPaths(node.right, path, pathLen);
            }
        }
    }

    /**
     Utility that prints ints from an array on one line.
     */
    private void printArray(int[] ints, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();
    }

    public void mirror() {
        mirror(root);
    }

    private void mirror(Node node) {
        if (node == null) {
            return;
        }

        mirror(node.left);
        mirror(node.right);

        swapChildren(node);
    }

    private void swapChildren(Node node) {
        Node leftNode = node.left;
        node.left = node.right;
        node.right = leftNode;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(4, 2, 5, 1, 3);
        System.out.println(tree.minValue());
    }

}