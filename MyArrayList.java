import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;


    // Creating MyArraylist by using DEFAILT_CAPACITY
    public MyArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    // creating MyarrayList
    public MyArrayList(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        array = (T[]) new Object[capacity];
        size = 0;
    }

    // returning the size of the array
    @Override
    public int size() {
        return size;
    }


    // checking for if the memory size is okay so the memory allocated worked well, if not it extends the size of the array
    public void checkForCapacity(){
        if(size == array.length){
            T[] newArray = (T[]) new Object[size * 2 + 1];
            //  + 1 to handle zero initial capacity
            for(int i = 0; i<size; i++){
                newArray[i] = (T) array[i];
            }
            array = newArray;
        }
    }

    // adds the element on top
    @Override

    public void add(T item){
        checkForCapacity();
        array[size] = item;
        size++;
    }

    // first element of the array by shifting all the elements to the right side and at the end add the item to the first element which at the index 0
    @Override
    public void addFirst(T item){
        checkForCapacity();
        for( int i = size; i > 0; i--){
            array[i] = array[i-1];
        }
        array[0] = item;
        size++;


    }

    // just using the same method as add()
    @Override
    public void addLast(T item){
        checkForCapacity();
        array[size] = item;
        size++;

    }

    // throws exception if there any problem with the memory
    private void throwException(int index){
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
    }


    // 1 2 3 4 5 6 ... ?null?

    // 1 2 3 4 5 ... 6 || 1 2 3 4 ... 5 6 || 1 2 3 ... 4 5 6 || 1 2 ... 3 4 5 6

    // 1 2 ... 3 4 5 6  <<-- moved to the right by checking for first of all is there exists
    // any memory space, and then shift them to the right after ensuring that everything is right

    // 1 2 6 3 4 5 6

    // at the end at index 3 there adds the number 6

    // there have to be inserted number 6 on index 3
    @Override

    public void add(int index, T item){
        throwException(index);
        checkForCapacity();
        for(int i = size; i>index; i--){
            array[i] = array[i-1];
        }
        array[index] = item;
        size++;
    }

    // 1 2 3 4 5 6 ... ?null?

    // there have to be deleted the element with index 3

    // 1 2 4 4 5 6 || 1 2 4 5 5 6 || 1 2 4 5 6

    // 1 2 4 5 6  <<-- commencing from the given index element goes to the right side until the size of the array
    // the key element is array[i] = array[i+1]
    // so by using this method the current index stores next element, and at the end it turns into null to prevent the memory leakage
    @Override
    public void remove(int index){
        throwException(index);
        for( int i = index; i<size-1; i++){
            array[i] = array[i+1];
        }
        array[size - 1] = null; // prevents the memory leak
        size--;
    }

    // 1 2 3 4 5 6 ... ?null?
    // 2 2 3 4 5 6 || 2 3 3 4 5 6 || 2 3 4 4 5 6 || 2 3 4 5 5 6 || 2 3 4 5 6 ... || 2 3 4 5 6 null ...
    // the function removeFirst works as shown above by overriding the first element with the next one and at the end converting the empty place to null

    @Override
    public void removeFirst() {
        checkEmpty();
        for (int i = 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        array[--size] = null;
    }

    // removes the last element by converting it to null
    @Override
    public void removeLast() {
        checkEmpty();
        array[--size] = null;
    }


    // obtaining need element by using index
    @Override
    public T get(int index){
        throwException(index);
        return (T) array[index];
    }

    // shows the first element of the array
    @Override
    public T getFirst(){
        checkEmpty();
        return (T) array[0];
    }

    // shows the last element of the array
    @Override
    public T getLast(){
        checkEmpty();
        return (T) array[size-1];
    }


    // clears everything and ensures that everything is null, and the size is 0
    @Override
    public void clear(){
        for( int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
    }

    // creating any type of array whether it is int or string
    @Override
    public Object[] toArray(){
        Object[] result = new Object[size];
        for( int i = 0; i< size; i++){
            result[i] = array[i];
        }
        return result;
    }

    // using iterator by importing it from import java.util.Iterator;
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indx = 0;

            // checks for if the array has the next element
            @Override
            public boolean hasNext() {
                return indx < size;
            }

            // add the element as the next one and the iterator moves forward one step

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) array[indx++];
            }
        };
    }

    // sets the input element with the given index
    @Override
    public Object set(int index, T item){
        throwException(index);
        return array[index] = item;
    }

    // checks whether a specific element object is exist in the list
    @Override
    public boolean exists(Object object) {
        for (int i = 0; i < size; i++) {
            if (array[i] == null && object == null) {
                return true;  // Both are null
            } else if (array[i] != null && array[i].equals(object)) {
                return true;  // not null and equal
            }
        }
        return false;
    }

    // Sorting array
    @Override
    public void sort() {
        T temp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (((Comparable<T>) array[j]).compareTo(array[j + 1]) > 0) { // it changes the place of elements if true. It is true when array[j] > array[j+1]
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                // Why Cast?: We cast because we want to use the compareTo() method, which is a member of the Comparable<T> interface
            }
        }
    }


    // returns the index of the first occurrence of the indicated element in this array, or -1 if this list does not exist
    @Override
    public int indexOf(Object object){
        if( object == null){
            for (int i = 0; i < size; i++)
            {
                if (array[i] == null)
                    return i;
            }
        }else
        {
            for (int i = 0; i < size; i++)
            {
                if(object.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }

    // last index of array
    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;  // return index of null element
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(array[i])) {
                    return i;  // return index of matching element
                }
            }
        }
        return -1;  // return -1 if no match is found
    }






}