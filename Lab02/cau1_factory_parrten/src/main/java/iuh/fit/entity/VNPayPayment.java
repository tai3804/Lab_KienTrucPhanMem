package iuh.fit.entity;

public class VNPayPayment implements Payment {

    @Override
    public void pay() {
        System.out.println("VN Pay Payment");
    }
}
