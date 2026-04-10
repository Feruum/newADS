import java.util.Comparator;
import java.util.Iterator;
public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;
    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];
            for (int i = 0; i < size; i++) newData[i] = data[i];
            data = newData;
        }
    }
    @Override
    public void add(T item) {
        ensureCapacity();
        data[size++] = item;
    }
    @Override
    public void set(int index, T item) {
        checkIndex(index);
        data[index] = item;
    }
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensureCapacity();
        for (int i = size; i > index; i--) data[i] = data[i - 1];
        data[index] = item;
        size++;
    }
    @Override
    public void addFirst(T item) {
        add(0, item);
    }
    @Override
    public void addLast(T item) {
        add(item);
    }
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }
    @Override
    public T getFirst() {
        if (size == 0) throw new RuntimeException("List is empty");
        return get(0);
    }
    @Override
    public T getLast() {
        if (size == 0) throw new RuntimeException("List is empty");
        return get(size - 1);
    }
    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) data[i] = data[i + 1];
        data[--size] = null; 
    }
    @Override
    public void removeFirst() {
        if (size == 0) throw new RuntimeException("List is empty");
        remove(0);
    }
    @Override
    public void removeLast() {
        if (size == 0) throw new RuntimeException("List is empty");
        remove(size - 1);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void sort(Comparator<T> cmp) {
        for (int i = 1; i < size; i++) {
            T key = (T) data[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare((T) data[j], key) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null ? data[i] == null : object.equals(data[i])) return i;
        }
        return -1;
    }
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object == null ? data[i] == null : object.equals(data[i])) return i;
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
        for (int i = 0; i < size; i++) result[i] = data[i];
        return result;
    }
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) data[i] = null;
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < size;
            }
            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) throw new RuntimeException("No more elements");
                return (T) data[cursor++];
            }
        };
    }
    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}