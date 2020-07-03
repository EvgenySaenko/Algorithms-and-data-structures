package lesson3;

//программа переворачивает строки обратном порядке
public class Reverse {
    private char arr[];
    String reversLine;

    public String reverseReadString(String inputString) {
        if (inputString == null|| inputString.length() ==0){
            throw new NullPointerException("Входящая строка пустая");
        }
        char[] backArray = inputString.toCharArray();
        this.arr = new char[inputString.length()];
        for (int i = backArray.length-1, j =0; i >= 0; i--,j++) {
            this.arr[j] = backArray[i];
        }
        this.reversLine = String.valueOf(arr);
        return reversLine;
    }
}
