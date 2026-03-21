package iuh.fit;

public class PaymentSystem {
    private PaymentStrategy paymentStrategy = new VAT();
    public PaymentSystem(PaymentStrategy paymentStrategy) {}

    public PaymentSystem() {    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public double calc(int amount) {
        return paymentStrategy.calc(amount);
    }
}
