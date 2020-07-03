package lesson3;

public class Brackets {
    //проверяем каждый символ текста
    public static boolean check(String input){
        int size = input.length();
        Stack stack = new Stack(size);
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            if (ch == '[' || ch == '{' || ch == '('){
                stack.push(ch);
            }else if(ch == ']' || ch == '}' || ch == ')'){
                if (stack.isEmpty()){//если стек пустой
                    System.out.printf("Error: %c at %d\n", ch,i);
                    return false;
                }
                char c = (char) stack.pop();//выкинули из текста закр скобку
                if (ch == ']' && c == '[' ||
                        ch == '}' && c == '{'||
                        ch == ')'&& c == '(' ){
                    System.out.printf("Error: %c at %d\n", ch,i);
                    return false;
                }
            }
        }
        if (!stack.isEmpty()){//если стек не пустой вконце
            System.out.printf("Error: %c at %d\n", stack.peek(),size - 1);
            return false;
        }
        return true;
    }
}
