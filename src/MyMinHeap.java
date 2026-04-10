import java.util.Comparator;
public class MyMinHeap<T> {
    private final MyArrayList<T> list = new MyArrayList<>();
    private final Comparator<T> cmp;
    @SuppressWarnings("unchecked")
    public MyMinHeap() {
        this.cmp = (a, b) -> ((Comparable<T>) a).compareTo(b);
    }
    public MyMinHeap(Comparator<T> cmp) {
        this.cmp = cmp;
    }
    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1; }
    private int right(int i)  { return 2 * i + 2; }
    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    private void heapifyUp(int i) {
        while (i > 0 && cmp.compare(list.get(i), list.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    private void heapifyDown(int i) {
        int smallest = i;
        int l = left(i);
        int r = right(i);
        if (l < list.size() && cmp.compare(list.get(l), list.get(smallest)) < 0) smallest = l;
        if (r < list.size() && cmp.compare(list.get(r), list.get(smallest)) < 0) smallest = r;
        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }
    public void insert(T item) {
        list.addLast(item);
        heapifyUp(list.size() - 1);
    }
    public T extractMin() {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        T min = list.get(0);
        T last = list.getLast();
        list.removeLast();
        if (!isEmpty()) {
            list.set(0, last);
            heapifyDown(0);
        }
        return min;
    }
    public T getMin() {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        return list.get(0);
    }
    public boolean isEmpty() {
        return list.size() == 0;
    }
    public int size() {
        return list.size();
    }
    @Override
    public String toString() {
        return "MinHeap" + list.toString();
    }
}