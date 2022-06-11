public class App {
    public static void main(String[] args) throws Exception {
        BinarySearchTree tree = new BinarySearchTree(new Node(20));
        tree.insert(9);
        tree.insert(25);
        tree.insert(5);
        tree.insert(12);
        tree.insert(11);
        tree.insert(14);
        tree.insert(9);
        tree.insert(30);
        System.out.println("Inorder search: ");
        tree.inOrderSearchForTree();
        Node result = tree.findInOrderSuccessor(tree.findNode(14));
        if (result == null) {
            System.out.println("No result returned");
        } else {
            System.out.print("FindInOrderSuccessor with a key 14 returns: " + result.getValue() + "\n");
        }
        //tree.BinaryTreePruning(9);
        System.out.println("Inorder search: ");
        tree.inOrderSearchForTree();
    }
}
