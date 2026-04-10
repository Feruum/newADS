public class MyStack<T> {
    private final MyArrayList<T> list = new MyArrayList<>();
    public void push(T item) {
        list.addLast(item);
    }
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        T top = list.getLast();
        list.removeLast();
        return top;
    }
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return list.getLast();
    }
    public boolean isEmpty() {
        return list.size() == 0;
    }
    public int size() {
        return list.size();
    }
    @Override
    public String toString() {
        return "Stack(top->)" + list.toString();
    }
}