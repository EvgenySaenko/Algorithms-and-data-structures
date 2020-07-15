package lesson6;

import lesson4.Cat;

public class Main {
    public static String boxNameCat[] = {"Валенсия"," Баунти", "Свити", "Текила", "Ириска"," Карамель", "Виски", "Кукуруза"," Гренка"," Фасолька", "Льдинка", "Китана",
                      "Офелия", "Дайкири", "Брусника","Вальс", "Несквик", "Златан", "Баскет", "Изюм"," Цукат"," Мокко", "Месси", "Кокос", "Адидас"," Бейлиз", "Тайгер",
                      "Зефир", "Мохито", "Оксфорд"," Бисквит"," Алмаз", "Бади", "Бакс", "Боня", "Ватсон"," Вегас", "Кекс", "Кокос", "Лаки"," Мартин", "Оскар",
                       "Патрик", "Паштет", "Ричи"," Саймон", "Сём Сёмыч", "Том"," Феликс", "Яшка"};

    public static void main(String[] args) {

        //тестовое дерево
        Tree tree = new Tree();
        tree.insert(new Cat(9,"a"));
        tree.insert(new Cat(4,"b"));
        tree.insert(new Cat(13,"c"));
        tree.insert(new Cat(2,"c"));
        tree.insert(new Cat(8,"c"));
        tree.insert(new Cat(11,"c"));
        tree.insert(new Cat(15,"c"));
        tree.insert(new Cat(6,"c"));
        tree.insert(new Cat(14,"c"));
        tree.insert(new Cat(16,"c"));
       // tree.insert(new Cat(5,"c"));
        //заполнение нормального дерева
        ///////////////////////////////////////
        //System.out.println("Сбалансировано ли дерево " + tree + " : " + (tree.checkBalance(tree.root)));
        ////////////////////////////////////////

        Tree [] tr = new Tree[20];//20 деревьев

        for (int count = 0; count < 20; count++) {
            tr[count]  = new Tree();
            for (int j = 0; j < 100; j++) {
                if (j == 0){
                    tr[count].insert(new Cat(0,boxNameCat[1]));
                }
                //генерим возраст кота
                int age = (int)Math.floor ((Math.random()*(200+1)) - 100);
                //генерим имя коту
                int numberName = (int)Math.floor (Math.random() * 50);
                //вставляем котов
                tr[count].insert(new Cat(age,boxNameCat[numberName]));
            }
        }
        int notBalanced = 0;
        int balanced = 0;
        for (int i = 0; i < tr.length; i++) {
            boolean balance = tr[i].checkBalance(tr[i].root);
            if (balance){
                balanced++;
            }else
                notBalanced++;
            System.out.println("Сбалансировано ли дерево " + tr[i] + " : " + (tr[i].checkBalance(tr[i].root)));
        }

        System.out.println("Процент не сбалансированных деревьев : " + (tr.length * 100) / tr.length + " %");
        //при рандомном заполнении даже если первый закидывать ноль(середину) -  100% не сбалансированы






    }


}
