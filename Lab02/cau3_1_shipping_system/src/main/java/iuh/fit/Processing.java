package iuh.fit;

public class Processing implements ShippingState {
    @Override
    public void process() {
        System.out.println("Đang xử lý, Đóng gói và vận chuyển");
    }
}
