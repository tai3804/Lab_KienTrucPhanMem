package iuh.fit;

public class Created implements ShippingState {
    @Override
    public void process() {
        System.out.println("Đơn hàng vừa được tạo, kiểm tra thông tin đơn hàng.");
    }
}
