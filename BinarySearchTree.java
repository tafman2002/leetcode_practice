import java.util.ArrayList;


public class BinarySearchTree {
    private Node root;
    private Integer treeSize;

    public BinarySearchTree(Node root) {
        this.root = root;
        treeSize = 0;
    }

    public void inOrderSearchForTree() {
        if (this.root == null) {
            System.out.println("The tree does not have any nodes");
        } else {
            inOrderSearch(this.root);
        }
        
    }

    public void insert(int value) {
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        // Remember, in order to reference in java and not make a copy, create a object pointing to null and then set its address
        Node currentNode = null;
        currentNode = root;
        Node newVal = new Node(value);
        while (currentNode != null) {
            if (value < currentNode.getValue()) {
                if (currentNode.getLeft()== null) {
                    currentNode.setLeft(newVal);
                    currentNode.getLeft().setParent(currentNode);
                    break;
                } else {
                    currentNode = currentNode.getLeft();
                }
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(newVal);
                    currentNode.getRight().setParent(currentNode);
                    break;
                } else {
                    currentNode = currentNode.getRight();
                }
            }
        }
        treeSize++;
        System.out.println("Value " + value + " inserted!");
        return;
    }

    public Node findNode(int value) {
        Node currentNode = null;
        Node validNode = null;
        currentNode = this.root;
        while (currentNode != null) {
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeft();
            } else if (value > currentNode.getValue()) {
                currentNode = currentNode.getRight();
            } else {
                validNode = currentNode;
                System.out.println("Node found!");
                break;
            }
        }
        return validNode;
    }

/* 
Returns the same tree where every subtree (of the given tree) not containing a 1 has been removed.
Prune children of the tree recursively. The only decisions at each node are whether to prune the left child or the right child.

Complexity Analysis
    Time Complexity: O(N), where NNN is the number of nodes in the tree. We process each node once.
    Space Complexity: O(N), the recursion call stack can be as large as the height HHH of the tree. In the worst case scenario, H=NH=NH=N, when the tree is skewed.

Explanation: https://leetcode.com/problems/binary-tree-pruning/
*/
public Node BinaryTreePruning(int num) {
    return checkNumber(root,num) ? root : null;
}

// Bug
private boolean checkNumber(Node inputNode, int num) {
    if (inputNode == null) return false;
    boolean left = checkNumber(inputNode.getLeft(), num);
    boolean right = checkNumber(inputNode.getRight(), num);
    if (!left) {
        inputNode.setLeft(null);
        treeSize--;
    };
    if (!right) {
        inputNode.setRight(null);
        treeSize--;
    };

    return inputNode.getValue() == num || left || right;
}
        
    
    public Node findInOrderSuccessor(Node inputNode) {
        /*
         * Solution video: https://www.youtube.com/watch?v=jma9hFQSCDk
         * if node.right exists
         *      ill need to go down to the right
         *      check if left exists
         *          if left exists, go all the way left until null
         * i'll need to go up
         *      go to parent
         *      check which side you came from
         *      if you come from left side, return parent
         *      if you came from right side, go up again
         */

        /*
         * Key assumptions:
         * If the inputNode does not have any left or right children when it first starts, it returns the root node
         * If the inputNode does not exist or there is no InOrderSuccessor, the function returns null
         * If the inputNode is invalid, return null
         */
    /* MY approach 
        // if (inputNode == null) return null;

        // if (inputNode.getLeft() == null && inputNode.getRight() == null) {
        //     return root;
        // } 

        // if (inputNode.getRight() == null) {
        //     Node test = null;
        //     test = inputNode;
        //     while(test != null) {
        //         int temp = test.getValue();
        //         test = test.getParent();
        //         if (temp == test.getLeft().getValue()) {
        //             return test.getParent();
        //         }

        //     }
        // }
        

        // Node currentNode = inputNode.getRight();
        // Node validNode = currentNode;
        // while (currentNode != null) {
        //     if (currentNode.getValue() > inputNode.getValue() && currentNode.getValue() < validNode.getValue()) {
        //         validNode = currentNode;
        //     }
        //     currentNode = currentNode.getLeft();
        // }
        // return validNode;
        */
        /* Video Solution 
         * Time complexity:
         * The best case scenario: Height of the tree is O(log n)
         * The worst case scenario: O(n) if there is a chaining problem
         * Space complexity:
         * O(1) since we are not calling the function recursively.
        */
        if (inputNode == null) return null;

        if (inputNode.getRight() != null) {
            return getMostLeft(inputNode.getRight());
        }
        
        // 
        Node parent = inputNode.getParent();
        Node child = inputNode;
        while(parent.getRight() == child) {
            if (parent.getParent() == null) return null;
            child = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    public Node getMostLeft(Node node) {
        if (node.getLeft() == null) return node;
        
        return getMostLeft(node.getLeft());
    }

    public void inOrderSearch(Node node) {
        if (node != null) {
            inOrderSearch(node.getLeft());
            System.out.println(node.getValue());
            inOrderSearch(node.getRight());
        }
    }

    public Integer getSize() {return this.treeSize;}

}
