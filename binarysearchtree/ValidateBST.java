package binarysearchtree;

// Validate if given binary tree is a BST
public class ValidateBST {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int v) {
            data = v;
            left = right = null;
        }
    }

    public static void main (String args[]) {
        Node root = new Node(9);
        root.left = new Node(8);
        root.right = new Node(7);

        root.left.left = new Node(6);
        root.left.right = new Node(5);
        root.right.left = new Node(4);
        root.right.right = new Node(3);

        root.left.left.left = new Node(2);
        root.left.left.right = new Node(1);

        System.out.print("is valid BST " + isValidBST(root.data, root));
    }

    /**
     * using recursion
     * @return
     */
    public static boolean isValidBST(int rootValue, Node root) {
        boolean isValid = false;
        if (root == null) {
             isValid= true;
        }

        if (root.left != null && root.left.data < root.data && root.data < rootValue) {
             isValid = true;
        }

        if (root.right != null && root.right.data > root.data && root.data > rootValue) {
             isValid = true;
        }
        return isValid;
    }

    public static boolean validateBST(int rootValue, Node root) {
        while (root.left != null) {
            boolean left = isValidBST(rootValue, root.left);
        }
        boolean right = isValidBST(rootValue, root.right);

//        if(left && right) {
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }
}
