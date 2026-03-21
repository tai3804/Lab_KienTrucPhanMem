package iuh.fit;

public abstract class AddonDecorator implements Beverage {
    protected Beverage beverage;

    public AddonDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}
