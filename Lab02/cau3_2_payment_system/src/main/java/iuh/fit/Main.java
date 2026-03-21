package iuh.fit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PaymentSystem paymentSystem = new PaymentSystem();
        paymentSystem.setPaymentStrategy(new VAT());
        System.out.println("San pham ban mua gia 100, thue VAT (15%) la: " + paymentSystem.calc(100));

        paymentSystem.setPaymentStrategy(new ExpensiveTax());
        System.out.println("San pham ban mua gia 100, thue dac biet (20%) la: " + paymentSystem.calc(100));
    }
}