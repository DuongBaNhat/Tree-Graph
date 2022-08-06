package tree;

import java.util.Scanner;

/**
 * Lớp MyPerson sẽ chứa các phương thức thức quản lý hồ sơ (được lớp BSTree hỗ
 * trợ)
 */
public class MyPerson {

    private BSTree tree;
    private Scanner sc;

    //1. contructor
    public MyPerson() {
        sc = new Scanner(System.in);
        tree = new BSTree();
    }

    //2.1 input and insert a new product to tree
    public void insert() {

        String id, name, birthPlace;

        //1. id
        System.out.print("id: ");
        id = sc.nextLine();
        while (tree.search(id) != null) {
            System.out.println(id + " is already exists!");
            System.out.print("id: ");
            id = sc.nextLine();
        }

        //2. Tên
        System.out.print("name: ");
        name = sc.nextLine();

        //3. Nơi sinh
        System.out.print("birthPlace: ");
        birthPlace = sc.nextLine();

        //4. Ngày sinh
        System.out.print("dob (dd/MM/yyyy): ");
        String dob = sc.nextLine();

        //5. Chèn vào cây         
        tree.insert(new Person(id, name, birthPlace, dob));

    }

    //2.2 in-order traverse
    public void inOrder() {
        tree.inOrder(tree.getRoot());
    }

    //2.3 BFT a tree
    public void BFT() {
        tree.BFT(tree.getRoot());
    }

    //2.4 search by ID
    public void search() {
        //1. Nhập id tìm kiếm
        System.out.print("Id: ");
        String id = sc.nextLine();

        //2. Tìm kiếm trong cây tree theo id.
        Person p = tree.search(id);

        if (p == null) {
            System.out.println("Not exists !");
        } else {
            System.out.println(p);
        }
    }

    //2.5 delete by ID
    public void delete() {
        //1. Nhập id cần xóa
        System.out.print("Id: ");
        String id = sc.nextLine();

        //2. Xóa theo id
        tree.delete(id);
    }

    //2.6 simply balancing a tree
    public void balance() {
        tree.balance();
    }

    //2.9 Duyệt cây theo thứ tự preorder
    public void preOrder() {
        tree.preOrder(tree.getRoot());
    }

    //2.10 Duyệt cây theo thứ tự Post order
    public void postOrder() {
        tree.postOrder(tree.getRoot());
    }
}
