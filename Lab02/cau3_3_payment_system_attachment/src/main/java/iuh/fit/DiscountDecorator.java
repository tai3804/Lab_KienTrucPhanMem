package iuh.fit;

public class DiscountDecorator extends PaymentExtension {
    public DiscountDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        System.out.println("=> Áp dụng giảm giá 10%");
        payment.pay(amount * 0.9);
    }
}
