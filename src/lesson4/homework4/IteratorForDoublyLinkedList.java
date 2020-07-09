package lesson4.homework4;

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

    //добавляет после текущего элемента
    void insertAfter(T data);

    //добавляет перед текущим элементом
    void insertBefore(T data);

    //удаляет текущий элемент
    T deleteCurrent();
}
