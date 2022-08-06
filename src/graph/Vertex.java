package graph;

/**
 * Vertex dùng để quản lý thông tin một đỉnh của đồ thị
 */
class Vertex {
    private char name;
    private int index; // Chỉ số của đỉnh trong danh sách
    private int dist; //Quảng đường từ gốc tới đỉnh
    private boolean visited; //Đánh dấu đã thăm
    private Vertex parent; //Quản lý đường đi ngắn nhất
  
    //1. Contructor
    public Vertex(char name, int index, int dist) {
        this.name = name;
        this.index = index;
        this.dist = dist;
        this.visited = false;
        this.parent = null;
    }

    //2. Getter, setter
    public char getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }
        
    //3. toString
    @Override
    public String toString() {
        return name + "";
    }  
}
