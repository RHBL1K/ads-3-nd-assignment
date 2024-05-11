import java.util.Comparator;

public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;
    private Comparator<T> comparator;

    public MyMinHeap() {
        this(null);
    }

    public MyMinHeap(Comparator<T> comparator) {
        this.heap = new MyArrayList<>();
        this.comparator = comparator;
    }

    public boolean empty() {
        return heap.size() == 0;
    }

    public int size() {
        return heap.size();
    }

    public T getMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }



    public T extractMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = getMin();
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapify(0);
        return min;
    }

    public void insert(T element) {
        heap.add(element);
        traverseUp(heap.size() - 1);
    }

    private void heapify(int index) {
        int left = leftChildOf(index);
        int right = rightChildOf(index);
        int smallest = index;

        if (left < heap.size() && compare(heap.get(left), heap.get(index)) < 0) {
            smallest = left;
        }

        if (right < heap.size() && compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void traverseUp(int index) {
        while (index != 0 && compare(heap.get(index), heap.get(parentOf(index))) < 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    private int compare(T first, T second) {
        if (comparator != null) {
            return comparator.compare(first, second);
        } else {
            return first.compareTo(second);
        }
    }
}