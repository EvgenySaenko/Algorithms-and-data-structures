package lesson3;

import java.util.NoSuchElementException;

public class Stack {
    private int capacity;
    private int[]stack;
    private int head;//указывает на место с последним положенным элементом

    public Stack(int capacity){
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.head = -1;
    }

    public boolean isEmpty(){
        return this.head == -1;
    }

    public boolean isFull(){
        return this.head == capacity -1;
    }

    public void push(int value){
        if (isFull()){
            capacity *= 2;
            int[] newStack = new int[capacity];
            System.arraycopy(stack,0,newStack,0,stack.length);
            stack = newStack;
        }
        stack[++head] = value;//передвигаем указатель head с -1 на 0 и кладем туда элемент
    }
    //сначало вернем хед по индексу потом сместим указатель влево
    //и когда след элемент будем записывать мы перезатрем справа элемент
    public int pop(){
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return stack[head--];
    }
    //посмотреть на какой элемент "верхний"
    public int peek(){
        return stack[head];
    }
}
