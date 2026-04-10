public class Main {
    public static void main(String[] args) {
        testMyArrayList();
        testMyLinkedList();
        testMyStack();
        testMyQueue();
        testMyMinHeap();
    }
    static void testMyArrayList() {
        System.out.println("══ MyArrayList ══");
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(3);
        list.add(5);
        list.addFirst(1);
        list.addLast(7);
        list.add(2, 4);        
        System.out.println("After adds:       " + list);   
        System.out.println("get(2):           " + list.get(2));       
        System.out.println("getFirst:         " + list.getFirst());   
        System.out.println("getLast:          " + list.getLast());    
        list.set(0, 10);
        System.out.println("After set(0,10):  " + list);  
        list.set(0, 1); 
        list.add(3);
        System.out.println("indexOf(3):       " + list.indexOf(3));       
        System.out.println("lastIndexOf(3):   " + list.lastIndexOf(3));   
        System.out.println("exists(99):       " + list.exists(99));       
        list.removeFirst();
        list.removeLast();
        list.remove(1);
        System.out.println("After removes:    " + list);  
        list.add(2); list.add(9); list.add(1);
        list.sort(Integer::compareTo);
        System.out.println("After sort:       " + list);  
        Object[] arr = list.toArray();
        System.out.print("toArray:          [");
        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        System.out.println("]");
        System.out.println("size:             " + list.size());  
        list.clear();
        System.out.println("After clear:      " + list + " size=" + list.size());
        MyArrayList<String> words = new MyArrayList<>();
        words.add("hello"); words.add("world");
        System.out.print("Iterator:         ");
        for (String w : words) System.out.print(w + " ");
        System.out.println("\n");
    }
    static void testMyLinkedList() {
        System.out.println("══ MyLinkedList ══");
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(5);
        list.addFirst(1);
        list.addLast(7);
        list.add(2, 4);
        System.out.println("After adds:       " + list);   
        System.out.println("get(2):           " + list.get(2));
        System.out.println("getFirst:         " + list.getFirst());
        System.out.println("getLast:          " + list.getLast());
        list.set(4, 99);
        System.out.println("After set(4,99):  " + list);
        list.set(4, 7);
        list.add(3);
        System.out.println("indexOf(3):       " + list.indexOf(3));
        System.out.println("lastIndexOf(3):   " + list.lastIndexOf(3));
        System.out.println("exists(100):      " + list.exists(100));
        list.removeFirst();
        list.removeLast();
        list.remove(1);
        System.out.println("After removes:    " + list);
        list.add(2); list.add(9); list.add(1);
        list.sort(Integer::compareTo);
        System.out.println("After sort:       " + list);
        System.out.println("size:             " + list.size());
        list.clear();
        System.out.println("After clear:      " + list + " size=" + list.size());
        MyLinkedList<String> words = new MyLinkedList<>();
        words.add("foo"); words.add("bar"); words.add("baz");
        System.out.print("Iterator:         ");
        for (String w : words) System.out.print(w + " ");
        System.out.println("\n");
    }
    static void testMyStack() {
        System.out.println("══ MyStack ══");
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("After 3 pushes:   " + stack);
        System.out.println("peek:             " + stack.peek());   
        System.out.println("pop:              " + stack.pop());    
        System.out.println("pop:              " + stack.pop());    
        System.out.println("size:             " + stack.size());   
        System.out.println("isEmpty:          " + stack.isEmpty()); 
        stack.pop();
        System.out.println("isEmpty after pop:" + stack.isEmpty()); 
        System.out.println();
    }
    static void testMyQueue() {
        System.out.println("══ MyQueue ══");
        MyQueue<String> queue = new MyQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println("After 3 enqueues: " + queue);
        System.out.println("peek:             " + queue.peek());     
        System.out.println("dequeue:          " + queue.dequeue()); 
        System.out.println("dequeue:          " + queue.dequeue()); 
        System.out.println("size:             " + queue.size());    
        queue.dequeue();
        System.out.println("isEmpty:          " + queue.isEmpty()); 
        System.out.println();
    }
    static void testMyMinHeap() {
        System.out.println("══ MyMinHeap ══");
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        heap.insert(15);
        heap.insert(5);
        heap.insert(20);
        heap.insert(1);
        heap.insert(8);
        System.out.println("After inserts:    " + heap);
        System.out.println("getMin:           " + heap.getMin());        
        System.out.println("extractMin:       " + heap.extractMin());    
        System.out.println("extractMin:       " + heap.extractMin());    
        System.out.println("extractMin:       " + heap.extractMin());    
        System.out.println("size:             " + heap.size());          
        System.out.println("remaining:        " + heap);
        System.out.println();
    }
}