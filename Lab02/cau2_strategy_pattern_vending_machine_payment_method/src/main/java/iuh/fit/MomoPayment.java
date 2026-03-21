package iuh.fit;

public class MomoPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("dang nhan " + amount + "k tien mat.");
    }
}
