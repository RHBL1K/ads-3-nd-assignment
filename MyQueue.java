public class MyQueue<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    // adds item to the end of the list
    public T enqueue(T item) {
        list.addLast(item);
        return item;
    }

    // removes the first item of the list, which is the front of the queue

    public T dequeue(){
        T removingItem = peek();
        list.removeFirst();
        return removingItem;
    }


    // gets the first item of the list, which is the front of the queue

    public T peek() {
        if (isEmpty()) {
            return null; // or throw exception
        }
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
    public int size() {
        return list.size();
    }
}