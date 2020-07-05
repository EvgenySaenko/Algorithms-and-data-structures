package lesson4;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class RelatedList {

    private class Node{
        Cat c;
        Node next;

        public Node(Cat c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return c.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return c.equals(node.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }
    }

    private Node head;

    public RelatedList(){
        head = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void push(Cat c){
        Node n = new Node(c);//содали ноду, положили кота в нее
        n.next = head;
        head = n;
    }

    public Cat pop(){//при удалении возвращаем данные лежащие в хэде, и делаем хедом=> next элемент
        if (isEmpty()) return null;
        Cat tamp = head.c;
        head = head.next;
        return tamp;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder sb = new StringBuilder("[");
        while(current != null){//пока текущий узел не будет равен нулю
            sb.append(current);//добавляем инфу о текущем узле
            current = current.next;//переключаемся
            sb.append((current == null) ? "]" : ",");//если переключились в нулевой узел закрываем скобку
        }
        return sb.toString();
    }

    public boolean contains(Cat c) {
        return find(c) == null;
    }

    private Node find(Cat c) {
        if (isEmpty()) return null;//проверили head на null
        Node current = head;
        while (!current.c.equals(c)) {
            if (current.next == null)//если не можем переключится(след ссылка null)
                return null;
            else
                current = current.next;//переключаем ссылку, если ссылка не null
        }
        return current;
    }


    public boolean delete(Cat c){
        Node current = head;
        Node previous = head;
        while (!current.c.equals(c)){
            if (current.next == null){
                return false;
            }else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head){
            head = head.next;
        }else {
            previous.next = current.next;//у предыдущего элемента ссылка которая ссылаласть на нас должна ссылаться на нашего next , current пропускай
        }
        return true;
    }

}
