package tree;

import model.MyQueue;
import model.Node;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Lớp BSTree chứa các phương thức của BST và hỗ trợ lớp MyPerson
 */
class BSTree {

    // Khai báo
    private Node<Person> root;

    //1. Contructor
    BSTree() {
        root = null;
    }

    //2. Getter, setter
    Node<Person> getRoot() {
        return root;
    }

    //3.Clear
    void clear() {
        root = null;
    }

    //4. is empty
    boolean isEmpty() {
        return root == null;
    }

    //5. count number of products
    int count() {
        ArrayList<Person> list = new ArrayList<Person>();
        MethodTree.inOrder(list, root);
        return list.size();
    }

    //6.1 insert a new Person to a tree (Tạo cây nhị phân tìm kiếm)
    void insert(Person x) {
        Node<Person> newNode = new Node<>(x);

        //Trường hợp 1: Cây rỗng
        if (root == null) {
            root = newNode;
            return;
        }

        Node<Person> parent = null;
        Node<Person> current = root;

        //Trường hợp 2: Cây không rỗng.
        //Bước 1: Duyệt đến cuối cây.
        while (current != null) {
            if (x.equals(current.getInfo()) == true) {
                System.out.println("The key " + x.getId() + " already exists, no insertion");
                return;
            }

            //Duyệt trái, duyệt phải
            parent = current;
            if (x.compareTo(current.getInfo()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        //Bước 2: Chèn vào cuối cây
        //Khi gặp nút rỗng thì chèn vào bên trái hoặc bên phải node cha(parent)
        if (x.compareTo(parent.getInfo()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

    }

    //6.2 inorder a tree
    void inOrder(Node<Person> node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.println(node.getInfo());
        inOrder(node.getRight());
    }

    //6.3 breadth-first traverse a tree (Duyệt cây dùng queue)
    void BFT(Node<Person> p) {
        if (p == null) {
            return;
        }
        MyQueue<Node<Person>> queue = new MyQueue<>();
        Node<Person> q;
        
        //Quy tắc 1: Thêm vào queue
        // Thêm node gốc vào cuối queue.
        queue.enqueue(p);
        
        while (queue.isEmpty() == false) {

            //Quy tắc 2: Lấy node từ đầu queue ra (và xóa khỏi queue)
            //Hiển thị
            q = queue.dequeue();
            System.out.println(q.getInfo());

            //Quy tắc 1: Thêm vào queue
            //Thêm node trái, node phải vào cuối queue
            if (q.getLeft() != null) {
                queue.enqueue(q.getLeft());
            }
            if (q.getRight() != null) {
                queue.enqueue(q.getRight());
            }
            
            //Quy tắc 3: Thực hiện quy tắc 1, 2 đến khi queue rỗng.
        }
    }
    
    //6.4 Search a Node of tree by  ID (Dùng queue)
    //return null if given code does not exists
    Person search(String id) {
        if (root == null) {
            return null;
        }

        MyQueue<Node<Person>> queue = new MyQueue<>();
        Node<Person> current;
        
        //Quy tắc 1: Thêm vào queue
        //Thêm node gốc vào cuối queue
        queue.enqueue(root);

        while (queue.isEmpty() == false) {

            //Quy tắc 2: Lấy đầu node đầu queue ra
            //Kiểm tra (dừng lại nếu tìm thấy)
            current = queue.dequeue();
            if (id.compareToIgnoreCase(current.getInfo().getId()) == 0) {
                return current.getInfo();
            }

            //Quy tắc 1: Thêm vào queue
            //Thêm node trái, node phải vào cuối queue
            if (current.getLeft() != null) {
                queue.enqueue(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.enqueue(current.getRight());
            }

            //Quy tăc 3: Lặp lại quy tắc 1, 2 đến khi tìm thấy, hoặc queue rỗng
        }

        return null;
    }

    //6.5 delete a node by a given ID
    void delete(String id) {
        root = MethodTree.delete(root, id);
    }
    
    //6.6 balance a tree (Cân bằng cây BST)
    void balance() {
        ArrayList<Person> list = new ArrayList<>();

        //1. Copy toàn bộ cây vào danh sách list
        MethodTree.inOrder(list, root);

        //2. Sắp xếp danh sách list
        Collections.sort(list);

        //3. Xóa rỗng cây
        this.clear();

        //4. Copy toàn bộ danh sách (đã sắp xếp) vào cây
        int first = 0;
        int last = list.size() - 1;

        MethodTree.listToTree(list, this, first, last);

    }
    
    //6.9 preOrder
    void preOrder(Node<Person> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getInfo());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    //6.10 postOrder
    void postOrder(Node<Person> node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.println(node.getInfo());
    }
}
