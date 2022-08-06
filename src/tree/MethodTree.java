package tree;

import java.util.ArrayList;
import model.Node;

/**
 * Phương thức hỗ trợ trong gói BSTree
 */

 class MethodTree {
     
    //1 Traverse inorder tree and copy all item on tree to an arraylist
    static void  inOrder(ArrayList<Person> list, Node<Person> p) {
        if (p == null) {
            return;
        }
        inOrder(list, p.getLeft());
        list.add(p.getInfo());
        inOrder(list, p.getRight());
    }

    //2 Insert all elements of ArrayList<Person1>  into AVL (cây cân bằng)
    static void listToTree(ArrayList<Person> list, BSTree tree, int first, int last) {
        if (first <= last) {
            //1. Lấy vị trí chính giữa của list
            int mid = (first + last) / 2;
            Person person = list.get(mid);

            //2. Chèn phần tử chính giữa vào cây
            tree.insert(person);

            //3. Chèn vị trí chính giữa trái của list vào cây
            listToTree(list, tree, first, mid - 1);

            //4. Chèn vị trí chính giữa phải của list vào cây
            listToTree(list, tree, mid + 1, last);
        }
    }
    
    //3 Xóa đệ quy
    static Node<Person> delete(Node<Person> root, String id) {
        if (root == null) {
            return root;
        }

        if (id.compareToIgnoreCase(root.getInfo().getId()) < 0) {
            //1. Xóa trái và gán node con trái = kết quả trả về
            root.setLeft(delete(root.getLeft(), id));
            
        } else if (id.compareToIgnoreCase(root.getInfo().getId()) > 0) {
            //2. Xóa phải và gán node con phải = kết quả trả về
            root.setRight(delete(root.getRight(), id));
            
        } else {
            //3. Nếu tìm thấy node cần xóa
            if (root.getRight() == null) {
                //3.1 Nếu node là node lá hoặc có một con bên trái thì trả về node con trái
                return root.getLeft();
                
            } else if (root.getLeft() == null) {
                //3.2 Nếu node có một node con bên phải thì trả về node con bên phải
                return root.getRight();
                
            } else {
                //3.3 Nếu node có 2 node con
                //3.3.1 Tìm node con bên phải nhỏ nhất
                Person minPerson = minId(root.getRight());

                //3.3.2 Thay giá trị của node = giá trị của node con bên phải nhỏ nhất
                root.setInfo(minPerson);

                //3.3.3 Xóa node con bên phải nhỏ nhất
                root.setRight(delete(root.getRight(), minPerson.getId()));
            }
        }
        
        //4. Trả về node gốc của cây.
        return root;
    }

    //4 Tìm node con nhỏ nhất
    private static Person minId(Node<Person> right) {
        Person minPerson = right.getInfo();
       
        //1.  Dyệt cây con right đến node cuối cùng bên trái.
        while (right.getLeft() != null) {
            minPerson = right.getLeft().getInfo();
            right = right.getLeft();
        }
        
        //2. Trả về giá trị nhỏ nhất của cây con right.
        return minPerson;
    }
}
