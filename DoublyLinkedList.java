import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class DoublyLinkedList repersents a LinkedList with nodes containing both a previous and next value. Allows for navigation in both directions.
 * @author Andrew Pearce
 * @verison 0.1
 */
public class DoublyLinkedList<E> implements List<E>{
    private Node head, tail;
    private int size;
    /**
     * Private inner class node to model a doubly linked node
     */
    private class Node {
		E value;
		Node next;
        Node previous;
        /**
         * <p>Constructor for Node class including one parameter for the value of the current node.</p><p>Also includes a null next node value and a null previous node value</p>
         * @param initialValue for the value of this node
         */
		Node(E initialValue) {
			value = initialValue;
			next = null;
            previous = null;
		}
        /**
         * <p>Constructor for Node class including one parameter for the value of the current node. Includes a parameter for the previous node.</p><p>Also includes a null next node value.</p>
         * @param initialValue for the value of this node
         * @param previous for the previous node to this one
         */
		Node(E initialValue, Node previous) {
			value = initialValue;
			next = null;
            this.previous = previous;
		}
	}
    /**
     * Constructor for the DoublyLinkedList class. Initilizes head and tail as null, with a size of 0 to repersent the number of elements in the class.
     */
    public DoublyLinkedList(){
        head=tail=null;
        size=0;
    }
    /**
     * Adds item as the head of all elements in the list. O(1)
     * @param item the value for the node to be placed at the beginning
     * @return whether or not the node was added successfully
     */
    public boolean addFirst(E item){
        Node newNode = new Node(item);//node to be added to the list
        if(head==null){//first node to be in the list
            head = tail = newNode;
        }
        else{//not the first node to be inserted
            newNode.next = head;//set the head to be referenced as the next after
            head = newNode;//set the referenced head by the class to be the newNode
            newNode.next.previous=newNode;//set the previously know head node to be the node referenced after the new head
        }
        size++;
        return true;
    }
    /**
     * Adds a item of type E to the end of the linked list. O(1)
     * @param item of type E to be added to the end
     * @return true if function was completed
     */
    public boolean addLast(E item){
        Node newNode = new Node(item);
        if(head==null){
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
        return true;
    }
    /**
     * Adds a item of type E to the start of the linked list. O(1)
     */
    public boolean add(E item){
        return addFirst(item);
    }
    /**
     * Returns the value of the first node of the LinkedList. O(1)
     * @return value of type E of the first node
     */
    public E getFirst(){
        if(head == null){
            throw new NoSuchElementException();
        }
        return head.value;
    }
    /**
     * Returns the value of the last node of the LinkedList. O(1)
     * @return value of type E of the last node
     */
    public E getLast(){
        if(head == null){
            throw new NoSuchElementException();
        }
        return tail.value;
    }
    /**
     * Removes the first element of the linked list. O(1)
     * @return if the first element was successfully removed
     */
    public boolean removeFirst(){
        if(head==null){
            throw new NoSuchElementException();
        }
        head = head.next;
        head.previous = null;
        if(head==null){
            tail = null;
        }
        size--;
        return true;
    }
    /**
     * Removes the last element of the linked list. O(1)
     * @return if the last element was successfully removed.
     */
    public boolean removeLast(){
        if(head==null){//empty
            throw new NoSuchElementException();
        }
        if(size==1){//only one element, head == tail
            return removeFirst();
        }
        tail = tail.previous;
        tail.next = null;
        size--;
        return true;
    }
    /**
     * toString method to return the values contained in the linked list. O(n)
     */
    public String toString(){
        String output = "[";
		Node node = head;
		while (node != null) {
			output += node.value + " ";
			node = node.next;
		}
		output += "]";
		return output;
    }
    /**
     * Clears the list, sets head, tail to null and size to 0. O(1)
     */
    public void clear(){
        head = tail = null;
        size=0;
    }
    /**
     * Method to determine if the list is empty O(1)
     * @return whether or not the list is empty
     */
    public boolean isEmpty(){
        return (size == 0);
    }
    /**
     * Getter for the size of the list. O(1)
     */
    public int size(){
        return size;
    }
    /**
     * Getter for iterator repersenting the DoublyLinkedList O(1) 
     * @return the iterator repersenting the list
     */
    public Iterator<E> iterator(){
        return new DoublyLinkedListListIterator();
    }
    /**
     * Private class for the iterator of the doubly linked list
     */
    private class DoublyLinkedListListIterator implements ListIterator<E>{
        private Node current = head;
        /**
         * Returns <code>true</code> if there is a next node O(1)
         */
        public boolean hasNext(){
            return (current!=null);
        }
        /**
         * Returns the next value in the LinkedList. Increments the iterator fowards. O(1)
         */
        public E next(){
            if(current==null){
                throw new NoSuchElementException();
            }
            E value = current.value;
            current = current.next;
            return value;
        }
        /**
         *  Returns <code>true</code> if the function had a previous node. O(1)
         */
        public boolean hasPrevious(){
            return current.previous!=null;
        }
        /**
         * Returns previous and navigates iterator one back. O(1)
         */
        public E previous(){
            if(current==head){
                return null;
            }
            else{
                E value = current.previous.value;
                current = current.previous;
                return value;
            }
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
            
        }
        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
            
        }
        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
            
        }
        /**
         * Returns the index of an item in the array. O(log(n)) 
         * @param item to be searched for
         * @return the index of the searched for item
         */
        public int contains(E item){
            Iterator<E> iter = iterator();
            int iterations = 0;
            while(iter.hasNext()){
                iterations++;
                if(item.equals(iter.next())){
                    return iterations;
                }
            }
            return -1;
        }
        /**
         * Removes the node from the list. O(log(n))
         * @param item to be removed
         * @return index of the item, -1 if it doesn't exist in the node
         */
        public int remove(E item){
            Node current = head;
            int iterations=0;
            while(current!=null){
                iterations++;
                if(current.value.equals(item)){
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    size--;
                    return iterations;
                }
            }
            return -1;
        }
        public int add(int index, E item){//O(log(n))
            if(index < 0 || index > size) {
                throw new ArrayIndexOutOfBoundsException();	
            }
            if(index == 0) {
                addFirst(item);
                return 0;
            }
            if(index == size) {
                addLast(item);
                return 0;
            }
            int iterations = 0;
            Node current = head;
            while(iterations<index-1){
                iterations++;
                current = current.next;
            }
            Node prev = current.previous;
            Node newNode = new Node(item);
            prev.next = newNode;
            newNode.previous = prev;
            current.previous = newNode;
            newNode.next = current;
            size++;
            return iterations;
        }
    }
    public ListIterator<E> listIterator(){//O(1)
        return new DoublyLinkedListListIterator();
    }
    public ListIterator<E> listIterator(int index){//O(log(n))
        DoublyLinkedListListIterator itLL = new DoublyLinkedListListIterator();
        if(index==size()){
            index--;
        }
        for(int i=0; i<index; i++){
            itLL.next();
        }
        return itLL;
    }
}
