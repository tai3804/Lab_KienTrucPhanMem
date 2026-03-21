package iuh.fit;

public class ProcessFeeDecorator extends PaymentExtension {
    public ProcessFeeDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Phí xử lý: 5k");
        payment.pay(amount + 5);
    }
}
