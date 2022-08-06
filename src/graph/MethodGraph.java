package graph;

import java.util.ArrayList;
import java.util.List;
import model.MyStack;

/**
 * Chứa các phương thức hỗ trợ cho lớp đồ thị
 */
class MethodGraph {

    //1. Lấy ra một hàng xóm chưa thăm (dùng trong DFS, BFS)
    static Vertex getUnVisited(Vertex u, List<List<Edge>> adj, List<Vertex> listV, int INF) {
        int indexU = u.getIndex();

        //1. Duyệt hàng xóm của đỉnh u
        for (Edge e : adj.get(indexU)) {
            //2. Lấy ra đỉnh v, nếu chưa thăm thì trả về đỉnh v.
            int indexV = e.getDestV();
            Vertex v = listV.get(indexV);
            if (v.isVisited() == false && e.getWeight() > 0 && e.getWeight() < INF) {
                return v;
            }
        }

        return null;
    }

    //2. Thuật toán dijkstra
    static void dijkstra(List<List<Edge>> adj, List<Vertex> listV,
            int src, int dest, int INF) {

        //Lưu đanh sách đỉnh đã xét(Để hiển thị từng bước)
        ArrayList<Vertex> listSelect = new ArrayList<Vertex>();

        //Hiển thị danh sách đỉnh cần cập nhật
        MethodGraph.display(listV, src);

        //Thuật  toán ==========
        listV.get(src).setDist(0); //Đỉnh bắt đầu có quảng đường = 0;
        for (int step = 0; step < listV.size(); step++) {
            //Quy tắc 1: Xét đỉnh gần điểm xuất phát (src) nhất (xét đỉnh chưa xét)
            //Cập nhật hàng xóm của nó (Cập nhật quảng đường và đỉnh cha).
            Vertex u = MethodGraph.getMin(listV, INF);
            MethodGraph.updateNeighbours(u, listV, adj, listSelect, step, src, INF);

            //Quy tắc 2: Đánh dấu đỉnh đã xét.
            u.setVisited(true);

            //Quy tắc 3: Lặp lại quy tắc 1, 2 đến khi thấy đỉnh đích, hoặc hết đỉnh chưa xét.
            if (u.getIndex() == dest) {
                break;
            }
        }
    }

    //3. Hiển thị danh sách đỉnh cần cập nhật
    static void display(List<Vertex> listV, int src) {
        System.out.print("The set:\t");
        for (int i = 0; i < listV.size(); i++) {
            if (i != src) {
                System.out.print(listV.get(i).getName() + " ");
            }
        }
        System.out.println();
    }

    //4. Lấy ra đỉnh có quảng đường ngắn nhất trong danh sách đỉnh chưa xét
    static Vertex getMin(List<Vertex> listV, int INF) {
        Vertex u = null;
        int min = INF;

        //Duyệt danh sách đỉnh chưa xét, trả về đỉnh gần điểm xuất phát nhất
        for (Vertex vertex : listV) {
            int distU = vertex.getDist();
            if (vertex.isVisited() == false && distU < min) {
                min = distU;
                u = vertex;
            }
        }

        return u;
    }

    //5. Cập nhật quảng đường cho đỉnh hàng xóm
    static void updateNeighbours(Vertex u, List<Vertex> listV,
            List<List<Edge>> adj, ArrayList<Vertex> listSelect, int step, int src, int INF) {

        listSelect.add(u);//Danh sách đỉnh đã xét(Để hiển thị từng bước)
        boolean stopDisplay = false;//Quản lý việc in listSelect (dan sách đỉnh đã xét)

        //Cập nhật hàng xóm=====
        //1. Duyệt danh sách hàng xóm của đỉnh u
        int indexU = u.getIndex();
        for (Edge edgeUV : adj.get(indexU)) {
            //2. Lấy 1 hàng xóm ra
            int indexV = edgeUV.getDestV();
            Vertex v = listV.get(indexV);

            //3. Cập nhật quảng đường và đỉnh cha của hàng xóm chưa xét
            if (v.isVisited() == false && v.getDist() != 0 && u.getDist() != INF) {
                if (v.getDist() > u.getDist() + edgeUV.getWeight()) {
                    v.setDist(u.getDist() + edgeUV.getWeight());
                    v.setParent(u);
                }

                //4. In thông tin của tất cả đỉnh chưa xét
                displayStep(listSelect, listV, v, step, src, stopDisplay, INF);
                stopDisplay = true;
            }
        }
    }

    //6. Hiển thị các bước của thuật toán dijkstra
    static void displayStep(List<Vertex> listSelect, List<Vertex> listV, Vertex v,
            int step, int src, boolean stopDisplay, int INF) {

        //1. In đỉnh đã xét
        if (stopDisplay == false) {
            System.out.print("\n" + step + ": ");
            for (Vertex vertex : listSelect) {
                System.out.print(vertex.toString());
            }

            //====== In khoảng trắng
            for (int j = listV.size() + 2 - listSelect.size(); j > 0; j--) {
                System.out.print(" ");
            }
        }

        //2. In đỉnh đang được cập nhật
        System.out.print(v);

        //3. In quảng đường
        if (v.getDist() == INF) {
            System.out.print("(INF,");
        } else {
            System.out.print("(" + v.getDist() + ",");
        }

        //4. In đỉnh cha
        if (v.getParent() == null) {
            System.out.print(listV.get(src));
        } else {
            System.out.print(v.getParent());
        }
        System.out.print(") ");

    }

    //7. Hiển thị mảng trọng số
    static void displayWeights(List<List<Edge>> adj, int INF) {
        for (List<Edge> list : adj) {
            for (Edge edge : list) {
                if (edge.getWeight() == INF) {
                    System.out.printf("%-5s", "INF");
                } else {
                    System.out.printf("%-5s", edge);
                }
            }
            System.out.println();
        }
    }

    //8. Hiển thị đường đi ngắn nhất
    static void pathDijkstra(List<Vertex> listV, int src, int dest) {

        //1. Duyệt đỉnh trên đường đi. Đưa vào stack
        MyStack<Vertex> stack = new MyStack<Vertex>();
        Vertex parent = listV.get(dest);
        while (parent != null) {
            stack.push(parent);
            parent = parent.getParent();
        }

        //2. In đường đi (các đỉnh trên đường đi)
        parent = stack.pop();
        System.out.print(parent);
        
        parent = stack.pop();
        while (parent != null) {
            System.out.print("->" + parent);
            parent = stack.pop();
        }

        //3. In độ dài của đường đi
        char srcName = listV.get(src).getName();
        char destName = listV.get(dest).getName();
        int dist = listV.get(dest).getDist();
        System.out.println("\nThe length of shortest path from "
                + srcName + " to " + destName + " is " + dist);
    }
}
