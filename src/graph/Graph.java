package graph;

import model.MyQueue;
import model.MyStack;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Đồ thị được triển khai bằng danh sách đỉnh và mảng cạnh (trọng số) Đồ thị sẽ
 * chứa phướng thức của đồ thị và các thuật toán tìm đường đi
 */
public class Graph {

    private int n;
    private List<Vertex> listV;
    private List<List<Edge>> adj;
    private static int INF = 9999;

    //1. Contructor
    public Graph(String fileName) {
        try {
            setWeight(fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    //2. Getter, setter
    private void setWeight(String fileName) throws IOException {
        String s, s1, delim = ",", mode = "r"; //s nhận một dòng khi đọc f, s1 là một token của t
        Edge edge; //Chứa trọng số, đỉnh đầu, đỉnh cuối
        String names; //Danh sách tên của đỉnh
        StringTokenizer t; //Đuối tượng lấy s và delim làm tham số để tách s thành các token theo delim
        RandomAccessFile f; //Con trỏ file để đọc file trả về chuỗi

        //1. Đọc file
        f = new RandomAccessFile(fileName, mode);

        //2. Số đỉnh
        s = f.readLine();
        n = Integer.parseInt(s.trim());

        //3. Khởi tạo ma trận  cung
        this.setAdj();

        //4. Khởi tạo danh sách đỉnh(names là một chỗi các ký tự)
        names = f.readLine();
        this.setListV(names);

        //5. Trọng số
        int src, dest, weight;
        for (int i = 0; i < n; i++) {

            s = f.readLine();
            t = new StringTokenizer(s, delim);

            for (int j = 0; j < n; j++) {
                s1 = t.nextToken();
                src = i;
                dest = j;
                weight = Integer.parseInt(s1.trim());

                //Khởi tạo cung, thêm vào ma trận cung
                edge = new Edge(weight, src, dest);
                adj.get(i).add(edge);
            }
        }
        f.close();
    }

    private void setListV(String names) {
        listV = new ArrayList<Vertex>();
        for (int i = 0; i < n; i++) {
            char name = names.charAt(i);
            int index = i;
            int dist = INF;
            Vertex v = new Vertex(name, index, dist);

            listV.add(v);
        }
    }

    private void setAdj() {
        adj = new ArrayList<List<Edge>>();
        for (int i = 0; i < n; i++) {
            List<Edge> listE = new ArrayList<Edge>();
            adj.add(listE);
        }
    }

    //3.7. DFS (Dùng stack)
    public void DFS() {
        MyStack<Vertex> stack = new MyStack<Vertex>();

        System.out.print("Enter start: ");
        int src = new Scanner(System.in).nextInt();

        //Quy tắc 1: Kiểm tra điểm liền kề chưa được kiểm tra.
        //Đánh dấu đã kiểm tra. Hiển thị. Đưa vào stack
        Vertex v = listV.get(src);
        v.setVisited(true);
        System.out.print("DFS_Graph: " + v);
        stack.push(v);

        while (stack.isEmpty() == false) {
            Vertex u = stack.peek();

            //Quy tắc 1: Kiểm tra điểm liền kề chưa được kiểm tra.
            //Đánh dấu đã kiểm tra. Hiển thị. Đưa vào stack
            v = MethodGraph.getUnVisited(u, adj, listV, INF);
            if (v != null) {
                v.setVisited(true);
                System.out.print(v);
                stack.push(v);

            } else {
                //Quy tắc 2: Nếu không tìm thấy điểm liền kề thì xóa khỏi stack
                stack.pop();

            }
            //Quy tắc 3: Lặp lại bước 1 và bước 2 đến khi stack rỗng.
        }
        for (Vertex vertex : listV) {
            vertex.setVisited(false);
        }
    }

    //3.8 Dijkstra
    public void dijkstra() {
        //1. Hiển thị ma trận trọng số
        System.out.println("\nThe weighted matrix of the graph:");
        System.out.println("=================================");
        MethodGraph.displayWeights(adj, INF);

        //2. Nhập thông tin (đỉnh đầu, đỉnh đích)
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter start: ");
        int src = sc.nextInt();
        System.out.print("Enter end: ");
        int dest = sc.nextInt();

        //3. Thuật toán dijkstra
        System.out.println("\nDijkstra algorithm for shortest path from "
                + listV.get(src).getName() + " to " + listV.get(dest).getName());

        MethodGraph.dijkstra(adj, listV, src, dest, INF);

        //4. Hiển thị đường đi
        System.out.print("\n\nPath: ");
        MethodGraph.pathDijkstra(listV, src, dest);

        //5. Cập nhật lại giá trị ban đầu cho đỉnh
        for (Vertex vertex : listV) {
            vertex.setVisited(false);
            vertex.setParent(null);
            vertex.setDist(INF);
        }

    }

    //3.11 BFS (Dùng queue)
    public void BFS() {
        MyQueue<Vertex> queue = new MyQueue<Vertex>();

        System.out.print("Enter start: ");
        int src = new Scanner(System.in).nextInt();

        //Quy tắc 1: Kiểm tra đỉnh liền kề chưa được kiểm tra.
        //Đánh dấu đã kiểm tra. Hiển thị. Chèn vào queue
        Vertex v = listV.get(src);
        v.setVisited(true);
        System.out.print("BFS_Graph: " + v);
        queue.enqueue(v);

        while (queue.isEmpty() == false) {
            Vertex u = queue.peek();

            //Quy tắc 1: Kiểm tra đỉnh liền kề chưa được kiểm tra.
            //Đánh dấu đã kiểm tra. Hiển thị. Chèn vào queue   
            v = MethodGraph.getUnVisited(u, adj, listV, INF);
            if (v != null) {
                v.setVisited(true);
                System.out.print(v);
                queue.enqueue(v);

            } else {
                //Quy tắc 2: Nếu không tìm thấy đỉnh liền kề thì xóa đỉnh khỏi queue
                queue.dequeue();
            }
            //Quy tắc 3: Lặp lại bước 1 và 2 cho đến khi queue rỗng
        }

        
        for (Vertex vertex : listV) {
            vertex.setVisited(false);
        }
        System.out.println();
    }
}
