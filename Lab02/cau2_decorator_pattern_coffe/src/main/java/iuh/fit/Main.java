package iuh.fit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // 1. Một ly Espresso cơ bản
        Beverage myCoffee = new Espresso();

        // 2. Bọc thêm lớp Sữa
        myCoffee = new Milk(myCoffee);

        // 3. Bọc thêm lớp Kem tươi (Lớp bọc lồng trong lớp bọc)
        myCoffee = new WhippedCream(myCoffee);

        System.out.println("Món của bạn: " + myCoffee.getDescription());
        System.out.println("Tổng tiền: " + myCoffee.cost() + "k");
    }
}