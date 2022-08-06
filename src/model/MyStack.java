package model;

/**
 * Lớp MyStack sẽ chứa các thông tin và thao tác cơ bản của stack
 * @param <T>
 */
public class MyStack<T> {

    private Node<T> head;

    //1 Contructor
    public MyStack() {
        head = null;
    }

    //2. isEmpty
    public boolean isEmpty() {
        return head == null;
    }

    //3. push
    public void push(T info) {
        Node<T> node = new Node<>(info);
        if (head == null) {
            head = node;
        } else {
            node.setNext(head);
            head = node;
        }
    }

    //4. pop
    public T pop() {
        if (head == null) {
            return null;
        }
        Node<T> node = head;
        head = head.getNext();
        
        return node.getInfo();
    }

    //5. peek
    public T peek() {
        return head.getInfo();
    }
    
    
}
