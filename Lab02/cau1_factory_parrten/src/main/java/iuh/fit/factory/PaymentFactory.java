package iuh.fit.factory;

import iuh.fit.entity.Payment;

abstract class PaymentFactory {
    public abstract Payment createPayment();
}
