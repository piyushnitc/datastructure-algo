package binarytree;

public class DFSTraversal {

    static class Node {
        int data; // considering the data is number
        Node left;
        Node right;

        Node(int v) {
            data = v;
            left = right=null;
        }
    }

    public static void main (String args[]) {
        //Initialize binary tree with the values
        Node root = new Node(1);
        Node rl = root.left = new Node(2);
        Node rr = root.right = new Node(6);
        Node r1l = rl.left = new Node(3);
        Node r1r = rl.right = new Node(4);
        r1r.left = new Node(5);
        rr.left = new Node(7);
        rr.right = new Node(9);
        rr.left.left = new Node(8);

        System.out.println("Preorder Traversal");
        printPreorder(root);

        System.out.println("Inorder Traversal");
        printInorder(root);

        System.out.println("Postorder Traversal");
        printPostorder(root);
    }

    /**
     * Time complexity O(N) -- need to visit all the nodes .. N is the number of nodes
     * space O(1) -- if you do not consider the recursion stack, if you consider then see below
     * space O(h) --recursion stack h --- height of the tree
     * in skewed tree--- h = N
     * in complete tree h = logN
     *
     * left-root-right
     */
    public static void printInorder(Node node) {
        // base case when node = null
        if(node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.data+"--");
        printInorder(node.right);

    }

    /**
     * Root-left-Right
     * @param node
     */
    public static void printPreorder(Node node) {
        // base case when node = null
        if(node == null) {
            return;
        }
        System.out.print(node.data+"--");
        printPreorder(node.left);
        printPreorder(node.right);

    }

    /**
     * left-right-root
     * @param node
     */
    public static void printPostorder(Node node) {
        // base case when node = null
        if(node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.data+"--");
    }

    /**
     * prove the recursive relation
     *
     * lets say there are n no of total nodes.. k in left subtree, 1 at root, n-k-1 in right subtree
     * T(n) = T.C in left + T.C at root (o(1)) + T.C in right
     *  T(n) = T(k)+T(n-k-1) + c
     *
     *  for skewed binary tree k = 0;
     *  T(n) = T(n-1) + c -- solve this recurrence relation you will get O(n)
     *
     *  for complete binary tree k = (n-1)/2
     *  T(n) = T(n-1/2)+T(n-1/2)+c
     *  T(n) = 2T(n-1/2) +c -- use master theorem you will get O(n)
     */
}
