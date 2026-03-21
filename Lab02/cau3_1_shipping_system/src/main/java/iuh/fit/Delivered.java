package iuh.fit;

public class Delivered implements ShippingState {
    @Override
    public void process() {
        System.out.println("Đã giao, Cập nhật trạng thái đơn hàng là đã giao.");
    }
}
