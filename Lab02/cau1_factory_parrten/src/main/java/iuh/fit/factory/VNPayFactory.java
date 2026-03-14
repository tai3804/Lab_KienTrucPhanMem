package iuh.fit.factory;

import iuh.fit.entity.Payment;
import iuh.fit.entity.VNPayPayment;

public class VNPayFactory extends PaymentFactory {
    public VNPayFactory(){};

    @Override
    public Payment createPayment() {
        return new VNPayPayment();
    }
}
