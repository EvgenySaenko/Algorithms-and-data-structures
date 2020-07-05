package lesson2;

import lesson3.Deque;
import lesson3.Reverse;
import lesson3.Stack;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        int[] arr;
        arr = new int[5];

        int[]arr2 = {1,2,3,4};
        int [] arr5 = {3,1,9,4,7,12};

       Array arrTest = new Array(3,1,9,4,7,12);

        Array arr77 = new Array(3,1,9,4,7,12,2,5,8,45,31,15,6,23,44,67,11,16,19,25);


        Array arr55 = new Array(12,8,45,4,1,12,2,24,8,45,7,15,10,3,44,6,11,16,19,25,33,13,17,65,34,98,67,9,19);
        System.out.println(arr55.length());

        //System.out.println("удалим массив, вернет - " + arr55.deleteAll());






//        System.out.println("O большое BubbleSort = " + arr55.bubbleSort());
//        System.out.println(arr55.toString());

//        System.out.println("O большое bubbleSortEvenOdd = " + arr77.bubbleSortOptimize());
//        System.out.println(arr77.toString());

//        System.out.println("O большое sortInsert = " + arr77.sortInsert());
//
//        System.out.println("O большое SelectSort = " + arr77.sortSelect());
//        String string = "Привет мир!";//тест строка
//        String test = "";
//        Reverse reverseString = new Reverse();
//        String returnLine = reverseString.reverseReadString(string);
//        System.out.print("Строка в обратном порядке: " + returnLine);

        Deque deque = new Deque(7);
        deque.insertInHead(4);
        deque.insertInTail(5);
        System.out.println(deque.removeFromHead());
        System.out.println(deque.removeFromHead());
        System.out.println(deque.removeFromHead());




//        deque.insertInHead(4);
//        deque.insertInHead(3);
//        deque.insertInHead(2);
//        deque.insertInHead(1);
//        deque.insertInTail(5);
//        deque.insertInTail(6);
//        deque.insertInTail(7);
//        deque.insertInHead(-1);
//        deque.insertInTail(8);
//        deque.insertInTail(9);
//        deque.insertInTail(10);
//        deque.insertInTail(11);
//        deque.insertInHead(-2);
//        deque.insertInHead(-3);
//        deque.insertInHead(-4);
        System.out.println(deque);



    }
}
