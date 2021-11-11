import java.util.ListIterator;

public class Test {
    public static void main(String[] args) {
        testArrayList();
        testBinarySearchTree();
        testHeap();
    }

    private static void testBinarySearchTree() {
        // Testing the class BST
        BST<String> bst = new BST<>();
        bst.add("Kiwi");
        bst.add("Strawberry");
        bst.add("Apple");
        bst.add("Banana");
        bst.add("Orange");
        bst.add("Lemon");
        bst.add("Watermelon");
        bst.inorder();
        bst.remove("Banana");
        System.out.println(bst.contains("Banana"));
        bst.inorder();
        bst.remove("Orange");
        bst.inorder();
        bst.remove("Kiwi");
        bst.inorder();
    }

    private static void testArrayList() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Apple");
        arr.add("Banana");
        arr.add("Kiwi");
        arr.add("Lemon");
        arr.add("Orange");
        arr.add("Strawberry");
        arr.add("Watermelon");
        System.out.println("ArrayList: " + arr.toString());
        System.out.println("Removed: " + arr.remove(0));
        System.out.println("ArrayList: " + arr.toString());
        System.out.println("ArrayList contains Pear?: " + arr.contains("Pear"));
    }

    private static void testHeap() {
        Heap<String> heap = new Heap<>();
        heap.add("Apple");
        heap.add("Banana");
        heap.add("Kiwi");
        heap.add("Lemon");
        heap.add("Orange");
        heap.add("Strawberry");
        heap.add("Watermelon");
        System.out.println("Heap: " + heap.toString());
        System.out.println("Removed: " + heap.remove());
        System.out.println("Heap: " + heap.toString());
        System.out.println("Heap contains Pear?: " + heap.contains("Pear"));
    }
}
