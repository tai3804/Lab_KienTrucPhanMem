package iuh.fit;

public class HasMoneyState implements State {

    @Override
    public void insertMoney() {
        System.out.println("Bạn đã bỏ thêm tiền vào.");
    }

    @Override
    public void ejectMoney() {
        System.out.println("Trả lại tiền.");
    }
}
