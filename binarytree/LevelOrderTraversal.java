package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Also known as Breadth First Traversal
 */
public class LevelOrderTraversal {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int v) {
            data = v;
            left = right = null;
        }
    }

    static class BinaryTree {
        Node root;
        public BinaryTree() {
            root = null;
        }
    }


    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        int height = height(tree.root);
        System.out.println("height of the tree "+height);
        for(int l=0; l<height; l++) {
            recursiveLevelOrder(tree.root, l);
        }

        System.out.println("Iterative level order traversal");
        iterativeLevelOrderTraversal(tree.root);

    }

    /**
     * recursive node at each level
     * l = level
     * T.C = ?? for best and worst case
     */
    public static void recursiveLevelOrder(Node root, int l) {
        // base case
        if(root == null) {
            return;
        }
        // if l==0 - its root node
        if( l==0) {
            System.out.print(root.data+"--");
        } else {
            recursiveLevelOrder(root.left, l-1);
            recursiveLevelOrder(root.right,l-1);
        }
    }
    /**
     * get the height of the tree
     * @param root
     * @return
     */
    public static int height(Node root) {
        //base case if node == null
        if(root == null) {
            return 0;
        } else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if(leftHeight > rightHeight) {
                return leftHeight +1;
            } else {
                return rightHeight +1;
            }
        }
    }

    /**
     * Using queue
     * traverse the tree in O(n) complexity. think how?
     * if i traverse the tree once and put all the elements in queue for that level then i am visiting all the nodes
     * only once
     *
     * T.C = O(n) as i am visiting every node only once.
     */
    public static void iterativeLevelOrderTraversal(Node root) {
        if(root == null) {
            return;
        }
        Queue<Node> treeNode = new LinkedList<>();
        treeNode.add(root);

        while (! treeNode.isEmpty()) {
            Node currentNode = treeNode.poll();
            System.out.print(currentNode.data+"--");
            if(currentNode.left != null) {
                treeNode.add(currentNode.left);
            }
            if(currentNode.right != null) {
                treeNode.add(currentNode.right);
            }
        }
    }
}
