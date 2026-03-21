package iuh.fit;

public class ShippingSystem {
    private ShippingState shippingState;

    public ShippingState getShippingState() {
        return shippingState;
    }

    public  void setShippingState(ShippingState shippingState) {
        this.shippingState = shippingState;
    }

    public ShippingSystem() {
        this.shippingState = new Created();
    }
}
