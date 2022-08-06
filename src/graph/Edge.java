package graph;

class Edge {

    private int weight; //Trọng số
    private int src; // Điểm đầu, dùng trong BellmanFor (Cập nhật đỉnh cuối từ đỉnh đầu)
    private int dest; //Điểm cuối dùng để lấy đỉnh hàng xóm

    //1. Contructor
    Edge(int weight, int src, int dest) {
        this.weight = weight;
        this.src = src;
        this.dest = dest;
    }

    //2. setter, getter
    int getWeight() {
        return weight;
    }

    int getSrcV() {
        return src;
    }

    int getDestV() {
        return dest;
    }

    //3. toString
   
    @Override
    public String toString() {
        return weight + "";
    }
}
