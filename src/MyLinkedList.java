import java.util.Comparator;
import java.util.Iterator;
public class MyLinkedList<T> implements MyList<T> {
    private static class MyNode<E> {
        E element;
        MyNode<E> next;
        MyNode<E> prev;
        MyNode(E element, MyNode<E> prev, MyNode<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
    private MyNode<T> head; 
    private MyNode<T> tail; 
    private int size;
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    private MyNode<T> nodeAt(int index) {
        checkIndex(index);
        MyNode<T> curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.prev;
        }
        return curr;
    }
    @Override
    public void add(T item) {
        addLast(item);
    }
    @Override
    public void set(int index, T item) {
        nodeAt(index).element = item;
    }
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if (index == 0) { addFirst(item); return; }
        if (index == size) { addLast(item); return; }
        MyNode<T> successor = nodeAt(index);
        MyNode<T> predecessor = successor.prev;
        MyNode<T> newNode = new MyNode<>(item, predecessor, successor);
        predecessor.next = newNode;
        successor.prev = newNode;
        size++;
    }
    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item, null, head);
        if (head != null) head.prev = newNode;
        else tail = newNode; 
        head = newNode;
        size++;
    }
    @Override
    public void addLast(T item) {
        MyNode<T> newNode = new MyNode<>(item, tail, null);
        if (tail != null) tail.next = newNode;
        else head = newNode; 
        tail = newNode;
        size++;
    }
    @Override
    public T get(int index) {
        return nodeAt(index).element;
    }
    @Override
    public T getFirst() {
        if (head == null) throw new RuntimeException("List is empty");
        return head.element;
    }
    @Override
    public T getLast() {
        if (tail == null) throw new RuntimeException("List is empty");
        return tail.element;
    }
    private void unlink(MyNode<T> node) {
        MyNode<T> prev = node.prev;
        MyNode<T> next = node.next;
        if (prev != null) prev.next = next; else head = next;
        if (next != null) next.prev = prev; else tail = prev;
        node.prev = null; 
        node.next = null;
        node.element = null;
        size--;
    }
    @Override
    public void remove(int index) {
        unlink(nodeAt(index));
    }
    @Override
    public void removeFirst() {
        if (head == null) throw new RuntimeException("List is empty");
        unlink(head);
    }
    @Override
    public void removeLast() {
        if (tail == null) throw new RuntimeException("List is empty");
        unlink(tail);
    }
    @Override
    public void sort(Comparator<T> cmp) {
        if (size <= 1) return;
        Object[] arr = toArray();
        for (int i = 1; i < arr.length; i++) {
            @SuppressWarnings("unchecked") T key = (T) arr[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare((T) arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        MyNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked") T val = (T) arr[i];
            curr.element = val;
            curr = curr.next;
        }
    }
    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (MyNode<T> curr = head; curr != null; curr = curr.next, index++) {
            if (object == null ? curr.element == null : object.equals(curr.element)) return index;
        }
        return -1;
    }
    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (MyNode<T> curr = tail; curr != null; curr = curr.prev, index--) {
            if (object == null ? curr.element == null : object.equals(curr.element)) return index;
        }
        return -1;
    }
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (MyNode<T> curr = head; curr != null; curr = curr.next) result[i++] = curr.element;
        return result;
    }
    @Override
    public void clear() {
        MyNode<T> curr = head;
        while (curr != null) {
            MyNode<T> next = curr.next;
            curr.element = null;
            curr.next = null;
            curr.prev = null;
            curr = next;
        }
        head = null;
        tail = null;
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public T next() {
                if (!hasNext()) throw new RuntimeException("No more elements");
                T element = current.element;
                current = current.next;
                return element;
            }
        };
    }
    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (MyNode<T> curr = head; curr != null; curr = curr.next) {
            sb.append(curr.element);
            if (curr.next != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}