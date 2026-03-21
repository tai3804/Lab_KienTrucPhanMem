package iuh.fit;

public class QrPayment implements  PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("da tao ma qr cho so tien: " +amount+ "k, moi quet.");
    }
}
