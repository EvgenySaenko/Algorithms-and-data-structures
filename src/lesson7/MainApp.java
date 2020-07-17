package lesson7;

public class MainApp {

    //создадим графф на 10 вершин
    public static void main(String[] args) {
        GraphImprove g = new GraphImprove(10);


        g.addVertex('a');
        g.addVertex('b');
        g.addVertex('c');
        g.addVertex('d');
        g.addVertex('e');
        g.addVertex('f');
        g.addVertex('g');
        g.addVertex('i');
        g.addVertex('k');
        g.addVertex('l');

        g.addEdge(0,1);
        g.addEdge(0,6);
        g.addEdge(1,2);
        g.addEdge(1,3);

        g.addEdge(2,4);
        g.addEdge(3,4);

        g.addEdge(3,6);
        g.addEdge(4,7);

        g.addEdge(6,5);
        g.addEdge(6,7);
        g.addEdge(6,8);

        g.addEdge(7,9);
        g.addEdge(8,9);

        g.printShortestDistance('a','e');
    }
}
