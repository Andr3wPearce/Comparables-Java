import java.util.Iterator;
import java.util.ListIterator;
public class ArrayList<E> implements List<E>{
    
	// data members
	private E[] elements;
	private int size;

	// Constructors
	public ArrayList() { // O(1)
		elements = (E[]) new Object[10];
		size = 0;
	}

	public ArrayList(int capacity) { // O(1)
		elements = (E[]) new Object[capacity];
		size = 0;
	}
	/*
	public boolean add(int index, E item) { // O(n)
		if (index > size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		ensureCapacity(); // O(n)
		for (int i = size-1; i>= index; i--) { // O(n)
			elements[i + 1] = elements[i];
		}
		elements[index] = item;
		size++;
		return true;
	}
	*/
	public int add(int index, E item) { // O(n)
		int iterations = 0;
		if (index > size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		iterations = ensureCapacity(); // O(n)
		for (int i = size-1; i>= index; i--) { // O(n)
			iterations++;
			elements[i + 1] = elements[i];
		}
		elements[index] = item;
		size++;
		return iterations;
	}
	// Getter and Setter
	public E get(int index) { // O(1)
		checkIndex(index);
		return elements[index];
	}

	public E set(int index, E newItem) { // O(1)
		checkIndex(index);
		E oldItem = elements[index];
		elements[index] = newItem;
		return oldItem;
	}

	// Size of the list
	public int size() {//O(1)
		return size;
	}

	// Clear the list
	public void clear() {//O(1)
		size = 0;
	}

	// Check if the list is empty
	public boolean isEmpty() {//O(1)
		return (size == 0);
	}
	/*
	// Removing an object from the list
	public boolean remove(Object o) { // O(n)
		E item = (E) o;
		for (int i = 0; i < size; i++) { // O(n)
			if (elements[i].equals(item)) {
				remove(i); // O(n)
				return true;
			}
		}
		return false;
	}
	 */
	// Removing the item at index from the list
	public E remove(int index) { // O(n)
	     checkIndex(index);
	     E item = elements[index];
	     for(int i=index; i<size-1; i++) { // O(n)
				elements[i] = elements[i+1];
	     }
	     size--;
	     return item;
	}

	// Shrink the list to size
	public void trimToSize() { // O(n)
		if (size != elements.length) {
			E[] newElements = (E[]) new Object[size];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
		}
	}

	// Grow the list if needed
	private int ensureCapacity() { // O(n)
		int iterations=0;
		if (size >= elements.length) {
			int newCap = (int) (elements.length * 1.5);
			E[] newElements = (E[]) new Object[newCap];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[i];
				iterations++;
			}
			elements = newElements;
		}
		return iterations;
	}
	
	// Check if the index is valid
	private void checkIndex(int index) { // O(1)
		if (index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException("Index out of bounds. Must be between 0 and " + (size - 1));
	}

	// toString() method
	public String toString() {// O(n)
		String output = "[";
		for (int i = 0; i < size - 1; i++) {
			output += elements[i] + ", ";
		}
		output += elements[size - 1] + "]";
		return output;
	}
	// Iterator for the list
	public Iterator<E> iterator(){
			  return new ArrayListIterator();
	}
	// Inner class that implements Iterator<E>
	private class ArrayListIterator implements ListIterator<E>{
		private int current = -1;
		public boolean hasNext() { 
			return current < size-1; 
		}

		public E next() { 
			return elements[++current]; 
		}
		@Override
		public boolean hasPrevious() {
					return ((current-1)>=0&&(current-1)<elements.length);
		}
		@Override
		public E previous() {
			if(hasPrevious()){
				return elements[--current];
			}
			return null;
		}
		@Override
		public int nextIndex() {
			return current+1;
		}
		@Override
		public int previousIndex() {
			return current+1;
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
	}
	public int contains(E item) {
		Iterator<E> iter = iterator();
		int iterations = 0;
		while(iter.hasNext()) {
			iterations++;
			if(item.equals(iter.next())) {
				return iterations;
			}
		}
		return iterations;
	}
	public int remove(E item) {
		int iterations = 0;
		for(int i=0; i<size; i++) {// searching
			iterations++;
			if(elements[i].equals(item)) {
				for(int j=i; j<size-1; j++) {// shifting
					iterations++;
					elements[j] = elements[j+1];
				}
				size--;
				return iterations;
			}
		}
		return iterations;
	}

    @Override
    public boolean add(E item) {
        if(add(size, item)>1){
            return true;
        }
        return false;
    }

    @Override
    public ListIterator<E> listIterator() {
        ListIterator<E> returner = new ArrayListIterator();
        return returner;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
		if(index>=0&&index<size()){//if index is in bounds
			ArrayListIterator arrLI = new ArrayListIterator();
			arrLI.current = index-1;
			return arrLI;
		}
		if(index==size()){//if index is equal to the size of the array
			ArrayListIterator arrLI = new ArrayListIterator();
			arrLI.current = index-2;
		}
        return null;//if index is not in bounds
    }
}