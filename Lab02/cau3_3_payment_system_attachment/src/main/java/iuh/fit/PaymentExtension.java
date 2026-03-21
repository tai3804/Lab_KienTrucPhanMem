package iuh.fit;

abstract class PaymentExtension implements Payment {
    protected Payment payment;

    public PaymentExtension(Payment payment) {
        this.payment = payment;
    }
}
