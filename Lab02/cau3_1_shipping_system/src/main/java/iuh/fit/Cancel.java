package iuh.fit;

public class Cancel implements ShippingState {
    @Override
    public void process() {
        System.out.println("Huỷ, huỷ đơn hàng và hoàn tiền.");
    }
}
