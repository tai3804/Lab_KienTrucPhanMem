package iuh.fit;

public class Milk extends AddonDecorator {
    public Milk(Beverage beverage) { super(beverage); }

    @Override
    public String getDescription() { return beverage.getDescription() + ", thêm Sữa"; }
    @Override
    public double cost() { return beverage.cost() + 5.0; } // Thêm 5k
}
