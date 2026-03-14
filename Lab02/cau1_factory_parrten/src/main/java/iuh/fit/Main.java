package iuh.fit;

import iuh.fit.entity.MomoPayment;
import iuh.fit.entity.Payment;
import iuh.fit.entity.VNPayPayment;
import iuh.fit.factory.MomoFactory;
import iuh.fit.factory.VNPayFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MomoFactory momoFactory = new MomoFactory();
        Payment momoPayment = momoFactory.createPayment();

        momoPayment.pay();


        VNPayFactory vnPayFactory = new VNPayFactory();
        Payment vnPayPayment = vnPayFactory.createPayment();

        vnPayPayment.pay();
    }
}