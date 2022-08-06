package model;

/**
 * Lớp MyQueue chứa các thông tin và các thao tác cơ bản của Queue, lớp này sử
 * dụng để phục vụ cho một số nhiệm vụ trong lớp BSTree hoặc có thể cả
 * MyPerson
 * @param <T>
 */
public class MyQueue<T> {

    private Node<T> head;
    private Node<T> tail;

    //1. Contructor
    public MyQueue() {
        head = tail = null;
    }

    //2. isEmpty
    public boolean isEmpty() {
        return head == null;
    }

    //3. Enqueue
    public void enqueue(T info) {
        Node<T> node = new Node<>(info);
        if (head == null) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    //4. Dequeue
    public T dequeue() {
        if (head == null) {
            return null;
        }
        Node<T> current = head;
        head = head.getNext();
        return current.getInfo();
    }

    //5. Peek
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.getInfo();

    }
}
