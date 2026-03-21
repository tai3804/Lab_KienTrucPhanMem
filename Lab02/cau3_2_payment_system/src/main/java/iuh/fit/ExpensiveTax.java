package iuh.fit;

public class ExpensiveTax implements PaymentStrategy{
    @Override
    public double calc(int amount) {
        return amount * 0.2;
    }
}
