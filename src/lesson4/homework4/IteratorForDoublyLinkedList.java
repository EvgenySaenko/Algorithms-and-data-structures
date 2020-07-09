package lesson4.homework4;

import java.util.Iterator;

public interface IteratorForDoublyLinkedList<T> {

    //устанавливает себя на голову списка
    void reset();

    //возвражает данные на текущем своем положении и перемещается в следующюю ячейку
    T next();

    //возвращает текущий элемент
    T getCurrent();

    //проверяет есть ли следующий элемент
    boolean hasNext();

    //проверяет находимся ли в конце списка(true or false)
    boolean atEnd();

    boolean atBegin();

    //добавляет после текущего элемента
    void insertAfter(T data);

    //добавляет перед текущим элементом
    void insertBefore(T data);

    //удаляет текущий элемент
    T deleteCurrent();
}
