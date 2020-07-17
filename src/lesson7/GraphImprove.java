package lesson7;

import lesson3.Queue;

import java.util.LinkedList;

public class GraphImprove {
    private class Vertex{
        char label;
        boolean wasVisited;
        int distance;//удаленность от истояника
        int pred;//сосед предыдущий

        public Vertex(char label){
            this.label = label;
            this.wasVisited = false;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "label=" + label +
                    '}';
        }
    }

    private Vertex [] vertexList;
    private int [][] adjMatrix;
    private int size;
    private int graphSize;

    public GraphImprove(int size){
        this.graphSize = size;
        this.vertexList = new Vertex[size];
        this.adjMatrix = new int[size][size];
        this.size = 0;
    }

    public int getLabelVertex(char label) {
        for (int i = 0; i < vertexList.length; i++) {
            if (vertexList[i].label == label)return i;
        }
        return -1;
    }

    //добавление вершин в наш лист
    public void addVertex(char label){
        vertexList[size++] = new Vertex(label);//кладем в 0 и переходим в 1
    }

    //добавление ребра нашего графа(связи между вершинами) в матрицу смежности
    public void addEdge(int start, int end){
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    //выводит инфо о вершине
    public void displayVertex(int vertex){
        System.out.println(vertexList[vertex]);
    }

    //ищет не посещенные узлы из текущего(поиск по строке массива)
    public int getUnvisitedVertex(int ver){
        for (int i = 0; i < size; i++) {
            //если i элемент равен 1 (то есть это вершина) и он в  массиве нашем листе vertexList не был посещен
            if (adjMatrix[ver][i] == 1 && !vertexList[i].wasVisited){
                return i;//то вернем этозначение
            }
        }
        return -1; //непосещенных узлов(соседей) у текущей вершины нет
    }


    //метод поиска кратчайшего пути
    public boolean shortestPathSearch(int start, int finish){
        Queue queue = new Queue(graphSize);
        // изначально все вершины не посещены
        // и так как путь еще не построен
        // vertexList[i].distance для всего максимально допустимый

        for (int i = 0; i < vertexList.length; i++) {
            vertexList[i].distance = Integer.MAX_VALUE;
            vertexList[i].pred = -1;
        }

        // вершину отправления маркируем
        vertexList[start].wasVisited = true;
        vertexList[start].distance = 0;//ставим ей значение 0
        queue.insert(start);//и кладем в очередь


        while(!queue.isEmpty()){//пока очередь не пуста
            int vCurrent = queue.remove();//удалим и запомним
//            displayVertex(vCurrent);
//            System.out.println("удаляем из очереди "+ vertexList[vCurrent]);
            int vNext;
            //если находим смежную и она не была посещена
            while ((vNext = getUnvisitedVertex(vCurrent) )!= -1 & vNext != vCurrent){
                vertexList[vNext].wasVisited = true;//маркируем ее
                vertexList[vNext].distance = vertexList[vCurrent].distance + 1;//ставим ей расстояние +1(для B 0+1) итд
                vertexList[vNext].pred = vCurrent;//и говорим что предок у нее карент - (откуда мы пришли к ней) -предок ее 0 итд
                queue.insert(vNext);//и добавляем в очередь эту вершину
                if (vNext == finish){
                    return true;
                }
            }

        }
        return false;
    }

    public  void printShortestDistance(char source, char dest) {
        int src = getLabelVertex(source);//получаем номер ячейки вершины отправления
        int dst = getLabelVertex(dest);//получаем номер ячейки вершины назначения

        if ((src == -1 & dst ==-1) || (shortestPathSearch(src,dst) == false) ){
            System.out.println("Таких вершин не существует");
            return;
        }

        // Создаем линкед лист кратчайшего пути
        LinkedList<Vertex> path = new LinkedList<Vertex>();

        int current = dst;//запоминаем вершину назначения
        System.out.println(vertexList[current] + " путь назначения");
        path.add(vertexList[current]);//добавляем

        while (vertexList[current].pred != -1) {//пока не -1
            path.add(vertexList[vertexList[current].pred]);//добавляем в путь предыдущего
            current = vertexList[current].pred;//делаем текущим => ближайшего предка
        }

        // выводим длину кратчайшего пути
        System.out.println("Наименьшая длина пути: " + vertexList[dst].distance);

        // выводим кратчайший путь
        System.out.print("Путь начали ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + "--> ");
        }
        System.out.println("прибыли!");
    }

}
