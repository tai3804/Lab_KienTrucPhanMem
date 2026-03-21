package iuh.fit;

public class MomoPayment implements  Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Momo thanh toán " + amount + "k");
    }
}
