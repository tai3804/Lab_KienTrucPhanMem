package iuh.fit;

public class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán bằng thẻ số tiền: " + amount + "k");
    }
}
