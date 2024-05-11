public class MyStack<T>{

    private MyArrayList<T> list = new MyArrayList<>();

    // pushes or add new element
    public T push(T item) {
        list.add(item);
        return item;
    }

    // Get Front Element
    public T peek(){
        if (empty()) {
            return null; // or throw exception
        }
        return list.get(0);
    }

    // Removes the head of the linked list
    public T pop(){
        if (empty()) {
            return null; // or throw exception
        }
        T removingItem = peek();
        list.removeFirst();
        return removingItem;
    }

    // Returns whether the stack is empty
    public boolean empty(){
        return list.size() == 0;
    }
    // Returns the size of the stack
    public int size() {
        return list.size();
    }
}