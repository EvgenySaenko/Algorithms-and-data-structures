package lesson4.rightHomeWork;

public class MainClass {
    public static void main(String[] args) {
        DoubleRelatedList<Cat> drl = new DoubleRelatedList<>();
        System.out.println(drl);
        drl.push(new Cat(1, "cat1"));
        drl.push(new Cat(2, "cat2"));
        drl.push(new Cat(3, "cat3"));
        System.out.println(drl);
        drl.pop();
        System.out.println(drl);
        System.out.println(drl.getIterator().getCurrent());
        System.out.println(drl.getIterator().hasNext());
        drl.getIterator().deleteCurrent();
        System.out.println(drl);
        drl.push(new Cat(4, "cat4"));
        System.out.println(drl);
        drl.delete(new Cat(4, "cat4"));
        System.out.println(drl);
    }
}
