package iuh.fit.entity;

public class MomoPayment implements Payment{

    @Override
    public void pay() {
        System.out.println("Momo Payment");
    }
}
