package iuh.fit;

public class NoMoneyState implements State {

    @Override
    public void insertMoney() {
        System.out.println("Bạn đã bỏ tiền vào.");
    }

    @Override
    public void ejectMoney() {
        System.out.println("Bạn chưa bỏ tiền vào nên không thể hoàn");
    }
}
