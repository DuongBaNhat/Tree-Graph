package model;

/**
 * Lớp Node để quản lý thông tin và hành của vi mỗi node trong cây nhị phân tìm kiếm
 * @param <T>
 */
public class Node<T> {

    private T info; //Kiểu dữ liệu (Vertex, hoặc Person1)
    private Node<T> left, right, next; // Tạo cây, Linkedlist, Queue

    //1. Constructor
    public Node() {
        info = null;
        left = right = next = null;
    }

    public Node(T info) {
        this.info = info;
        left = right = next = null;
    }

    //2. setter, getter
    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    //3. toString
    public String toString() {
        return info.toString();

    }
}
