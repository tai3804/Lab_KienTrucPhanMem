package iuh.fit;

public class PaypalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paypal thanh toán " + amount + "k");
    }
}
