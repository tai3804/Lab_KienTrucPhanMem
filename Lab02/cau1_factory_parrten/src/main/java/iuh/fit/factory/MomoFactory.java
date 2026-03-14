package iuh.fit.factory;

import iuh.fit.entity.MomoPayment;
import iuh.fit.entity.Payment;

public class MomoFactory extends PaymentFactory {
    public MomoFactory() {    }

    @Override
    public Payment createPayment() {
        return new MomoPayment();
    }
}
