package lesson4.homework4;

public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<String> dl = new DoublyLinkedList<>();
        dl.push("data1");


        System.out.println(dl.toString());
        System.out.println(dl.getSize());


        IteratorForDoublyLinkedList iter = dl.iterator();
        iter.insertAfter("data2");
        iter.insertAfter("data3");
        iter.insertAfter("data4");

        System.out.println(dl.toString());



//        System.out.println("текущий элемент - "+ iter.next());
//        System.out.println(iter.hasNext());
//       System.out.println(iter.atEnd());


    }
}
