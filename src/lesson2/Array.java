package lesson2;

import java.util.Arrays;

public class Array {
    private int arr[];
    private int size;
    private boolean isSorted;

    private Array(){
        this.isSorted = false;
    }

    public Array(int capacity){
        this();
        if (capacity ==0) capacity = 1;
        this.arr = new int[capacity];
        this.size = 0;
    }
    public Array(int...args){
        this();
        this.size = args.length;
        this.arr = args;
    }

    public Array(Array from){
        this.arr = new int[from.size];
        this.size = from.size;
        this.isSorted = from.isSorted;
        for (int i = 0; i < from.size; i++) {
            arr[i] = from.get(i);
        }
    }

    @Override
    public String toString() {
        if (arr == null) return "null";
        int iMax = size - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        int i = 0;
        while (true) {
            b.append(arr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
            i++;
        }
    }

    public int get(int index){
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        return arr[index];

    }

    public void set(int index, int value){
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        arr[index] = value;
    }
    //вернуть длину массива
    public int length(){
        return size;
    }

    //увеличивает размер массива вдвое сохраняя старую ссылку
    private void increaseCapacity(){
        int[] temp = arr;
        arr = new int[size * 2];
        System.arraycopy(temp,0,arr,0,size);
    }

    private void copyLeft(int index) {
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
    }

    private void copyRight(int index) {
        System.arraycopy(arr, index, arr, index + 1, size - index - 1);
    }

    //добавление в конец массива
    public void append(int value){
        checkCapacity();
        arr[size++] = value;
        isSorted = false;
    }

    public void appendRandom(int elements, int range){
        for (int i = 0; i < elements; i++) {
            append((int)(Math.random() * range));
        }
    }

    //проверка на длину
    private void checkCapacity(){
        if (size >= arr.length){
            increaseCapacity();
        }
    }




    //удаляем последний элемент массива
    boolean deleteLast(){
        if (size == 0)
            return false;
        size--;
        return true;
    }
    //удаляем любой элемент в массиве
    public int delete(int value){
        if (size == 0 || value >= size || value < 0) throw new ArrayIndexOutOfBoundsException(value);
        int[] temp = arr;
        arr = new int[size];
        System.arraycopy(temp,0,arr,0,value);
        System.out.println(size);
        System.arraycopy(temp,value + 1,arr,value,size - (++value));
        return arr[--size];
    }

    //удаляем все элементы массива
    public int deleteAll(){
        if (size == 0) throw new ArrayIndexOutOfBoundsException(-1);
        return  size = 0;
    }
    //удаляет элементы в массиве равные входящему значению(например все 5-ки)
    boolean deleteAll(int value){
        boolean success = false;
        for (int i = 0; i < size; i++) {
            if (arr[i] == value){
                delete(i--);
                success = true;
            }
        }
        return success;
    }

    //вставка в массив
    public void insert(int index, int value){
        if (index > size || index < 0){
            throw new ArrayIndexOutOfBoundsException(index);
        }else if(index == size){
            append(value);
        }else {
            checkCapacity();
            copyRight(index);
            arr[index] = value;
            size++;
        }
    }






    //линейный поиск
    public int find (int value){
        for (int i = 0; i <size ; i++) {
            if (arr[i] == value)
                return i;
        }
        return -1;
    }
    //бинарная
    public boolean hasValue(int value){
        if (!isSorted)
            throw new RuntimeException("try the 'find' method");

        int l = 0;
        int r = size;
        int m;

        while (l < r) {
            m = (l + r) >> 1; //(l + r) / 2
            if (value == arr[m]) return true;
            else if (value < arr[m])
                r = m;
            else
                l = m + 1;
        }
        return false;
    }




    //замена местами элементов
    private void swap(int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //пузырьковая сотритовка
    public int bubbleSort(){
        int countO = 0;//О большое
        for (int i = 0; i < size ; i++) {
            //вычитаем количество ячеек которое можно с каждым последующим большим кругом можно не проверять(size - countI)
            for (int j = 0; j < size - 1; j++) {
//                System.out.println("сравниваю элемент " + arr[j] + " и " + arr[j+1]);
//                System.out.println("сравнили  " + j);
                countO++;
                if (arr[j] > arr[j + 1]) {
                    System.out.println("замена элементов # " + arr[j] + " и "+ arr[j + 1] + "  " + Arrays.toString(arr));
                    swap(j, j + 1);
                }
            }
            //прибавляем счетчик полных проходов, тем самым понимаем что за первый проход
            //сортировка вытеснила самый большой элемент в последнюю(правую) ячейку массива и ее можно не проверять
        }
        isSorted = true;
        return countO;
    }

    //пузырьковая сотритовка
    public int bubbleSortOptimize(){
        int countO = 0;//О большое
        int countI = 1;//счетчик большого круга
        for (int i = 0; i < size ; i++) {
            //вычитаем количество ячеек которое можно с каждым последующим большим кругом можно не проверять(size - countI)
            for (int j = 0; j < size - countI; j++) {
//                System.out.println("сравниваю элемент " + arr[j] + " и " + arr[j+1]);
//                System.out.println("сравнили  " + j);
                countO++;
                if (arr[j] > arr[j + 1]) {
                    System.out.println("замена элементов # " + arr[j] + " и "+ arr[j + 1] + "  " + Arrays.toString(arr));
                    swap(j, j + 1);
                }
            }
            //прибавляем счетчик полных проходов, тем самым понимаем что за первый проход
            //сортировка вытеснила самый большой элемент в последнюю(правую) ячейку массива и ее можно не проверять
            countI++;
        }
        isSorted = true;
        return countO;
    }

    public int bubbleSortImproved(){
        int countO = 0;
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                countO++;
                if (arr[j] > arr[j + 1]){
                    swap(j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;//если сделали один внутрений проход и не меняли элементы местами то дальше не проверяем все отсортировалось
        }
        isSorted = true;
        return countO;
    }

    public int bubbleSortEvenOdd() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = (i % 2 != 0) ? 0 : 1; j < size - 1; j += 2) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
        return count;
    }



    //int [] arr5 = {3,1,9,4,7,12};
    //сортировка выбора
    public int sortSelect(){
        int countO = 0;
        for (int flag = 0; flag < size; flag++) {
            int cMin = flag;
           // System.out.println("минимальный элемент = "+cMin);
            for (int rem = flag + 1; rem < size; rem++) {
               // System.out.println(arr[cMin]+ " > " +  arr[rem] );
                countO++;
                if (arr[cMin] > arr[rem])
                    cMin = rem;
            }
            swap(flag,cMin);
        }
        isSorted = true;
        return countO;
    }


    //int [] arr5 = {3,1,9,4,7,12};
    //сортировка вставки
    public int sortInsert() {
        int countO = 0;
        for (int out = 0; out < size; out++) {
            int temp = arr[out];
            int in = out;
            countO++;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                in--;
            }
            arr[in] = temp;
        }
        isSorted = true;
        return countO;
    }


    /////////////////////////////////////////////////////////
    // ищем максимальный элемент в массиве
    public int getMax() {
        if (size == 0) throw new RuntimeException("Empty array");
        if (size == 1) return arr[0];
        int r = arr[0];//запоминаем первый элемент массива
        for (int i = 1; i < size; i++) {
            if (r < arr[i]){ // если находим больше него
                r = arr[i];//перезаписываем его во временную переменную
            }
        }
        return r;
    }

    // ищем минимальный элемент в массиве
    public int getMin() {
        if (size == 0) throw new RuntimeException("Empty array");
        if (size == 1) return arr[0];
        int r = arr[0];//запоминаем первый элемент массива
        for (int i = 1; i < size; i++) {
            if (r > arr[i]){ // если находим меньше  него
                r = arr[i];//перезаписываем его во временную переменную
            }
        }
        return r;
    }
    void pigeon() {
        int min = getMin();
        int max = getMax();
        int[] freq = new int[max - min + 1];
        for (int i = 0; i < size; i++)
            freq[arr[i] - min]++;

        int arrIndex = 0;
        for (int i = 0; i < freq.length; i++)
            for (int elems = freq[i]; elems > 0; elems--)
                arr[arrIndex++] = i + min;
    }



    public int countingSort(){
        int countO = 0;
        int [] temp = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

            }
        }
        return countO;
    }


}
