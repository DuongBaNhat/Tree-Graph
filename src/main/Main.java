package main;

import graph.Graph;
import java.util.Scanner;
import tree.MyPerson;

/**
 * Chương trình quản lý hồ sơ và tìm đường đi ngắn nhất Lớn Main chứa menu
 */
class Main {

    public static void main(String[] args) {
        //1. Quản lý hồ sơ
        MyPerson myPerson = new MyPerson();

        //2. Quản lý đường đi
        String fileName = "Matran.txt";
        Graph graph = new Graph(fileName);

        //3. Triển khai
        showMenu(myPerson, graph);
    }

    public static void showMenu(MyPerson myPerson, Graph graph) {

        Scanner sc = new Scanner(System.in);
        boolean isPrint = true;
        while (true) {
            try {
                if (isPrint == true) {
                    printMenu();
                }
                isPrint = true;
                System.out.print("\nChoice: ");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        System.out.println("1. Insert a new Person.");
                        myPerson.insert();
                        break;
                    case "2":
                        System.out.println("2. Inorder traverse");
                        myPerson.inOrder();
                        break;
                    case "3":
                        System.out.println("3. Breadth-First Traversal traverse");
                        myPerson.BFT();
                        break;
                    case "4":
                        System.out.println("4. Search by Person ID");
                        myPerson.search();
                        break;
                    case "5":
                        System.out.println("5. Delete by Person ID");
                        myPerson.delete();
                        break;
                    case "6":
                        System.out.println("6. Balancing Binary Search Tree ");
                        myPerson.balance();
                        break;
                    case "7":
                        System.out.println("7. DFS_Graph");
                        graph.DFS();
                        break;
                    case "8":
                        System.out.println("8. Dijkstra");
                        graph.dijkstra();
                        break;
                    case "9":
                        System.out.println("9. Post order tree");
                        myPerson.postOrder();
                        break;
                    case "10":
                        System.out.println("10. Pre order tree");
                        myPerson.preOrder();
                        break;
                    case "11":
                        System.out.println("11. BFS_Graph");
                        graph.BFS();
                        break;
                    case "0":
                        System.out.println("\nThanks !");
                        System.exit(0);
                    default:
                        System.out.println("\nYour input is not supported, please try again");
                        isPrint = false;
                        break;
                }

            } catch (Exception e) {
                System.out.println("\nError! Your input is not supported, please try again");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n\t*** QUẢN LÝ HỒ SƠ VÀ TÌM ĐƯỜNG ĐI NGẮN NHẤT ***\n");
        System.out.println("\t***Choose one of this options:");
        System.out.println("\tPerson Tree:");
        System.out.println("\t1. Insert a new Person.");
        System.out.println("\t2. Inorder traverse");
        System.out.println("\t3. Breadth-First Traversal traverse");
        System.out.println("\t4. Search by Person ID");
        System.out.println("\t5. Delete by Person ID");
        System.out.println("\t6. Balancing Binary Search Tree ");
        System.out.println("\t7. DFS_Graph");
        System.out.println("\t8. Dijkstra");
        System.out.println("\t9. Post order tree");
        System.out.println("\t10. Pre order tree");
        System.out.println("\t11. BFS_Graph");
        System.out.println("\tExit:");
        System.out.println("\t0. Exit");
    }
}
