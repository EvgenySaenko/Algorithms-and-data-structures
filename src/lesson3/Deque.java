package lesson3;


import java.util.NoSuchElementException;


//эту неделю куча бытовых дел, писал в последний день всю домашку с утра
//проверил вроде работает и удаление тоже, скажите что упустил в моем случае?
public class Deque {

    private int capacity;
    private int[] deque;
    private int head;//голова очереди
    private int tail;//хвост очереди
    private int items;//текущее кол элементов
    private int start;


    public Deque(int capacity){
        this.capacity = capacity;
        this.deque = new int[capacity];
        this.head = returnHead();//если нечет размер массива голова левее середины
        System.out.println("head вначале равен = "+ head);
        this.tail = returnTail();//хвост правее середины
        System.out.println("tail вначале равен = "+ tail);
        this.items = 0;
        this.start = 0;
        /// |||||H||||T||||\\\\\
    }

    public int returnHead(){
        return this.head = (capacity % 2 !=0) ? ((capacity - 1) / 2): capacity / 2;
    }

    public int returnTail(){
        return tail = (returnHead() + 1);
    }

    public boolean isEmpty(){
        return items == 0;
    }

    public boolean isFull(){
        return items == capacity;
    }

    public int size(){
        return items;
    }

    //увеличивает размер массива вдвое
    public void increaseCapacity(){
        int [] temp = deque;
        int oldCapacity = this.capacity;
        capacity *= 2;
        this.deque = new int[capacity];
        System.out.println("capacity == " + this.capacity);
        System.out.println("oldCapacity ==  " + oldCapacity);

        capacity = (capacity % 2 !=0) ? ((capacity - 1)): capacity;//привели к четному размеру
        System.out.println("привели к четному стек " + capacity);
        if ((capacity - oldCapacity) % 2 != 0){
            start = (int)Math.floor((capacity - oldCapacity) / 2);
            System.out.println("копируем  с = "+ start+ " элемента");
            System.arraycopy(temp,0,deque, start,oldCapacity);
            this.head = start - 1;
            this.tail = start + oldCapacity;
        }else {
            System.arraycopy(temp,0,deque, start,oldCapacity);
            this.head = start;
            this.tail = start + (oldCapacity-1);
        }
    }
    //кладем в голову
    public void insertInHead(int value){
        if (isFull()) {
            System.out.println("количество элементов = " + items);
            System.out.println("емкость стека = " + capacity);
            System.out.println("=====================================================================");
            increaseCapacity();
        }

        deque[head--] = value;//переходит в ячейку и перезаписывает
        this.items++;
        System.out.println("маркер головы теперь на ячейке - " + head);
    }

    //кладем в хвост
    public void insertInTail(int value){
        if (isFull()) {
            increaseCapacity();
        }

        deque[tail++] = value;//переходит в ячейку и перезаписывает
        this.items++;
        System.out.println("маркер хвоста теперь на ячейке - " + tail);
    }

    public int removeFromHead(){
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        if (head + 1 == tail) {//если маркер головы стоит слева от хвоста, то становится в позицию хвоста- дальше не может
            this.head++;
            items--;
        }
        this.head++;
        this.items--;
        return head;
    }

    public int removeFromTail(){
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        if (tail - 1 == head){//если стоит на последней позиции
            this.items--;
            this.tail--;//отступает в позицию головы, дальше не может отступать, его предел
        }
        this.items--;
        this.tail--;
        return tail;
    }


    @Override
    public String toString() {
        if (deque == null) return "null";
        int iMax = deque.length - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        int i = 0;
        while (true) {
            b.append(deque[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
            i++;
        }
    }
}
