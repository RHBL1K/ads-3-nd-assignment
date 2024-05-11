import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {

    static class Node<T> {
        T item;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;


    // checks for if the element index within the range of size
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid Index: " + index + "or " +" Size: " + size);
    }

    // checks for if it is empty
    private void isEmpty(){
        if(head==null){
            System.out.println("this list is empty");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;  // starts at the head of the list

            // checks if the list has the next element
            @Override
            public boolean hasNext() {
                return current != null;
            }

            // moves the iterator forward one step and returns the current element
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("No more elements in the list");
                T item = current.item;  // returns retrieved the item
                current = current.next;  // move to the next node
                return item;
            }
        };
    }


    // sets the input element with the given index
    @Override
    public Object set(int index, T item){
        checkElementIndex(index);
        Node<T> current = head;
        for(int i = 0; i< index; i++){
            current = current.next;
        }
        T res = current.item;
        current.item = item;
        return res;
    }

    // adds new element as tail in the linked list
    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }


    // 1 - > 2 - > 3 - > 4 - > 5 - > null

    // int index = 4;
    // T item = 'new';

    // 1 -> 2 -> 3 -> 4 -> 'new' -> 5 -> null

    @Override
    public void add(int index, T item) {
        checkElementIndex(index);

        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            // this new node should point to the node that was originally next in the list from our 'current' node.
            Node<T> newNode = new Node<>(item, current.next);
            // and now 'current' node's next pointer to point to the new node
            current.next = newNode;
            size++;
        }
    }


    // used to add the first element to the list
    @Override
    public void addFirst(T item){
        Node<T> newNode = new Node<>(item, null);
        if( head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }



    // used to add the last element to the list by iterating from the start ( head ) and goes til the last element, and at the end adds the need element
    @Override
    public void addLast(T item){
        Node<T> newNode = new Node<>(item, null);
        if( head == null){
            head = newNode;
            return;
        }
        Node<T> current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
    }



    // used to obtain the needed item by it is given index
    @Override
    public T get(int index){
        checkElementIndex(index);
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.item;
    }


    // used to obtain the first element of the array
    @Override
    public T getFirst(){
        isEmpty();
        return head.item;
    }

    // used to obtain the last element of the array
    @Override
    public T getLast(){
        isEmpty();
        return tail.item;

    }




    // to remove the element with the index 3 here we have to iterate the 'current' before the index
    // 1 -> 2 -> 3 ( have to be removed ) -> 4 -> 5 -> null
    // current.next = current.next.next; || 2 refers to the element after next which is 4 || 2 - > 4
    // 1 -> 2 -> 4 -> 5 -> null
    @Override
    public void remove(int index){
        checkElementIndex(index);
        if (index == 0) {  // removes first element
            removeFirst();
            return;
        }
        if (index == size - 1) {  // removes last element
            removeLast();
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    // 1 -> 2 -> 3 -> 4 -> 5 -> null

    // now by referring the head to the next element we get rid of the first element

    // 2 -> 3 -> 4 -> 5 -> null
    @Override
    public void removeFirst(){
        isEmpty();
        head=head.next;
    }


    // 1 -> 2 -> 3 -> 4 -> null
    // here the last element is four and to delete this element here first have to be checked is everything okay with the size of the linked list
    // if yes it continues and takes the next step which is the current iterator goes before the last element and stops here
    // so in the given example it stops on the element 3 and turns the next element to null  <4 == null>, and the tail is now current element which is 3
    @Override
    public void removeLast(){
        isEmpty();
        if (size == 1) {
            head = tail = null;
            size--;
            return;
        }
        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        current.next = null;  // the last element is removed
        tail = current;       // now tail is the last new node
        size--;

    }


    // Sorting by using swapping
    @Override
    public void sort() {
        if (head == null || head.next == null) {
            return; // the list is empty or has only one element, so no need to sort
        }

        for (int i = 0; i < size; i++) {
            Node<T> current = head;
            for (int j = 0; j < size - 1; j++) {
                Comparable<T> currentComparable = (Comparable<T>) current.item;
                if (currentComparable.compareTo(current.next.item) > 0) {
                    T temp = current.item;
                    current.item = current.next.item;
                    current.next.item = temp;
                }
                current = current.next; // moves to the next node
            }
        }
    }


    // current index of object
    @Override
    public int indexOf(Object object) {
        Node<T> current = head;
        for (int i = 0; current != null; i++, current = current.next) {
            if (object == current.item) {
                return i;
            }
        }
        return -1;
    }


    // check out when the last time the need object is occurred in the list
    @Override
    public int lastIndexOf(Object object) {
        Node<T> current = head;
        int lastIndex = -1;
        for (int i = 0; current != null; current = current.next, i++) {
            if (object == current.item) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }


    // checks if any node contains the item that matches the given object based on equality
    @Override
    public boolean exists(Object object) {
        Node<T> current = head;
        while (current != null) {
            if (object == null && current.item == null) {
                return true;
            } else if (object != null && object.equals(current.item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // this method is used to convert the entire list into an array of Object elements
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        Node<T> current = head;

        while (current != null) {
            array[i++] = current.item;
            current = current.next;
        }
        return array;
    }

    // used to clear the array
    @Override
    public void clear(){
        head = tail = null;
        size = 0;
    }

    // used to find out the size of the array
    @Override
    public int size(){
        return size;
    }


}