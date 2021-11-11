import java.util.ListIterator;

interface List<E>{
    /**
     * Adds element of type E to the list
     * @param item the item to be added to the list
     * @return whether or not the item was added successfully
     */
    abstract boolean add(E item);
    /**
     * Getter for an int repersenting the amount of elements contained in the list
     * @return the amount of elements in the list
     */
    abstract int size();
    /**
     * Getter for iterator to navigate the list
     * @return object of type ListIterator repersenting the list
     */
    abstract ListIterator<E> listIterator();
    /**
     * Getter for iterator to navigate the list onwards from a certain element
     * @param index for the starting index 
     * @return object of type ListIterator repersenting the list onwards from an index
     */
    abstract ListIterator<E> listIterator(int index);
}