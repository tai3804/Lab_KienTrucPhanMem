package iuh.fit;

public class ConsumptionTax implements PaymentStrategy {
    @Override
    public double calc(int amount) {
        return amount * 0.1;
    }
}
