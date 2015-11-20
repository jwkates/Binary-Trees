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
    public void BinaryTree() {
        root = null;
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
        }

        return lookup(node.left, data) || lookup(node.right, data);
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
            return 0;
        }

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
            return 0;
        }
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
        return( minValue(root) );
    }

    /**
     Finds the min value in a non-empty binary search tree.
     */
    private int minValue(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node.data;
    }


    public void printTree() {
        printTree(root);
        System.out.println();
    }

    private void printTree(Node node) {

    }

    private void build123a() {
        Node b = new Node(1);
        Node c = new Node(3);
        Node a = new Node(2);

        a.left = b;
        a.right = c;
    }

    private void build123b() {
        Node root = new Node();
        root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(2);
    }

    public static void main(String[] args) {
        Node b = new Node(1);
        Node c = new Node(3);
        Node a = new Node(2);
        Node d = new Node(4);
        b.right = d;

        a.left = b;
        a.right = c;

        BinaryTree tree = new BinaryTree();
        System.out.println(tree.minValue(a));
    }

}