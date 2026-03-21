package iuh.fit;

public class VAT implements PaymentStrategy {
    @Override
    public double calc(int amount) {
        return amount * 0.15;
    }
}
