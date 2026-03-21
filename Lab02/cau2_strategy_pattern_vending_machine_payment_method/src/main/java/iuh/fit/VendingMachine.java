package iuh.fit;

public class VendingMachine {
    private PaymentStrategy paymentStrategy;

    public VendingMachine() {
        this.paymentStrategy = new MomoPayment();
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
