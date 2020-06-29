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
        this.arr = new int[capacity];
        this.size = 0;
    }
    public Array(int...args){
        this();
        this.size = args.length;
        this.arr = args;
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

    //добавление в конец массива
    public void append(int value){
        if (size >= arr.length){
            increaseCapacity();
        }
        arr[size++] = value;
        isSorted = false;
    }
    //удаляем последний элемент массива
    public int deleteLast(){
        if (size == 0)
            throw new ArrayIndexOutOfBoundsException(-1);
        return arr[--size];
    }
    //удаляем любой элемент в массиве
    public int delete(int value){
        if (size == 0 || value > size || value < 0) throw new ArrayIndexOutOfBoundsException(-1);
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
        return  arr[size = 0];
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

    public int countingSort(){
        int countO = 0;
        StringBuilder sb = new StringBuilder();
        int [] temp = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

            }
        }
        return countO;
    }


}
