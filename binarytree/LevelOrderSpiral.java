package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Level Order Traversal in Spiral form
 *     1
 *    / \
 *   2   3
 *  / \ / \
 * 4  5 6  7
 *
 * level Order Spiral traversal --> 1, 3, 2, 4, 5, 6, 7
 *
 * whats the traversal sequence root--left right -- right left ---left right ---right left and so on.
 * 4 approaches
 * 1. recursion
 * 2. stack
 * 3. dequeue
 * 4. queue and stack
 */
public class LevelOrderSpiral {

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
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        int height = height(root);
        boolean leftToRight = true;

        System.out.println("using recursion");
        for(int l=0; l<height; l++) {
            printGivenLevel(root, l, leftToRight);
            leftToRight = !leftToRight;
        }
        System.out.println();
        usingStack(root);

        System.out.println();
        usingDequeue(root);
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }
    }

    /**
     * this method uses recursion
     * @param root
     * @param level
     * @param leftToRight
     */
    public static void printGivenLevel(Node root, int level, boolean leftToRight) {
        if(root == null) {
            return;
        }
        if(level ==0) {
            System.out.print(root.data+"--");
        } else {
            if(leftToRight) {
                printGivenLevel(root.left, level-1, leftToRight);
                printGivenLevel(root.right, level-1, leftToRight);
            } else {
                printGivenLevel(root.right, level-1, leftToRight);
                printGivenLevel(root.left, level-1, leftToRight);
            }
        }
    }

    /**
     * using two stack (LIFO)
     * push the root node to stack1 and print the data (left right)
     * in stack 2 push left child followed by right.
     * from stack 2 peek the element (right left) and push right followed by left in stack 1
     */
    public static void usingStack(Node root) {
        System.out.println("using stack");
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        if(root == null) {
            return;
        }
        // push first node
        stack1.push(root);

        while (!stack1.empty() || !stack2.empty()) {
            while(!stack1.empty()) {
                Node temp = stack1.peek();
                stack1.pop();

                System.out.print(temp.data+"--");

                if(temp.left != null) {
                    stack2.push(temp.left);
                }

                if(temp.right != null) {
                    stack2.push(temp.right);
                }
            }
            while (!stack2.empty()) {
                Node temp2 = stack2.peek();
                stack2.pop();

                System.out.print(temp2.data+"--");

                if(temp2.right != null) {
                    stack1.push(temp2.right);
                }

                if(temp2.left != null) {
                    stack1.push(temp2.left);
                }
            }
        }
    }

    /**
     * using dequeue (doubly ended queue)
     * for one operation peek the elements from right
     * for another peek the elements from left
     *
     * offerLast - add at the end of the deque
     * offerFirst - add at the front of the queue
     * pollFirst - reads the first element of the queue. removes the element
     * pollLast - reads the last element of the queue. removes the element
     * peekFirst - peek first element of the dequeue. does not remove
     * peekLast - peek last element of the dequeue. does not remove
     */
    public static void usingDequeue(Node root) {
        System.out.println("using dequeue");
        if(root == null) {
            return;
        }
        Deque<Node> dq = new ArrayDeque<>();
        dq.offerLast(root);
        boolean reverse = false;

        while(!dq.isEmpty()) {
            // get the size of the queue so that we can iterate
            int size = dq.size();

                //status of flog. if reverse = true -- add right followed by left
                // read using poll first
                if(reverse) {
                    for(int i=0; i<size; i++) {
                        Node node = dq.pollLast();
                        System.out.print(node.data+"--");

                        if (node.right != null) {
                            dq.offerFirst(node.right);
                        }
                        if (node.left != null) {
                            dq.offerFirst(node.left);
                        }
                    }
                    reverse = !reverse;
                } else {
                    for(int i=0; i<size; i++) {
                        Node node = dq.pollFirst();
                        System.out.print(node.data+"--");
                        if (node.left != null) {
                            dq.offerLast(node.left);
                        }
                        if (node.right != null) {
                            dq.offerLast(node.right);
                        }
                    }
                    reverse = !reverse;
                }
            }
        }
    }
