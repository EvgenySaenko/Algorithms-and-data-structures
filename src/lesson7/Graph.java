package lesson7;

import lesson3.Queue;
import lesson3.Stack;

public class Graph {

    private class Vertex{
        char label;
        boolean wasVisited;

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

    private final int MAX_VERTICES = 32;
    private Vertex [] vertexList;
    private int [][] adjMatrix;
    private int size;

    public Graph(){
        this.vertexList = new Vertex[MAX_VERTICES];
        this.adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        this.size = 0;
    }

    //добавление вершин в наш лист
    public void addVertex(char label){
        vertexList[size++] = new Vertex(label);
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

    //метод обхода в глубину действует след образом
    //если найдена не посещенная вершина - помещаем ее в стек и продолжаем поиск
    //если внутри найденой вершины непосещенные закончились - выбрасываем ее из стека
    public void depthTraverse(){
        Stack stack = new Stack(MAX_VERTICES);
        vertexList[0].wasVisited = true;//начинаем с вершины 0
        displayVertex(0);//показали ее
        stack.push(0);//кладем в стек и если не находим куда из этой вершины уйти - выкидываем ее из стека

        while (!stack.isEmpty()){//пока стек не пуст
            int v = getUnvisitedVertex(stack.peek());// найди не посещенную вершину для этой вершины
            if (v == - 1){//вернется -1 если все вершины уже были посещены
                stack.pop();//следовательно удаляем ее из стека
            }else {
                vertexList[v].wasVisited = true;// в другом случае кладем ее в список посещенных делая ее true
                displayVertex(v);//показываем и кладем в стек
                stack.push(v);
            }
        }
        resetFlags();
    }

    //обход в ширину
    public void widthTraverse(){
        Queue queue = new Queue(MAX_VERTICES);//создаем очередь из 32 элементов
        vertexList[0].wasVisited = true; // первым делаем 0 элемент мы в нем стоим = true
        displayVertex(0);//показали
        queue.insert(0);//добавили
        while (!queue.isEmpty()){//пока очередь не пустая
            int vCurrent = queue.remove();//удалили и запомнили в vCurrent
            displayVertex(vCurrent);//показали что удалили
            int vNext;
            while ((vNext = getUnvisitedVertex(vCurrent) )!= -1){
                vertexList[vNext].wasVisited = true; // первым делаем 0 элемент мы в нем стоим = true
                queue.insert(vNext);//добавили
            }
        }
    }




    //сбрасывает флаги в нашем массиве-списке вершин
    private void resetFlags() {
        for (int i = 0; i < size; i++) {
            vertexList[i].wasVisited = false;
        }
    }


}
