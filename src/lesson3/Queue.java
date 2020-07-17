package lesson3;

import java.util.NoSuchElementException;

public class Queue {
    private int capacity;
    private int[] queue;
    private int head;//голова очереди
    private int tail;//хвост очереди
    private int items;//текущее кол элементов


    public Queue(int capacity){
        this.capacity = capacity;
        queue = new int[capacity];
        head = 0;
        tail = -1;
        items = 0;
    }

    public int getSize() {
        return capacity;
    }

    public void setSize(int size) {
        this.capacity = size;
    }

    public int[] getQueue() {
        return queue;
    }

    public void setQueue(int[] queue) {
        this.queue = queue;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
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

    public void insert(int value){
        if (isFull()){
            capacity *= 2;
            int[] newQ = new int[capacity];
            if (tail >= head){
                System.arraycopy(queue,0,newQ,0,queue.length);
            }else {
                System.arraycopy(queue,0,newQ,0,tail+1);
                System.arraycopy(queue,head,newQ,capacity - (queue.length - head),queue.length - head - 1);
            }
            queue = newQ;
        }
        if (tail == capacity -1)
            tail = -1;
        queue[++tail] = value;
        items++;
    }

    public int remove(){
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int temp = queue[head++];
        head %= capacity;//if(head ==capacity) head = 0;
        items--;
        return temp;
    }
}
