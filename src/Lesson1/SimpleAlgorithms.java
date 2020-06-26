package Lesson1;

public class SimpleAlgorithms {

    public static int [] box = {12,4,45,-4,18,34,6,-2,39,-8,20,39,79};
    public static int [] small = {2,4,6};

    //метод возведение числа в степень
    //сложность алгоритма зависит от размера в какую степень нужно возвести число
    //(n - 1) раз мы выполним перемножение числа на число
    public static int exp(int number, int exponentiation){//первое число, второе в какую степень возвести
        int result = number;
        int countO = 0;
        for (int i=2; i<=exponentiation; i++){
            result *= number;
            countO++;
        }
        System.out.println("Количество производимых действий = "+ countO);
        return result;
    }

    //Поиск минимального элемента в массиве
    //ВЫВОД мы будем совершать N действие где N - размер массива
    //Но присваивать минимальное значение это действие будет выполняться не всегда - значит оно незначительное К - действий и мы его не считаем?
    public static int minNumberSearch(int[] arr){//на вход получаем массив
        int countO = 0;
        int minNumber = arr[0];//возьмем за минимальный эелемент первый элемент массива и будем его сравнивать с каждым последующим
        for (int i = 1; i < arr.length; i++) {
            countO++;
            if (minNumber > arr[i]) {//проверять мы будем каждое число = весь размер массива
                minNumber = arr[i];//но вот присваивать будем не всегда
            }
        }
        System.out.println("Количество производимых действий = "+ countO);
        return minNumber;
    }

    //Нахождение среднего арифметического массива
    //Вывод О большое будет равно размеру массива, а значит зависеть от размера входящих данных, чем больше длина массива тем больше (О большое)
    public static void avg(int [] arr){
        int countO = 0;
        int item = 0;
        for (int i = 0; i <arr.length ; i++) {
            item +=arr[i];
            countO++;
        }
        System.out.println("Среднее арифметическое массива составит: " + item / arr.length);
        System.out.println("O большое = "+ countO);
    }

    //Нахождение среднего ариметического массива

    public static void main(String[] args) {
        System.out.println( exp(2,9));

        System.out.println(minNumberSearch(box));

        avg(small);
    }
}
