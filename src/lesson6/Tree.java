package lesson6;

import lesson4.Cat;

public class Tree {

    //travers
    //delete

    private class TreeNode implements Comparable{
        private Cat c;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Cat c){
            this.c = c;
        }
        @Override
        public String toString() {
            return "TreeNode{" +
                    "c=" + c.toString() +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Cat)) throw  new ClassCastException("Not a cat");
            return c.getAge() - ((Cat) o).getAge();//от текущего отнимаем предыдущий
        }
    }

    TreeNode root;//корень дерева

    //вставка элемента в дерево
    public void insert(Cat c){
        TreeNode node = new TreeNode(c);//создали ноду
        if (root == null){
            root = node;
        }else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true){
                parent = current;
                if (c.getAge() < current.c.getAge()){//если у входящего кота возраст меньше => уходим влево
                    current = current.left;
                    if (current == null){
                        parent.left = node;//становится левой ссылкой
                        return;
                    }
                }else if (c.getAge() > current.c.getAge()){
                    current = current.right;//уходим вправо
                    if (current == null){
                        parent.right = node;//становится правой ссылкой
                        return;
                    }
                }else {
                    return;
                }
            }
        }
    }

    //поиск элемента
    public Cat find(int age){
        TreeNode current = root;//ищем от корня
        while (current.c.getAge() != age){//ищем пока возраст кота в корне не будет равен нужному
            //если нужный возраст меньше чем в корне - идем влево, карент становится равным карент лефт, становится левой нодой корня
            current = (age < current.c.getAge() ? current.left : current.right);
            if (current == null)return null;//если уперлись в пустоту в конец и ничего не нашли
        }
        return current.c;//если нашли вышли из цыкла и вернули кота
    }
    //прямой порядок обхода дерева
    private void preOrderTraverse(TreeNode current){
        if (current != null){
            System.out.println(current.c.getAge() + " ");
            preOrderTraverse(current.left);
            preOrderTraverse(current.right);
        }
    }

    public void displayTree(){
        preOrderTraverse(root);
    }

    //удаление элемента
    public boolean delete(int age){
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while (current.c.getAge() != age){
            parent = current;
            if (age < current.c.getAge()){
                current = current.right;
                isLeftChild = true;
            }else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null){
                return false;
            }
        }
        //если нашли совпадение то мы можем быть листом,
        //у нас может быть один наследник
        //и два наследника

        //если  мы лист
        if (current.left == null && current.right == null){//если у нас левая и правая ссылка нулевые то мы нижний последний элемент(лист)
            if (current == root)//если корень - зануляем корень
                root = null;
            else if(isLeftChild)//если мы левый элемент
                parent.left = null;//зануляем левую ссылку
            else
                parent.right = null;
        }

        //один наследник
        else if (current.right == null){
            if (isLeftChild)//если я левый наследник
                parent.left = current.left;

            else //если правый
                 parent.right = current.left;

        }else if (current.left == null){
            if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }

        //удаление узла с двумя наследника
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root){
                root = successor;
            }else if (isLeftChild){
                parent.left = successor;
            }else {
                parent.right = successor;
            }
            successor.left = current.right;//найденый текущий узел

        }
        return true;
    }
    //метод будет искать лучшую замену для удаляемой ноды
    //пойдет направо искать левый лист или налево искать правый лист
    private TreeNode getSuccessor(TreeNode node){
       TreeNode parent = node;//представим что она родитель
       TreeNode s = node;//самый лучший
       TreeNode curr = node.right;//идем вправо ищем самого левого
        while (curr != null){
            parent = s;//тот кто был самым лучшим
            s = curr;// лучшего ставим на текущего
            curr = curr.left;
        }
        if (s != node.right){
            parent.left = s.right;
            s.right = node.right;
        }
        return s;
    }
}
