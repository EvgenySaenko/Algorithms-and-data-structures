package lesson4.homework4;


import lesson4.rightHomeWork.DoubleRelatedList;

import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<T>{

    private class Node<T> {

        Node<T> prev;
        Node<T> next;
        T data;

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
            if (!(o instanceof Node)) return false;
            Node <?> node = (Node<?>) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public int getSize(){
        return size;
    }

    public void push(T data) {
        Node n = new Node<>(data);
        //сразу некст ссылается на голову, если нет то станет нулем, если есть то норм
        n.next = head; // if (head == null) n.next = null;
        if (head == null) {//если головы нет то хвост это новый элемент
            tail = n;
        } else {
            head.prev = n;//если голова есть то , то ее прев ссылается но новый
        }
        head = n;//вконце головой становится новый элемент всегда
        size++;
    }

    public T pop() {
        if (isEmpty()) return null;
        T data = tail.data;//достаем данные
        if (tail.prev != null) {//значит у нас не один элемент
            tail.prev.next = null;//переключаемся на него, на элемент слева от хвоста,его некстовую ссылку зануляем, то есть он больше не видит хвост
            tail = tail.prev;//хвостом становится, адрес на который указывал prev.хвоста
        } else {//если tail.prev null то у нас всего 1 элемент просто удалем его
            tail = null;//затирая ссылки в ноль
            head = null;
        }
        size--;
        return data;//возвращаем данные которые удалили
    }

    public boolean contains(T data) {
        Node<T> n = new Node<>(data);
        Node<T> current = head;
        while (!current.equals(n)) {
            if (current.next == null) return false;
            else current = current.next;
        }
        return true;
    }

    public T delete(T data) {
        Node<T> n = new Node<>(data);
        Node<T> current = head;
        Node<T> previous = head;

        while (!current.equals(n)) {
            if (current.next == null) return null;
            else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head && current == tail) {
            head = null;
            tail = null;
        } else if (current == head) {
            head.next.prev = null;
            head = head.next;
        } else if (current == tail) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            previous.next = current.next;
            current.next.prev = previous;
        }
        size--;
        return current.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        Node current = head;
        StringBuilder sb = new StringBuilder("[");
        while(current != null){
            sb.append(current);//добавляем инфу о текущем узле
            current = current.next;//переключаемся
            sb.append((current == null) ? "]" : ",");//если переключились в нулевой узел закрываем скобку
        }
        return sb.toString();
    }
    private class LinkedListIterator implements IteratorForDoublyLinkedList<T>{

        private Node<T> current;

        LinkedListIterator(){
            this.current = head;
        }

        @Override
        public void reset(){//устанавливает себя на голову списка
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
            if (current.next == null){
                return false;
            }
            return (current != null);
        }

        @Override
        public boolean atEnd() { //проверяет находимся ли в конце списка(true or false)
            return current.next == null;
        }
        @Override
        public boolean atBegin() {
            return current.prev == null;
        }

        @Override
        public void insertAfter(T data) {//добавляет после текущего элемента
            Node<T> temp = new Node<>(data);
            if (!atEnd()) {
                temp.next = current.next;
                current.next.prev = temp;
                System.out.println("temp.next " + temp.next);
                System.out.println("current.next.prev " + current.next.prev);
            } else {
                tail = temp;
                System.out.println("tail " + tail);
            }
            current.next = temp;
            temp.prev = current;
            size++;

        }

        @Override
        public void insertBefore(T c) {
            Node<T> temp = new Node<>(c);
            if (!atBegin()) {
                temp.prev = current.prev;
                current.prev.next = temp;
            } else {
                head = temp;
            }
            current.prev = temp;
            temp.next = current;
            size++;
        }

        @Override
        public T deleteCurrent() {
            T temp = current.data;
            if (atBegin() && atEnd()) {
                head = null;
                tail = null;
                reset();
            } else if (atBegin()) {
                head = current.next;
                head.prev = null;
                reset();
            } else if (atEnd()) {
                tail = current.prev;
                tail.next = null;
                current = current.next;
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
            return temp;

        }



        }



    //дает доступ к итератору(вовзращает объект итератора)
    public LinkedListIterator iterator(){
        LinkedListIterator linkedListIterator = new LinkedListIterator();
        return linkedListIterator;
    }
}
