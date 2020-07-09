package lesson4.homework4;


import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<T>{

    private class Node<T> {

        Node<T> prev;
        Node<T> next;
        T data;

        public Node() {
            this.data = null;
            this.next = null;
            this.prev = null;
        }

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(T data, Node<T> previous, Node<T> next) {
            this.data = data;
            this.prev = previous;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return data.equals(node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

    private Node head, tail;
    private int size;

    DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public int getSize(){
        return size;
    }

    public void pushHead(T data){
        if (isEmpty()){
            tail = new Node<>(data);
            head = tail;
            size++;
            System.out.println("добавлен "+ size + " элемент " + tail);
            System.out.println("хвост содержит " + tail+"\n");
        }else if(size == 1){
            head = new Node<T>(data);
            System.out.println("голова содержит тоже что и хвост " + head);
            size++;
            System.out.println("добавлен "+ size + " элемент " + head +"\n");
            head.next = tail;
            tail.prev = head;
            System.out.println("head.next = " + head.next);
        }
        else {// в другом случае => если голова и хвост уже есть
            Node<T> current = new Node<>(data);//головой становится новый элемент и в некст записывает ссылку на старую голову
            current.next = head;
            head = current;
            size++;
            System.out.println("добавлен "+ size + " элемент " + current +"\n");

            System.out.println("head => "+ head);
            System.out.println("tail => "+ tail);
        }
    }

    public T popHead(){//при удалении возвращаем данные лежащие в хэде, и делаем хедом=> next элемент
        if (isEmpty()) return null;
        T tamp = (T) head.data;//сохраняем данные во временную переменную
        head = head.next;//смещаем head вправо
        size--;
        return tamp;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder sb = new StringBuilder("[");
        while(current != null){
            sb.append(current);//добавляем инфу о текущем узле
            current = current.next;//переключаемся
            System.out.println(sb);
            sb.append((current == null) ? "]" : ",");//если переключились в нулевой узел закрываем скобку
        }
        return sb.toString();
    }
    private class LinkedListIterator implements IteratorForDoublyLinkedList<T>{

        private Node current;

        LinkedListIterator(){
            this.current = head;
        }

        @Override
        public void reset() {//устанавливает себя на голову списка
            this.current = head;
        }

        @Override
        public T next() { //возвражает данные на текущем своем положении и перемещается в следующюю ячейку
            current = current.next;
            return (T) current.data;
        }

        @Override
        public T getCurrent() {//возвращает текущий элемент
            return (T) current.data;
        }

        @Override
        public boolean hasNext() {//проверяет есть ли следующий элемент
            if (size ==1){
                return false;
            }
            return (current != null);
        }

        @Override
        public boolean atEnd() { //проверяет находимся ли в конце списка(true or false)
            return current.next == null;
        }

        @Override
        public void insertAfter(T data) {//добавляет после текущего элемента
            Node<T> freeItem = null;//свободный элемент
            Node<T> bindingItem =  new Node<T>(data);//создали новый элемент без ссылки;//связующий элемент
            //T temp = null;
            if (isEmpty()){
                throw new NoSuchElementException("Список пуст, нет элементов после которых вы могли бы вставить свой");
            }else if(size == 1){
                // при создании итератора как мы знаем он указывает на голову  this.current = head;
                tail = new Node<T>(data);//t.k елемент у нас 1, новый элемент встает за ним и становится хвостом
                head.next = tail; //след за головой становится новый элемент = он же хвост
                tail.prev = head;
                System.out.println("вставленный элемент = " + tail);
                size++;
                System.out.println("добавлен "+ size + " элемент " + tail +"\n");
                System.out.println("head = "+ head +" "+ tail + " = tail");
            }
            else if(size == 2) {// в другом случае => если голова и хвост уже есть
                head.next = bindingItem;
                bindingItem.prev = head;
                bindingItem.next = tail;
                tail.prev = bindingItem;
                size++;
                System.out.println("добавлен "+ size + " элемент " + bindingItem +"\n");

                System.out.println("head => "+ head);
                System.out.println("bindingItem => " + bindingItem);
                System.out.println("tail => "+ tail);
            }else {
                System.out.println("мы сюда попали");
                freeItem = bindingItem;//новый становится старым дата3
                Node<T> newNode = new Node<>(data);//data4
                newNode.prev = head;
                head.next = newNode;
                newNode.prev = freeItem;
                freeItem.prev = newNode;
                freeItem.next = tail;
                size++;
                System.out.println("добавлен "+ size + " элемент " + freeItem +"\n");

                System.out.println("head => "+ head);
                System.out.println("freeItem => " + freeItem);
                System.out.println("bindingItem => "+ bindingItem);

                System.out.println("tail => "+ tail);

            }
        }

        @Override
        public void insertBefore(T data) { //добавляет перед текущим элементом
            if (isEmpty()){
                tail = new Node<>(data);
                head = tail;
                size++;
                System.out.println("добавлен "+ size + " элемент " + tail);
                System.out.println("хвост содержит " + tail+"\n");
            }else if(size == 1){
                head = new Node<T>(data);
                System.out.println("голова содержит тоже что и хвост " + head);
                size++;
                System.out.println("добавлен "+ size + " элемент " + head +"\n");
                head.next = tail;
                tail.prev = head;
                System.out.println("head.next = " + head.next);
            }
            else {// в другом случае => если голова и хвост уже есть
                Node<T> current = new Node<>(data);//головой становится новый элемент и в некст записывает ссылку на старую голову
                current.next = head;
                head = current;
                size++;
                System.out.println("добавлен "+ size + " элемент " + current +"\n");

                System.out.println("head => "+ head);
                System.out.println("tail => "+ tail);
            }
        }

        @Override
        public T deleteCurrent() {//удаляет текущий элемент
            if (isEmpty()) return null;
            T tamp = (T) current.data;//сохраняем данные во временную переменную
            current = current.next;//смещаем head вправо
            size--;
            return tamp;
        }
    }
    //дает доступ к итератору(вовзращает объект итератора)
    public LinkedListIterator iterator(){
        LinkedListIterator linkedListIterator = new LinkedListIterator();
        return linkedListIterator;
    }
}
